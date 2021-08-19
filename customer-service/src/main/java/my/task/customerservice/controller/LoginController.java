package my.task.customerservice.controller;

import my.task.customerservice.dto.DTOUserOrAdmin;
import my.task.customerservice.service.AdminService;
import my.task.customerservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private UserService userService;
    private AdminService adminService;

    public LoginController(UserService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    @GetMapping("/requestLogin/{username}")
    public ResponseEntity<DTOUserOrAdmin> getUsernameAndPasswordAndRoleToAuthService
            (@PathVariable(value = "username") String username) {
        DTOUserOrAdmin dtoUserOrAdmin = userService.findCustomerByUsername(username);
        if (dtoUserOrAdmin != null) {
            return new ResponseEntity<>(dtoUserOrAdmin, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
