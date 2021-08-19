package my.task.customerservice.service;

import my.task.customerservice.dto.DTOUser;
import my.task.customerservice.dto.DTOUserOrAdmin;

public interface UserService {

    DTOUser findByUsernameInToken(String username);

    DTOUserOrAdmin findCustomerByUsername (String username);

    DTOUser updateUserByUsername(String username, DTOUser dtoUser);

    DTOUser createNewUser(DTOUser dtoUser);

}
