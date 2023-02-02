package com.neurogine.assignment.demo.controller;

import com.neurogine.assignment.demo.entity.Product;
import com.neurogine.assignment.demo.exception.ProductNotFoundException;
import com.neurogine.assignment.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String showProductList(Model model){
        List<Product> productList = productService.listAll();
        model.addAttribute("productList",productList);
        model.addAttribute("pageTitle","Add New Product");
        return "manage-product";
    }

    @GetMapping("/products/new")
    public String showNewForm(Model model){
        Product product=new Product();
        model.addAttribute("product",product);
        return "product-form";
    }
    @GetMapping("/products/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id,Model model,RedirectAttributes redirectAttributes){
        try {
            Product product=productService.get(id);
            model.addAttribute("product",product);
            model.addAttribute("pageTitle","Edit Product (ID: "+id+" )");
            return "product-form";


        } catch (ProductNotFoundException e) {
            redirectAttributes.addFlashAttribute("message",e.getMessage());
            return "redirect:/products";
        }

    }

    @PostMapping("/products/new")
    public String saveProduct(Product product, RedirectAttributes redirectAttributes){
        productService.save(product);
        redirectAttributes.addFlashAttribute("message","The Product Saved Successfully");
        return "redirect:/products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id,RedirectAttributes redirectAttributes){
        try {
            productService.delete(id);
            redirectAttributes.addFlashAttribute("message","The Product Deleted Successfully");

        }catch(ProductNotFoundException ex){
            redirectAttributes.addFlashAttribute("message",ex.getMessage());

        }
        return "redirect:/products";
    }



}
