package xyz.digivice.drivemerge.auth.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import xyz.digivice.drivemerge.auth.dto.UserDTO;
import xyz.digivice.drivemerge.auth.entity.User;
import xyz.digivice.drivemerge.auth.service.UserService;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistration(Model model){
        model.addAttribute("user", new UserDTO());
        return "auth/register";
    }

    @PostMapping("/register")
    public String doRegistration(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult result, Model model){
        User user = userService.findByEmail(userDTO.getEmail());
        if(user != null){
            result.rejectValue("email", null, "An account exists with this email");
        }


        if(result.hasErrors()){
            model.addAttribute("user", userDTO);
            return "auth/register";
        }

        userService.saveUser(userDTO);
        return "redirect:/register?success";
    }
}
