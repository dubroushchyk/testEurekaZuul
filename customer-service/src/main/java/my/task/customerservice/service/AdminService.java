package my.task.customerservice.service;

import my.task.customerservice.dto.DTOAdmin;
import my.task.customerservice.dto.DTOUser;
import java.util.List;

public interface AdminService {

    DTOAdmin createNewAdmin(DTOAdmin dtoAdmin);

    List<DTOUser> getAllSearchingUsers(String username, String name, String sex,
                                       Integer age, String purpose, String additionalСharacteristics);

}
