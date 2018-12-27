package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import com.itheima.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String findAll(@RequestParam(required = false,defaultValue = "1") Integer pageNum, @RequestParam(required = false,defaultValue = "1") Integer pageSize,Model model) throws Exception {
        List<Product> productList = productService.findAll(pageNum,pageSize);
        /*将查询出来的集合封装成为pageinfo对象*/
        PageInfo pageInfo = new PageInfo(productList);
        model.addAttribute("page",pageInfo);
        return "product-list";
    }



  @RequestMapping("/saveProduct")
  public String saveProduct(Product product) throws Exception{
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
      if(product.getDepartureTimeStr()!=null){
          Date deaptureTime = simpleDateFormat.parse(product.getDepartureTimeStr());
          product.setDepartureTime(deaptureTime);
      }
      productService.saveProduct(product);
      return "redirect: findAll";
  }
}
