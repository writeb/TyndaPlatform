package project.TyndaPlatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.TyndaPlatform.service.AdminService;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/admin-page")
    public String adminPage(Model model, @RequestParam(name = "userKey", required = false, defaultValue = "") String key){
        model.addAttribute("users", adminService.searchUser(key));
        return "admin";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping(value = "/delete-user")
    public String deleteUser(@RequestParam(name = "userId") Long userId){
        adminService.deleteUser(userId);
        return "redirect:/admin-page?deletesuccess";
    }
}
