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
        Thread.sleep(1000);

        Date today = new Date();
        model.addAttribute("today", today);
        return "welcome";
    }
}
