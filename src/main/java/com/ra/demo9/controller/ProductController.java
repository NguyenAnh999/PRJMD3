package com.ra.demo9.controller;

import com.ra.demo9.model.dto.ProductRequest;
import com.ra.demo9.model.entity.Product;
import com.ra.demo9.service.ICategoryService;
import com.ra.demo9.service.IProductService;
import com.ra.demo9.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private FileService fileService;
    LocalDateTime currentTime = LocalDateTime.now();

    @Value("${file-upload}")
    private String fileUpload;

    @RequestMapping(value = {"/Product"})
    public String productHome(Model model,@RequestParam (defaultValue = "0") int currentPage,@RequestParam(defaultValue = "3") int size) {
        List<Product> products = productService.getProduct(currentPage,size);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", Math.ceil((double) productService.countAllProduct()/size));
        model.addAttribute("products", products);
        model.addAttribute("productRequest", new ProductRequest());
        model.addAttribute("categories", categoryService.getCategory());
        return "/productAdmin";
    }

    @GetMapping("/createProduct")
    public String createProduct(Model model) {
        model.addAttribute("product", new ProductRequest());
        model.addAttribute("view","catalog_add");
        model.addAttribute("categories", categoryService.getCategory());
        return "/productAdmin";
    }

    @PostMapping("/saveProduct")
    public String actionCreateProduct(@Valid @ModelAttribute("productRequest") ProductRequest productRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("productRequest", productRequest);
            model.addAttribute("categories", categoryService.getCategory());
            return "/productAdmin";
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
                .stockQuantity(productRequest.getStockQuantity())
                .createdAt(currentTime)
                .updatedAt(currentTime)
                .category(categoryService.getCategoryById(productRequest.getCategoryId()))
                .image(imageUrl)
                .build();
        boolean bl = productService.insertProduct(product);
        if (bl) {
            return "redirect:/Product";
        } else {
            model.addAttribute("productRequest", product);
            return "/productAdmin";
        }
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/Product";
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
        productRequest.setStockQuantity(product.getStockQuantity());
        productRequest.setCategoryId(product.getCategory().getCategoryId());

        model.addAttribute("product", productRequest);
        model.addAttribute("categories", categoryService.getCategory());
        return "/productAdmin";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@Valid @ModelAttribute("product") ProductRequest productRequest, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("product", productRequest);
            model.addAttribute("categories", categoryService.getCategory());
            return "/productAdmin";
        }

        Long productId = productRequest.getProductId();
        if (productId == null) {
            result.rejectValue("productId", "error.product", "Product ID is required");
            model.addAttribute("categories", categoryService.getCategory());
            return "/productAdmin";
        }

        // Lấy sản phẩm hiện có từ cơ sở dữ liệu
        Product existingProduct = productService.getProductById(productId);
        if (existingProduct == null) {
            result.rejectValue("productId", "error.product", "Product not found");
            model.addAttribute("categories", categoryService.getCategory());
            return "/productAdmin";
        }

        // Cập nhật chi tiết sản phẩm
        existingProduct.setProductName(productRequest.getProductName());
        existingProduct.setSku(productRequest.getSku());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setUnitPrice(productRequest.getUnitPrice());
        existingProduct.setStockQuantity(productRequest.getStockQuantity());
        existingProduct.setUpdatedAt(currentTime);
        existingProduct.setCategory(categoryService.getCategoryById(productRequest.getCategoryId()));

        // Cập nhật hình ảnh nếu có
        if (productRequest.getProductImage() != null && !productRequest.getProductImage().isEmpty()) {
            try {
                String imageUrl = fileService.uploadFileToServer(productRequest.getProductImage());
                existingProduct.setImage(imageUrl);
            } catch (Exception e) {
                e.printStackTrace();
                result.rejectValue("productImage", "error.product", "Failed to upload image");
                model.addAttribute("categories", categoryService.getCategory());
                return "/productAdmin";
            }
        }

        productService.updateProduct(existingProduct,productRequest);
        return "redirect:/Product";
    }

    @GetMapping("/viewProduct/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            model.addAttribute("error", "Product not found");
            return "redirect:/Product";
        }
        model.addAttribute("product", product);
        return "/viewsProduct";
    }

    @GetMapping("/sortByName")
    public String sortByName( Model model,@RequestParam (defaultValue = "0") int currentPage,@RequestParam(defaultValue = "3") int size) {
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", Math.ceil((double) productService.countAllProduct()/size));
        model.addAttribute("products", productService.sortByName(currentPage,size));
        model.addAttribute("product", new ProductRequest());
        model.addAttribute("categories", categoryService.getCategory());
        return "/productAdmin";
    }

    @GetMapping("/search")
    public String search(@RequestParam ("search")String name, Model model,@RequestParam (defaultValue = "0") int currentPage,@RequestParam(defaultValue = "3") int size) {
        List<Product> searchedProducts = productService.getProductByName(name,currentPage,size);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", Math.ceil((double) productService.countAllProduct()/size));
        model.addAttribute("products", searchedProducts);
        model.addAttribute("product", new ProductRequest());
        model.addAttribute("categories", categoryService.getCategory());

        return "/productAdmin";
    }
}
