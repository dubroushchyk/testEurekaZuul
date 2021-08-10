package my.task.authservice.service;

import my.task.authservice.Enum.EnumCharacteristics;
import my.task.authservice.model.JwtResponse;
import my.task.authservice.model.User;
import java.util.List;

public interface UserService {

    User findByUsername(String username);

    JwtResponse authenticate (User user);

    User updateUserByUsername(String username, User user);

    User createNewUser(User user);

    List<User> getAllUsersByAdditionalСharacteristics(EnumCharacteristics characteristics);

}
