package my.task.customerservice.service;

import lombok.RequiredArgsConstructor;
import my.task.customerservice.dto.DTOUser;
import my.task.customerservice.dto.DTOUserOrAdmin;
import my.task.customerservice.model.Admin;
import my.task.customerservice.model.User;
import my.task.customerservice.repository.AdminRepository;
import my.task.customerservice.repository.UserRepository;
import my.task.customerservice.util.JwtTokenDeconstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final JwtTokenDeconstructor jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public DTOUser findByUsernameInToken(String token) {
        try {
            User user = userRepository.findByUsername(jwtTokenUtil.getAuthenticatedUserFromHeader(token));
            return copyUserEntityToDTOUser(user);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public DTOUser updateUserByUsername(String token, DTOUser dtoUserToUpdate) {
        try {
            String username = jwtTokenUtil.getAuthenticatedUserFromHeader(token);
            User user = userRepository.findByUsername(username);
            user.setUsername(dtoUserToUpdate.getUsername());
            user.setName(dtoUserToUpdate.getName());
            user.setAge(dtoUserToUpdate.getAge());
            user.setSex(dtoUserToUpdate.getSex());
            user.setRole(dtoUserToUpdate.getRole());
            user.setPurpose(dtoUserToUpdate.getPurpose());
            user.setAdditionalСharacteristics(dtoUserToUpdate.getAdditionalСharacteristics());
            userRepository.save(user);
            return copyUserEntityToDTOUser(user);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public DTOUser createNewUser(DTOUser dtoUser) {
        try {
            User user = copyDTOUserToUserEntity(dtoUser);
            user.setPassword(passwordEncoder.encode(dtoUser.getPassword()));
            user.setRole("USER");
            userRepository.save(user);
            return copyUserEntityToDTOUser(user);
        } catch (Exception exception) {
            return null;
        }
    }

    @Override
    public DTOUserOrAdmin findCustomerByUsername(String username) {
        try {
            User user = userRepository.findCustomerByUsername(username);
            if (user != null) {
                return copyUserEntityToDTOUserOrAdmin(user);
            }
            Admin admin = adminRepository.findCustomerByUsername(username);
            if (admin != null) {
                return copyAdminEntityToDTOUserOrAdmin(admin);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
}

    private DTOUser copyUserEntityToDTOUser(User user) {
        DTOUser dtoUser = new DTOUser();
        BeanUtils.copyProperties(user, dtoUser);
        return dtoUser;
    }

    private User copyDTOUserToUserEntity(DTOUser dtoUser) {
        User user = new User();
        BeanUtils.copyProperties(dtoUser, user);
        return user;
    }

    private DTOUserOrAdmin copyUserEntityToDTOUserOrAdmin(User user) {
        DTOUserOrAdmin dtoUserOrAdmin = new DTOUserOrAdmin();
        BeanUtils.copyProperties(user, dtoUserOrAdmin);
        return dtoUserOrAdmin;
    }

    private DTOUserOrAdmin copyAdminEntityToDTOUserOrAdmin(Admin admin) {
        DTOUserOrAdmin dtoUserOrAdmin = new DTOUserOrAdmin();
        BeanUtils.copyProperties(admin, dtoUserOrAdmin);
        return dtoUserOrAdmin;
    }

}
