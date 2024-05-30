package com.ra.demo9.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ra.demo9.model.entity.Users;
import com.ra.demo9.repository.AddressDao;
import com.ra.demo9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;

@Controller
public class SocialFacebookController {
    @Autowired
    private UserService userService;
    @Autowired
    AddressDao addressDao;
    @Value("${facebook.redirect.uri}")
    private String redirectUri;

    @Value("${facebook.client.id}")
    private String clientId;

    @Value("${facebook.client.secret}")
    private String clientSecret;

    @GetMapping("/login/facebook")
    public String login() {
        String facebookLoginUrl = "https://www.facebook.com/v12.0/dialog/oauth?client_id=" + clientId
                + "&redirect_uri=" + redirectUri + "&scope=email,public_profile";
        return "redirect:" + facebookLoginUrl;
    }

    @GetMapping("/login/oauth2/code/facebook")
    public String loginFacebook(@RequestParam("code") String code, Model model, HttpSession session) {
        String accessToken = getFacebookAccessToken(code);
        if (accessToken != null) {
            JsonNode userProfile = getFacebookUserProfile(accessToken);
            System.out.println(userProfile);
            String facebookId = userProfile.get("id").asText();
            String name = userProfile.get("name").asText();
            String email = userProfile.get("email").asText();
         //   String avatarUrl = userProfile.get("picture").get("data").get("url").asText();
            String avatarUrl = userProfile.get("picture").get("data").get("url").asText();
            // Kiểm tra xem nó tồn tại user trong DB chưa
            // CHưa thì tạo mới, có rồi thì lấy ra
            Users user = userService.findOrCreateUser(name, email,facebookId,avatarUrl);
            // xử lí lưu session
            model.addAttribute("user", user);
            session.setAttribute("user", user);
            session.setAttribute("address",addressDao.getAllAddress(user.getUserId()));
            return "redirect:/";
        } else {
            return "redirect:/login?error";
        }
    }

    private String getFacebookAccessToken(String code) {
        String accessTokenUrl = "https://graph.facebook.com/v12.0/oauth/access_token?client_id=" + clientId
                + "&redirect_uri=" + redirectUri + "&client_secret=" + clientSecret + "&code=" + code;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(accessTokenUrl, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.readTree(response.getBody());
                return jsonNode.get("access_token").asText();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private JsonNode getFacebookUserProfile(String accessToken) {
        String userProfileUrl = "https://graph.facebook.com/me?fields=id,name,email,picture&access_token=" + accessToken;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(userProfileUrl, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readTree(response.getBody());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "home";
    }
}