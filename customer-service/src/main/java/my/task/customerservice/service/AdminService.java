package my.task.customerservice.service;

import my.task.customerservice.dto.DTOAdmin;
import my.task.customerservice.dto.DTOUserListWithCounter;

public interface AdminService {

    DTOAdmin createNewAdmin(DTOAdmin dtoAdmin);

    DTOUserListWithCounter getAllSearchingUsers(String Username, String name, String sex,
                                                Integer age, String purpose,
                                                String additional–°haracteristics,
                                                long offset, long size);

}
