package net.homenet.controller;

import net.homenet.domain.PeriodicReservation;
import net.homenet.domain.Player;
import net.homenet.service.PeriodicReservationValidator;
import net.homenet.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.util.WebUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/periodicReservationForm")
@SessionAttributes("reservation")
public class PeriodReservationController {
    private final ReservationService reservationService;
    private final PeriodicReservationValidator validator;
    private final Map<Integer, String> pageForms = new HashMap<>();

    public PeriodReservationController(ReservationService reservationService, PeriodicReservationValidator validator) {
        this.reservationService = reservationService;
        this.validator = validator;
    }

    @PostConstruct
    public void initialize() {
        pageForms.put(0, "reservationCourtForm");
        pageForms.put(1, "reservationTimeForm");
        pageForms.put(2, "reservationPlayerForm");
    }

    @ModelAttribute("periods")
    public Map<Integer, String> periods() {
        Map<Integer, String> periods = new HashMap<>();
        periods.put(1, "Daily");
        periods.put(7, "Weekly");
        return periods;
    }

    @GetMapping
    public String setupForm(Model model) {
        PeriodicReservation periodicReservation = new PeriodicReservation();
        periodicReservation.setPlayer(new Player());
        model.addAttribute("reservation", periodicReservation);
        return "reservationCourtForm";
    }

    @PostMapping(params = { "_cancel" })
    public String cancelForm(@RequestParam("_page") int currentPage) {
        return pageForms.get(currentPage);
    }

    @PostMapping
    public String submitForm(HttpServletRequest request
        , @ModelAttribute("reservation") PeriodicReservation periodicReservation
        , BindingResult result
        , @RequestParam("_page") int currentPage) {

        int targetPage = getTargetPage(request, "_target", currentPage);
        if (targetPage < currentPage)
            return pageForms.get(targetPage);

        validateCurrentPage(result, currentPage);
        if (!result.hasErrors())
            return pageForms.get(targetPage);
        return pageForms.get(currentPage);
    }

    @PostMapping(params = { "_finish" })
    public String completeForm(@ModelAttribute("reservation") @Validated PeriodicReservation periodicReservation
        , BindingResult result
        , SessionStatus status
        , @RequestParam("_page") int currentPage) {

        if (result.hasErrors())
            return pageForms.get(currentPage);

        reservationService.makePeriodic(periodicReservation);
        status.setComplete();
        return "redirect:reservationSuccess";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    private int getTargetPage(HttpServletRequest request, String prefix, int currentPage) {
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            if (paramName.startsWith(prefix)) {
                for (int index = 0; index < WebUtils.SUBMIT_IMAGE_SUFFIXES.length; index++) {
                    String suffix = WebUtils.SUBMIT_IMAGE_SUFFIXES[index];
                    if (paramName.endsWith(suffix)) {
                        paramName = paramName.substring(0, paramName.length() - suffix.length());
                    }
                }
                return Integer.parseInt(paramName.substring(prefix.length()));
            }
        }
        return currentPage;
    }

    private void validateCurrentPage(BindingResult result, int currentPage) {
        switch (currentPage) {
            case 0:
                validator.validateCourt(result);
                break;
            case 1:
                validator.validateTime(result);
                break;
            case 2:
                validator.validatePlayer(result);
                break;
        }
    }
}
