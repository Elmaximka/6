package web.controller;

import com.sun.istack.internal.NotNull;
import hiber.config.AppConfig;
import hiber.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import hiber.service.UserService;

import javax.persistence.NoResultException;
import java.util.List;


@Controller
@RequestMapping("/")
public class UserController {

    private AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

    private UserService userService = context.getBean(UserService.class);

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String printUsers(ModelMap model) {
        List<User> users = userService.listUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @RequestMapping(value = "users", method = RequestMethod.POST)
    public String addUser(@RequestParam String name, @RequestParam String lastName,
                          @RequestParam String email, @RequestParam Long age) {
        try {
            userService.getUserByName(name);
        } catch (NoResultException e) {
            userService.add(new User(name, lastName, email, age));
        }
        return "redirect:/users";
    }

    @RequestMapping(value = "users/delete", method = RequestMethod.POST)
    public String deleteUser(@RequestParam @NotNull String name) {
        userService.deleteUser(name);
        return "redirect:/users";
    }

    @RequestMapping(value = "users/change", method = RequestMethod.POST)
    public String changeUser(@RequestParam String name, @RequestParam String lastName,
                             @RequestParam String email, @RequestParam Long age) {
        try {
            User user = userService.getUserByName(name);
            if (!lastName.isEmpty()) {
                user.setLastName(lastName);
            }
            if (!email.isEmpty()) {
                user.setEmail(email);
            }
            if (age != null) {
                user.setAge(age);
            }
            userService.deleteUser(name);
            userService.add(user);
        } catch (NoResultException e) {
            return "redirect:/users";
        }
        return "redirect:/users";
    }

}