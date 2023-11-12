package vn.edu.iuh.fit.frontends.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class ShoppingController {

    @GetMapping("/shopping/home")
    public String homePage(){
        return "/client/shopping/home";
    }
    @GetMapping("/shopping/cart")
    public String checkOut(){
        return "/client/shopping/cart";
    }
}
