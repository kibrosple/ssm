package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Orders;
import com.itheima.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OredrsController {
    @Autowired
    private OrdersService ordersService;
    @RequestMapping("findAll")
    public String findAll(@RequestParam(required = false,defaultValue = "1") Integer pageNum, @RequestParam(required = false,defaultValue = "1")Integer pageSize, Model model) throws Exception{
        List<Orders> ordersList = ordersService.findAll(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(ordersList);
        model.addAttribute("page",pageInfo);
        return "orders-list";
    }
}
