package project.TyndaPlatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.TyndaPlatform.dto.UserDTO;
import project.TyndaPlatform.model.User;
import project.TyndaPlatform.service.AdminService;
import project.TyndaPlatform.service.ArtistService;
import project.TyndaPlatform.service.MusicService;

@Controller
public class  UserController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private MusicService musicService;

    @Autowired
    private ArtistService artistService;

    @GetMapping(value = "/login")
    public String loginPage(){
        return "login";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/profile")
    public String profilePage(){
        return "profile";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping(value = "/subscription")
    public String subscriptionPage(){
        return "subscription";
    }

    @PostMapping(value = "/assign-vip")
    public String assignVip(@RequestParam(name = "userId") Long userId, @RequestParam(name = "roleId") Long roleId){
        adminService.changeUserRole(userId, roleId);
        return "redirect:/subscription?success";
    }

    @GetMapping(value = "/403-error")
    public String accessDenied(){
        return "403-page";
    }

    @GetMapping(value = "/sign-up")
    public String registerPage(){
        return "sign-up";
    }

    @PostMapping(value = "/sign-up")
    public String signUp(@RequestParam(name = "user_email") String email,
                         @RequestParam(name = "user_fullName") String fullName,
                         @RequestParam(name = "user_password") String password,
                         @RequestParam(name = "user_repeat_password") String repeatPassword){

        if (password.equals(repeatPassword)){
            UserDTO user = new UserDTO();
            user.setEmail(email);
            user.setFullName(fullName);
            user.setPassword(password);
            UserDTO newUser = adminService.addUser(user);
            if (newUser!=null){
                return "redirect:/login";
            } else {
                return "redirect:/sign-up?emailerror";
            }
        } else {
            return "redirect:/sign-up?passworderror";
        }
    }

    @GetMapping(value = "/update-password")
    public String updatePasswordPage(){
        return "update-password";
    }

    @PostMapping(value = "/to-update-password")
    public String toUpdatePassword(@RequestParam(name = "user_old_password") String user_old_password,
                                   @RequestParam(name = "user_new_password") String user_new_password,
                                   @RequestParam(name = "user_repeat_new_password") String user_repeat_new_password){
        if (user_new_password.equals(user_repeat_new_password)){

            UserDTO user = adminService.updatePassword(user_new_password, user_old_password);

            if (user!=null){
                return "redirect:/profile";
            } else {
                return "redirect:/update-password?oldpassworderror";
            }

        } else {
            return "redirect:/update-password?passwordmismatch";
        }
    }

    @GetMapping(value = "/library")
    public String libraryPage(Model model){
        model.addAttribute("musics", musicService.getMusicsByUserId(adminService.getCurrentSessionUser().getId()));
        model.addAttribute("artists", artistService.getArtistsByUserId(adminService.getCurrentSessionUser().getId()));
        return "library";
    }

}
