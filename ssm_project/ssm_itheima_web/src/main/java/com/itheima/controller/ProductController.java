package com.itheima.controller;

import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/findAll")
    public String findAll(Model model) throws Exception {
        List<Product> productList = productService.findAll();
        model.addAttribute("productList",productList);
        return "product-list";
    }



  @RequestMapping("/saveProduct")
  public String saveProduct(Product product) throws Exception{
     //将参数改为departureTimeStr
      if(product.getDepartureTimeStr()!=null){
          DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
          Date date = dateFormat.parse(product.getDepartureTimeStr());
          product.setDepartureTime(date);
      }
      productService.saveProduct(product);
      return "redirect: findAll";
  }
}
