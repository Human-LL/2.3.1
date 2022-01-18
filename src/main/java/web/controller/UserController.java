    package web.controller;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import web.model.User;
    import web.service.UserService;

    import javax.servlet.http.HttpServletRequest;
    import java.util.List;

    @RestController
    @RequestMapping("/users")
    public class UserController {
        @Autowired
        private UserService userService;

        @GetMapping("/all")
        public ResponseEntity<List<User>> getAllUsers() {
            List<User> users = userService.getAllUsers();
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        }

        @PostMapping("/add")
        public ResponseEntity<User> addUser(@RequestBody User user) {
            User newUser = userService.addUser(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }

        @GetMapping("/by-id")
        public ResponseEntity getUserById(HttpServletRequest request) {
            String idStr = request.getParameter("id");
            if (idStr == null) {
                return ResponseEntity.badRequest().build();
            }
            Long id = Long.valueOf(idStr);

            User user = userService.getUserById(id);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(user);
        }

        @PostMapping("/update")
        public ResponseEntity<User> updateUser(@RequestBody User updatedUser) {
            User user = userService.updateUser(updatedUser.getId(), updatedUser);
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        @GetMapping("/delete")
        public ResponseEntity<Void> deleteUser(@RequestParam Long id) {
            if (id != null) {
                userService.deleteUser(id);
                return ResponseEntity.ok().build();
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }