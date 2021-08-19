package my.task.customerservice.controller;

import my.task.customerservice.dto.DTOUser;
import my.task.customerservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/user/detail")
    public ResponseEntity<DTOUser> getUserDetail(@RequestHeader(value = "Authorization") String token) {
        DTOUser DTOUser = userService.findByUsernameInToken(token);
        if (DTOUser != null) {
            return new ResponseEntity<>(DTOUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/user/update")
    public ResponseEntity<DTOUser> updateUserDetail(@RequestHeader("Authorization") String token,
                                                 @RequestBody DTOUser dtoUser) {
        DTOUser updatedDTOUser = userService.updateUserByUsername(token, dtoUser);
        if (updatedDTOUser != null) {
            return new ResponseEntity<>(updatedDTOUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/user/new")
    public ResponseEntity<DTOUser> createUser(@RequestBody DTOUser dtoUser) {
        DTOUser newDTOUser = userService.createNewUser(dtoUser);
        if (newDTOUser != null) {
            return new ResponseEntity<>(newDTOUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
