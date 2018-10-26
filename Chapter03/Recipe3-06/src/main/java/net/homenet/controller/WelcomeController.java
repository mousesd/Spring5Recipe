package net.homenet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
public class WelcomeController {
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(Model model) throws InterruptedException {
        Thread.sleep(500);

        Date today = new Date();
        model.addAttribute("today", today);
        return "welcome";   // ResourceBundleViewResolver 를 사용하는 경우 IntelliJ에서 인식하지 못함
    }
}
