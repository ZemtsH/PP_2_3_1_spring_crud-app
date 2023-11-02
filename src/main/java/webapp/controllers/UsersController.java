package webapp.controllers;

import webapp.models.User;
import webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @GetMapping()
    public String showUser(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping()
    public String editUser(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    @PatchMapping()
    public String updateUser(@ModelAttribute("user") User user, @RequestParam("id") int id) {
        userService.updateUser(id, user);
        return "redirect:/";
    }

    @DeleteMapping()
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}