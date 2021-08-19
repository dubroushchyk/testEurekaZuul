package my.task.customerservice.controller;

import my.task.customerservice.dto.DTOAdmin;
import my.task.customerservice.dto.DTOUser;
import my.task.customerservice.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/admin/new")
    public ResponseEntity<DTOAdmin> createNewAdmin(@RequestBody DTOAdmin dtoAdmin) {
        DTOAdmin newDTOAdmin = adminService.createNewAdmin(dtoAdmin);
        if (newDTOAdmin != null) {
            return new ResponseEntity<>(newDTOAdmin, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/users/search/characteristics/{characteristics}")
    public ResponseEntity<List<DTOUser>> getAllUsersByAdditionalCharacteristics(
            @PathVariable("characteristics") String characteristics) {
        List<DTOUser> listUsers = adminService.getAllUsersByAdditionalСharacteristics(characteristics);
        if (!listUsers.isEmpty()) {
            return new ResponseEntity<>(listUsers, HttpStatus.OK);
        }
        return new ResponseEntity<>(listUsers, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/users/search/purpose/{purpose}")
    public ResponseEntity<List<DTOUser>> getAllUsersByPurpose(
            @PathVariable("purpose") String purpose) {
        List<DTOUser> listUsers = adminService.getAllUsersByPurpose(purpose);
        if (!listUsers.isEmpty()) {
            return new ResponseEntity<>(listUsers, HttpStatus.OK);
        }
        return new ResponseEntity<>(listUsers, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/users/search/age/{age}")
    public ResponseEntity<List<DTOUser>> getAllUsersByAge(
            @PathVariable("age") Integer age) {
        List<DTOUser> listUsers = adminService.getAllUsersByAge(age);
        if (!listUsers.isEmpty()) {
            return new ResponseEntity<>(listUsers, HttpStatus.OK);
        }
        return new ResponseEntity<>(listUsers, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/users/search/sex/{sex}")
    public ResponseEntity<List<DTOUser>> getAllUsersBySex(
            @PathVariable("sex") String sex) {
        List<DTOUser> listUsers = adminService.getAllUsersBySex(sex);
        if (!listUsers.isEmpty()) {
            return new ResponseEntity<>(listUsers, HttpStatus.OK);
        }
        return new ResponseEntity<>(listUsers, HttpStatus.NO_CONTENT);
    }
}
