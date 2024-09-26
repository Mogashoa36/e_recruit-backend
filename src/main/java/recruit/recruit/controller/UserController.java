package recruit.recruit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recruit.recruit.dao.UserDao;
import recruit.recruit.entity.User;
import recruit.recruit.service.UserService;

import java.util.Optional;


@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
            @RequestParam String fullName,
            @RequestParam String password,
            @RequestParam String role,
            @RequestParam String email) {

        try {
            // Create a User object from the request parameters
            User user = new User(fullName, email, role, password);

            // Register the user
            userService.registerUser(user);

            return ResponseEntity.ok("User registered successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> optionalUser = userDao.findByEmail(email);

        if (optionalUser.isPresent()) {
            // If the user is found, return it wrapped in ResponseEntity
            return ResponseEntity.ok(optionalUser.get());
        } else {
            // If the user is not found, return a NOT FOUND response
            return ResponseEntity.notFound().build();
        }
    }
}
