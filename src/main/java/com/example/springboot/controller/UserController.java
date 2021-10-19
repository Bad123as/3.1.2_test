package com.example.springboot.controller;

import com.example.springboot.model.Role;
import com.example.springboot.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.springboot.model.User;
import com.example.springboot.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String showAllUser(Model model, @ModelAttribute("users") User user) {
        model.addAttribute("users", userService.getAllUsers());
        return "redirect:/login";
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model, @ModelAttribute("users") User user) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin";
    }

    @GetMapping("/login")
    public String getLogin(Model model, @ModelAttribute("users") User user) {
        return "login";
    }

    @GetMapping("/logout")
    public String getLogout(Model model, @ModelAttribute("users") User user) {
        return "redirect:/login";
    }

    @GetMapping("/user")
    public String getUserPage(Model model, @ModelAttribute("users") User user) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());
        return "user";
    }

    @GetMapping("/new")
    public String addNewUser(Model model, @ModelAttribute("users") User user) {
        model.addAttribute("users", new User());
        model.addAttribute("user", user);
        return "info";
    }

    @GetMapping("/updateUser")
    public String updateUser(Model model, @ModelAttribute("users") User user) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", user);
        return "updateUser";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@ModelAttribute("users") User user, Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "deleteUser";
    }

    @PostMapping(value = "/admin/save-user")
    public String addNewUser(@ModelAttribute User user, @RequestParam(value = "checkBoxRoles") String[] checkBoxRoles) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : checkBoxRoles) {
            roleSet.add(roleService.getRoleByName(role));
        }
        user.setRoles(roleSet);
        userService.addUser(user);

        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(@ModelAttribute("users") User user, Model model, @PathVariable("id") long id) {
        model.addAttribute("users", userService.getUserById(id));
        model.addAttribute("user", user);
        return "update";
    }

    @PostMapping("/{id}")
    public String updateUser(@ModelAttribute User user, @RequestParam(value = "checkBoxRoles") String[] checkBoxRoles) {
        Set<Role> roleSet = new HashSet<>();
        for (String roles : checkBoxRoles) {
            roleSet.add(roleService.getRoleByName(roles));
        }
        user.setRoles(roleSet);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/{id}/delete")
    public String delete(@ModelAttribute("id") long id, @ModelAttribute("users") User user) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
