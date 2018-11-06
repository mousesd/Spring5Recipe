package net.homenet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {
    //# TODO: @GetMapping 를 주석처리하면 ServletException 이 발생하는지 확인
    @GetMapping
    public String welcome(Model model) {
        model.addAttribute("today", Mono.just(LocalDate.now()));
        return "welcome";
    }
}
