package com.ra.demo9.controller;

import com.ra.demo9.model.dto.ProductDetailDTO;
import com.ra.demo9.model.dto.ProductRequest;
import com.ra.demo9.model.entity.Category;
import com.ra.demo9.model.entity.Product;
import com.ra.demo9.model.entity.ProductDetail;
import com.ra.demo9.service.ICategoryService;
import com.ra.demo9.service.IProductDetailService;
import com.ra.demo9.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
public class ProductDetailController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IProductDetailService productDetailService;

    @RequestMapping(value = {"/productDetail"})
    public String productDetailHome(Model model, @RequestParam(defaultValue = "0") Integer currentPage, @RequestParam(defaultValue = "4") Integer size) {
        List<ProductDetail> productDetails =productDetailService.getProductDetail(currentPage, size);
        model.addAttribute("isProductDetail", "productDetail");
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", Math.ceil((double) productService.countAllProduct() / size));
        model.addAttribute("productDetailList", productDetails);
        model.addAttribute("productDetailDTO", new ProductDetailDTO());
        model.addAttribute("products", productService.getProduct(currentPage,10));
        return "/adminProductDetail";
    }

    @GetMapping("/createProductDetail")
    public String createProductDetail(Model model) {
        model.addAttribute("productDetailDTO", new ProductDetailDTO());
        return "/adminProductDetail";
    }

    @PostMapping("/saveProductDetail")
    public String actionCreateProductDetail(@Valid @ModelAttribute("productDetailDTO") ProductDetailDTO productDetailDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("productDetailDTO", new ProductDetailDTO());
            model.addAttribute("products", productService.getProduct(0,10));
            return "/adminProductDetail";
        }
        ProductDetail productDetail = ProductDetail.builder()
                .productDetailId(productDetailDTO.getProductDetailId())
                .product(productService.getProductById(productDetailDTO.getProductId()))
                .color(productDetailDTO.getColor())
                .quantity(productDetailDTO.getQuantity())
                .size(productDetailDTO.getSize())
                .build();
        productDetailService.insertProductDetail(productDetail);
        Product product = productDetail.getProduct();
        Integer totalQuantity = productService.getTotalQuantityByProductId(product.getProductId());
        product.setStockQuantity(totalQuantity);
        productService.updateProductStock(product);
        return "redirect:/productDetail";
    }

    @RequestMapping("/showProductDetail/{id}")
    public String showProductDetail(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        List<ProductDetail> productDetails = productDetailService.findByProductId(id);
        List<Product> products = productService.listProductOfCategory(id,product.getProductName());
        model.addAttribute("product", product);
        model.addAttribute("productDetailList2", productDetails);;
        model.addAttribute("productList5",products);
        return "Productdetail";
    }

    @PostMapping("/addProductDetail")
    public String addProductDetail( @ModelAttribute("category") Category category) {
        return "/adminProductDetail";
    }
}
