package my.task.customerservice.controller;

import my.task.customerservice.dto.DTOAdmin;
import my.task.customerservice.dto.DTOUser;
import my.task.customerservice.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;

@RestController
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/admin/new")
    public ResponseEntity<DTOAdmin> createNewAdmin(@RequestBody DTOAdmin dtoAdmin,
                                                   @RequestHeader("X-Secret-Key") String key) {
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String text = LocalDate.now().format(formatters);
        byte[] decodedBytes = Base64.getDecoder().decode(key);
        String decodedKey = new String(decodedBytes);
        if (!text.equals(decodedKey)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        DTOAdmin newDTOAdmin = adminService.createNewAdmin(dtoAdmin);
        if (newDTOAdmin != null) {
            return new ResponseEntity<>(newDTOAdmin, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/users/search")
    public ResponseEntity<List<DTOUser>> getAllUsersBySex(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String sex,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String purpose,
            @RequestParam(required = false) String additionalСharacteristics) {
        List<DTOUser> listUsers = adminService.getAllSearchingUsers(username, name, sex, age,
                                                                    purpose, additionalСharacteristics);
        if (!listUsers.isEmpty()) {
            return new ResponseEntity<>(listUsers, HttpStatus.OK);
        }
        return new ResponseEntity<>(listUsers, HttpStatus.NO_CONTENT);
    }
}
