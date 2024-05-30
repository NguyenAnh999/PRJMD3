package com.ra.demo9.controller;

import com.ra.demo9.model.dto.ProductDetailDTO;
import com.ra.demo9.model.dto.ProductRequest;
import com.ra.demo9.model.entity.Category;
import com.ra.demo9.model.entity.Comment;
import com.ra.demo9.model.entity.Product;
import com.ra.demo9.model.entity.ProductDetail;
import com.ra.demo9.service.CommentService;
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
@RequestMapping("/admin")
public class ProductDetailController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IProductDetailService productDetailService;
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = {"/productDetail"})
    public String productDetailHome(Model model, @RequestParam(defaultValue = "0") Integer currentPage, @RequestParam(defaultValue = "4") Integer size) {
        List<ProductDetail> productDetails =productDetailService.getProductDetail(currentPage, size);
        model.addAttribute("isProductDetail", "productDetail");
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", Math.ceil((double) productService.countAllProduct() / size));
        model.addAttribute("productDetailList", productDetails);
        model.addAttribute("productDetailDTO", new ProductDetailDTO());
        model.addAttribute("products", productService.getProduct(currentPage,10,true));
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
            model.addAttribute("products", productService.getProduct(0,10,true));
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
        return "redirect:/admin/productDetail";
    }
    @GetMapping("/deleteProductDetail/{id}")
    public String deleteProductDetail(@PathVariable("id") Long id) {
        productDetailService.deleteProductDetail(id);
        return "redirect:/admin/productDetail";
    }

    @GetMapping("/editProductDetail/{id}")
    public String editProductDetail(@PathVariable Long id, Model model) {
        ProductDetail productDetail = productDetailService.getProductDetailById(id);
        ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        productDetailDTO.setProductDetailId(productDetail.getProductDetailId());
        productDetailDTO.setProductId(productDetail.getProduct().getProductId());
        productDetailDTO.setColor(productDetail.getColor());
        productDetailDTO.setSize(productDetail.getSize());
        productDetailDTO.setQuantity(productDetail.getQuantity());

        model.addAttribute("productDetailDTO", productDetailDTO);
        model.addAttribute("products", productService.getProduct(0, 10, true));
        return "adminProductDetail";
    }

    @PostMapping("/updateProductDetail")
    public String updateProductDetail(@Valid @ModelAttribute("productDetailDTO") ProductDetailDTO productDetailDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("productDetailDTO", productDetailDTO);
            model.addAttribute("products", productService.getProduct(0, 10, true));
            return "adminProductDetail";
        }

        ProductDetail existingProductDetail = productDetailService.getProductDetailById(productDetailDTO.getProductDetailId());
        if (existingProductDetail == null) {
            return "redirect:/admin/productDetail";
        }

        existingProductDetail.setProduct(productService.getProductById(productDetailDTO.getProductId()));
        existingProductDetail.setColor(productDetailDTO.getColor());
        existingProductDetail.setSize(productDetailDTO.getSize());
        existingProductDetail.setQuantity(productDetailDTO.getQuantity());
        productDetailService.updateProductDetail(existingProductDetail,productDetailDTO);
        return "redirect:/admin/productDetail";
    }
}
