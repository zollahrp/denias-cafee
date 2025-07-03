package deniascafe.demo.controller;

import deniascafe.demo.config.DatabaseConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
public class WebController {

    @Autowired
    private DatabaseConfig databaseConfig;

    @GetMapping("/aboutus")
    public String aboutUs() {
        return "aboutus";
    }

    /*@GetMapping("/contact")
    public String contact() {
        return "contact";
    }*/

    @GetMapping("/review")
    public String review() {
        return "review";     
    }

    @GetMapping("/pagereview")
    public String pagereview() {
        return "pagereview";     
    }


    @GetMapping("/faq")
    public String faq() {
        return "faq";     
    }

    @GetMapping("/dbstatus")
    @ResponseBody
    public String checkDatabaseConnection() {
        return databaseConfig.checkDatabaseConnection();
    }

    @GetMapping("/dashboardlogin")
    public String dashboardlogin() {
        return "dashboardlogin";
    }

    @GetMapping("/change-language")
    public String changeLanguage(@RequestParam("lang") String lang, HttpServletRequest request, HttpServletResponse response) {
        org.springframework.web.servlet.LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if (localeResolver != null) {
            localeResolver.setLocale(request, response, Locale.forLanguageTag(lang));
        }
        return "redirect:/";
    }
}
