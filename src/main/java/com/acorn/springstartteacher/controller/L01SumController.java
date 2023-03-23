package com.acorn.springstartteacher.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class L01SumController {
    @GetMapping("/sum.do")
    public void sum(int a, int b, Model model){
        model.addAttribute("a",a);
        model.addAttribute("b",b);
        //==req.setAttribute("a",a):
        //model.addAttribute :thymeleaf 문서에서 a,b 변수를 사용 가능
        //req.getRequestDistpatcher("/templates/sum.html").forward(req,resp)
    }
    @GetMapping("/mult.do")
    public String multiply(
            @RequestParam(name = "a",required = true) int a,
            //required=true 파라미터 a가 없으면 400
            @RequestParam(name = "b",defaultValue="0") int b,
            Model model){
            //required = false : 기본형으로 파라미터를 받으 싶으면 defaultValue 를 작성해야한다.
            model.addAttribute("a",a);
            model.addAttribute("b",b);
        return "/multiply"; //=> /templates/multiply.html 을 렌더링 한다.
    }
}
