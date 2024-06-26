package com.ra.demo9.controller;

import com.ra.demo9.model.dto.ProductRequest;
import com.ra.demo9.model.entity.Product;
import com.ra.demo9.model.entity.Users;
import com.ra.demo9.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductController {
    @Autowired
    UserService userService;
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private FileService fileService;
    @Autowired
    private IProductDetailService productDetailService;
    LocalDateTime currentTime = LocalDateTime.now();

    @Value("${file-upload}")
    private String fileUpload;

    @RequestMapping(value = {"/Product"})
    public String productHome(Model model, @RequestParam(defaultValue = "0") Integer currentPage, @RequestParam(defaultValue = "4") Integer size) {
        List<Product> products = productService.getProduct(currentPage, size);
        model.addAttribute("isproduct", "product");
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", Math.ceil((double) productService.countAllProduct() / size));
        model.addAttribute("products", products);
        model.addAttribute("productRequest", new ProductRequest());
        model.addAttribute("categories", categoryService.getCategory(currentPage,10));
        return "/adminproduct";
    }

    @GetMapping("/createProduct")
    public String createProduct(Model model) {
        model.addAttribute("productRequest", new ProductRequest());
        return "/adminproduct";
    }

    @PostMapping("/saveProduct")
    public String actionCreateProduct(@Valid @ModelAttribute("productRequest") ProductRequest productRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("productRequest", productRequest);
            model.addAttribute("categories", categoryService.getCategory(0,10));
            return "/adminproduct";
        }

        // Xử lý tải lên ảnh
        String imageUrl = "";
        if (productRequest.getProductImage() != null && !productRequest.getProductImage().isEmpty()) {
            try {
                imageUrl = fileService.uploadFileToServer(productRequest.getProductImage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Product product = Product.builder()
                .productId(productRequest.getProductId())
                .sku(productRequest.getSku())
                .productName(productRequest.getProductName())
                .description(productRequest.getDescription())
                .unitPrice(productRequest.getUnitPrice())
                .stockQuantity(productService.getTotalQuantityByProductId(productRequest.getProductId()))
                .createdAt(currentTime)
                .updatedAt(currentTime)
                .category(categoryService.getCategoryById(productRequest.getCategoryId()))
                .image(imageUrl)
                .build();
        boolean bl = productService.insertProduct(product);
        if (bl) {
            return "redirect:/admin/Product";
        } else {
            model.addAttribute("productRequest", product);
            return "/adminproduct";
        }
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/Product";
    }

    @GetMapping("/editProduct/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductId(product.getProductId());
        productRequest.setProductName(product.getProductName());
        productRequest.setSku(product.getSku());
        productRequest.setDescription(product.getDescription());
        productRequest.setUnitPrice(product.getUnitPrice());
        productRequest.setUpdatedAt(currentTime);
        productRequest.setStockQuantity(productService.getTotalQuantityByProductId(product.getProductId()));
        productRequest.setCategoryId(product.getCategory().getCategoryId());

        model.addAttribute("productRequest", productRequest);
        model.addAttribute("categories", categoryService.getCategory(0,10));
        return "/adminproduct";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@Valid @ModelAttribute("productRequest") ProductRequest productRequest, BindingResult result, Model model, Integer currentPage, Integer size) {
        if (result.hasErrors()) {
            model.addAttribute("productRequest", productRequest);
            model.addAttribute("categories", categoryService.getCategory(currentPage, size));
            return "/adminproduct";
        }

        Long productId = productRequest.getProductId();
        if (productId == null) {
            result.rejectValue("productId", "error.product", "Product ID is required");
            model.addAttribute("categories", categoryService.getCategory(currentPage, size));
            return "/adminproduct";
        }

        // Lấy sản phẩm hiện có từ cơ sở dữ liệu
        Product existingProduct = productService.getProductById(productId);
        if (existingProduct == null) {
            result.rejectValue("productId", "error.product", "Product not found");
            model.addAttribute("categories", categoryService.getCategory(currentPage, size));
            return "/adminproduct";
        }

        // Cập nhật chi tiết sản phẩm
        existingProduct.setProductName(productRequest.getProductName());
        existingProduct.setSku(productRequest.getSku());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setUnitPrice(productRequest.getUnitPrice());
        existingProduct.setUpdatedAt(currentTime);
        existingProduct.setCategory(categoryService.getCategoryById(productRequest.getCategoryId()));

        // Tính toán và cập nhật stockQuantity
        Integer totalQuantity = productService.getTotalQuantityByProductId(productId);
        existingProduct.setStockQuantity(totalQuantity);

        // Cập nhật hình ảnh nếu có
        if (productRequest.getProductImage() != null && !productRequest.getProductImage().isEmpty()) {
            try {
                String imageUrl = fileService.uploadFileToServer(productRequest.getProductImage());
                existingProduct.setImage(imageUrl);
            } catch (Exception e) {
                e.printStackTrace();
                result.rejectValue("productImage", "error.product", "Failed to upload image");
                model.addAttribute("categories", categoryService.getCategory(currentPage, size));
                return "/adminproduct";
            }
        }

        productService.updateProduct(existingProduct, productRequest);
        return "redirect:/admin/Product";
    }


    @GetMapping("/viewProduct/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            model.addAttribute("error", "Product not found");
            return "redirect:/admin/Product";
        }
        model.addAttribute("product", product);
        return "/viewsProduct";
    }

    @GetMapping("/sortByName")
    public String sortByName(Model model, @RequestParam(defaultValue = "0") Integer currentPage, @RequestParam(defaultValue = "3") Integer size) {
        model.addAttribute("issort", "sort");
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", Math.ceil((double) productService.countAllProduct() / size));
        model.addAttribute("products", productService.sortByName(currentPage, size));
        model.addAttribute("productRequest", new ProductRequest());
        model.addAttribute("categories", categoryService.getCategory(currentPage,size));
        return "/adminproduct";
    }

    @GetMapping("/search")
    public String search(@RequestParam("search") String name, Model model, @RequestParam(defaultValue = "0") Integer currentPage, @RequestParam(defaultValue = "3") Integer size) {
        List<Product> searchedProducts = productService.getProductByName(name, currentPage, size);
        model.addAttribute("issearch", name);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", Math.ceil((double) productService.countProductByName(name) / size));
        model.addAttribute("products", searchedProducts);
        model.addAttribute("productRequest", new ProductRequest());
        model.addAttribute("categories", categoryService.getCategory(currentPage,size));

        return "/adminproduct";
    }




}
