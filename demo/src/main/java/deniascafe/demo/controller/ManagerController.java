package deniascafe.demo.controller;

import deniascafe.demo.model.Manager;
import deniascafe.demo.service.ManagerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @GetMapping("/login-manager")
    public String showLoginForm() {
        return "login-manager";
    }

    @PostMapping("/login-manager")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        if (managerService.authenticate(username, password)) {
            session.setAttribute("username", username);
            return "redirect:/dashboard-manager";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login-manager";
        }
    }

    @GetMapping("/logout-manager")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login-manager";
    }

    @GetMapping("/profile-manager")
    public String showProfile(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login-manager";
        }
        Manager manager = managerService.findByUsername(username).orElseThrow();
        model.addAttribute("manager", manager);
        return "profile-manager";
    }

    @PostMapping("/profile-manager")
    public String updateProfile(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        String currentUsername = (String) session.getAttribute("username");
        if (currentUsername == null) {
            return "redirect:/login-manager";
        }
        Manager manager = managerService.findByUsername(currentUsername).orElseThrow();
        manager.setUsername(username);
        if (!password.isEmpty()) {
            manager.setPassword(password);
        }
        managerService.updateManager(manager);
        session.setAttribute("username", username);
        return "redirect:/dashboard-manager";
    }
}