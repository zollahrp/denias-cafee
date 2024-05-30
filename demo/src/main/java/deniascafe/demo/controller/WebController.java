package deniascafe.demo.controller;

import deniascafe.demo.config.DatabaseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

    @Autowired
    private DatabaseConfig databaseConfig;

    @GetMapping("/aboutus")
    public String aboutUs() {
        return "aboutus";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

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
}
