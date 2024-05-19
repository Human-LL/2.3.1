    package web.controller;



    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;
    import web.model.User;
    import web.service.UserService;

    import javax.validation.Valid;

    @Controller
    @RequestMapping("/users")
    public class UserController {

        private final UserService userService;

        @Autowired
        public UserController(UserService userService) {
            this.userService = userService;
        }

        @GetMapping
        public String allUsers(Model model) {
            model.addAttribute("users", userService.allUser());
            return "user/user-list";
        }

        @GetMapping("/new")
        public String createUserForm(Model model) {
            model.addAttribute("user", new User());
            return "user/user-create";
        }

        @PostMapping
        public String createUser(@ModelAttribute("user") @Valid User user) {
            userService.saveUser(user);
            return "redirect:/users";
        }

        @GetMapping("/edit")
        public String editUserForm(@RequestParam("id") Long id, Model model) {
            userService.findById(id).ifPresent(model::addAttribute);
            return "user/edit-user";
        }

        @PostMapping("/edit")
        public String editUser(@ModelAttribute("user") @Valid User user) {
            userService.updateUser(user);
            return "redirect:/users";
        }

        @PostMapping("/delete")
        public String deleteUser(@RequestParam("id") Long id) {
            userService.deleteById(id);
            return "redirect:/users";
        }
    }