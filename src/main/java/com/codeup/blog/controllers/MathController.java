package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {
    @GetMapping("/add/{num1}/and/{num2}")
    @ResponseBody
    public long sum(@PathVariable long num1, @PathVariable long num2){
        return num1 + num2;
    }
    @GetMapping("/subtract/{number1}/from/{number2}")
    @ResponseBody
    public long subtract(@PathVariable long number1, @PathVariable long number2){
        return number1 - number2;
    }
    @GetMapping("/multiply/{number1}/and/{number2}")
    @ResponseBody
    public long multiply(@PathVariable long number1, @PathVariable long number2){
        return number1 * number2;
    }
    @GetMapping("/divide/{number1}/by/{number2}")
    @ResponseBody
    public double divide (@PathVariable long number1, @PathVariable long number2){
        return number1 / number2;
    }
}


