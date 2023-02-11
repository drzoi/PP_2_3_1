package web.controller;

import web.model.User;
import web.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String index(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("user", users);
        return "/index";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("user") User user) {
        return "/add";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/user_{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "/edit";
    }

    @PatchMapping("/user_{id}")
    public String update(@ModelAttribute("user") User user, BindingResult bindingResult) {
        userService.updateUser(user);
        return "redirect:/";
    }

    @DeleteMapping("/user_{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/";
    }
}