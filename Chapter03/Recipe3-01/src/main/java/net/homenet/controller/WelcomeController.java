package net.homenet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

//# 1.
@Controller
@RequestMapping("/welcome")
public class WelcomeController {
    @RequestMapping(method = RequestMethod.GET)
    public String welcome(Model model) {
        Date today = new Date();
        model.addAttribute("today", today);
        return "welcome";
    }
}

//# 2.
//@Controller
//public class WelcomeController {
//    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
//    public String welcome(Model model) {
//        Date today = new Date();
//        model.addAttribute("today", today);
//        return "welcome";
//    }
//}

//# 3.
//@Controller
//public class WelcomeController {
//    @GetMapping("/welcome")
//    public String welcome(Model model) {
//        Date today = new Date();
//        model.addAttribute("today", today);
//        return "welcome";
//    }
//}
