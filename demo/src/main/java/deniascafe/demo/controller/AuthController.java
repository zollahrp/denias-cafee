package deniascafe.demo.controller;

import deniascafe.demo.model.Admin;
import deniascafe.demo.service.AdminService;
import deniascafe.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class AuthController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtUtil jwtUtil;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "login";
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "signup";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Admin admin, Model model, HttpSession session) {
        Admin existingAdmin = adminService.findAdminByEmail(admin.getEmail());
        if (existingAdmin != null && existingAdmin.getPassword().equals(admin.getPassword())) {
            session.setAttribute("loggedInAdmin", existingAdmin);
            return "redirect:/dashboardlogin";
        }
        model.addAttribute("error", "Invalid email or password");
        return "login";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute Admin admin, Model model) {
        Admin existingAdmin = adminService.findAdminByEmail(admin.getEmail());
        if (existingAdmin != null) {
            model.addAttribute("error", "Email already registered");
            logger.error("Signup failed, email already registered: {}", admin.getEmail());
            return "signup";
        }
        adminService.saveAdmin(admin);
        logger.info("Signup successful for email: {}", admin.getEmail());
        return "redirect:/login";
    }

    @GetMapping("/auth/dashboard")
    public String dashboard(Model model, @RequestHeader(value = "Authorization", required = false) String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            String email = jwtUtil.extractEmail(token);
            Admin loggedAdmin = adminService.findAdminByEmail(email);
            if (jwtUtil.validateToken(token, email)) {
                model.addAttribute("admin", loggedAdmin);
                logger.info("Access granted to dashboard for email: {}", email);
                return "dashboard";
            }
        }
        logger.error("Access denied to dashboard, invalid token");
        return "redirect:/login";
    }

    @GetMapping("/profile")
public String profile(Model model, @RequestHeader(value = "Authorization", required = false) String token) {
    if (token != null) {
        logger.info("Authorization header received: {}", token);
    } else {
        logger.warn("Authorization header not received");
    }

    if (token != null && token.startsWith("Bearer ")) {
        token = token.substring(7);
        String email = jwtUtil.extractEmail(token);
        Admin loggedAdmin = adminService.findAdminByEmail(email);
        if (jwtUtil.validateToken(token, email)) {
            model.addAttribute("admin", loggedAdmin);
            logger.info("Access granted to profile for email: {}", email);
            return "profile";
        }
    }
    logger.error("Access denied to profile, invalid token");
    return "redirect:/login";
}


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute Admin admin, HttpSession session, Model model) {
        Admin loggedAdmin = (Admin) session.getAttribute("loggedInAdmin");
        if (loggedAdmin != null) {
            loggedAdmin.setNama(admin.getNama());
            loggedAdmin.setEmail(admin.getEmail());
            adminService.saveAdmin(loggedAdmin);
            session.setAttribute("loggedInAdmin", loggedAdmin);  // Update session data
            model.addAttribute("admin", loggedAdmin);
            model.addAttribute("success", "Profile updated successfully");
            return "profile";
        }
        model.addAttribute("error", "Failed to update profile");
        return "profile";
    }

    @PostMapping("/profile/change-password")
    public String changePassword(@RequestParam String currentPassword, @RequestParam String newPassword,
                                 HttpSession session, Model model) {
        Admin loggedAdmin = (Admin) session.getAttribute("loggedInAdmin");
        if (loggedAdmin != null && loggedAdmin.getPassword().equals(currentPassword)) {
            loggedAdmin.setPassword(newPassword);
            adminService.saveAdmin(loggedAdmin);
            model.addAttribute("success", "Password changed successfully");
            return "profile";
        }
        model.addAttribute("error", "Incorrect current password");
        return "profile";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String email, @RequestParam String newPassword, Model model) {
        Admin admin = adminService.findAdminByEmail(email);
        if (admin != null) {
            admin.setPassword(newPassword);
            adminService.saveAdmin(admin);
            model.addAttribute("success", "Password reset successfully");
            return "forgot-password";
        } else {
            model.addAttribute("error", "Email not found");
            return "forgot-password";
        }
    }

    @GetMapping("/forgot-password")
    public String forgotPasswordForm() {
    return "forgot-password";
}

    @GetMapping("/awallogin")
    public String awallogin() {
    return "awallogin";
    }

    @GetMapping("/verifikasi")
    public String verifikasi() {
    return "verifikasi";
    }
}