package net.homenet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    //# 1.Use Device, DeviceUtils class in controller)
    //@RequestMapping("/home")
    //public String index(HttpServletRequest request) {
    //    Device device = DeviceUtils.getCurrentDevice(request);
    //    if (device.isMobile()) {
    //        return "mobile/home";
    //    } else if (device.isTablet()) {
    //        return "tablet/home";
    //    } else {
    //        return "home";
    //    }
    //}

    //# 2.Use MethodArgumentResolver
    //@RequestMapping("/home")
    //public String index(Device device) {
    //    if (device.isMobile()) {
    //        return "mobile/home";
    //    } else if (device.isTablet()) {
    //        return "tablet/home";
    //    } else {
    //        return "home";
    //    }
    //}

    //# 3.Use LiteDeviceDelegatingViewResolver
    @RequestMapping("/home")
    public String index() {
        return "home";
    }
}
