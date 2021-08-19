package my.task.customerservice.service;

import my.task.customerservice.dto.DTOAdmin;
import my.task.customerservice.dto.DTOUser;
import java.util.List;

public interface AdminService {

    DTOAdmin createNewAdmin(DTOAdmin dtoAdmin);

    List<DTOUser> getAllUsersByAdditionalСharacteristics(String characteristics);

    List<DTOUser> getAllUsersByPurpose(String purpose);

    List<DTOUser> getAllUsersByAge(Integer age);

    List<DTOUser> getAllUsersBySex(String sex);

}
