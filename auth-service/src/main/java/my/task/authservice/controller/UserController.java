package my.task.authservice.controller;

import my.task.authservice.Enum.EnumCharacteristics;
import my.task.authservice.model.JwtResponse;
import my.task.authservice.model.User;
import my.task.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticate(@RequestBody User user) {
        JwtResponse jwtResponse = userService.authenticate(user);
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/user/detail")
    public ResponseEntity<User> getUserDetail(@RequestHeader("Authorization") String token) {
        User user = userService.findByUsername(token);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/user/update")
    public ResponseEntity<User> updateUserDetail(@RequestHeader("Authorization") String token,
                                                 @RequestBody User user) {
        User updatedUser = userService.updateUserByUsername(token, user);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/user/new")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.createNewUser(user);
        if (newUser != null) {
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/users/search/{characteristics}")
    public ResponseEntity<List<User>> getAllUsersByAdditionalCharacteristics(
             @PathVariable("characteristics") EnumCharacteristics characteristics) {
        List<User> listUsers = userService.getAllUsersByAdditionalСharacteristics(characteristics);
        if (!listUsers.isEmpty()) {
            return new ResponseEntity<>(listUsers, HttpStatus.OK);
        }
        return new ResponseEntity<>(listUsers, HttpStatus.NO_CONTENT);
    }
}