package my.task.customerservice.service;

import lombok.RequiredArgsConstructor;
import my.task.customerservice.dto.DTOUser;
import my.task.customerservice.dto.DTOCustomerForAuth;
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
            if (user != null) {
                user.setPassword(null);
                return copyUserEntityToDTOUser(user);
            }
            return null;
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
            user.setPurpose(dtoUserToUpdate.getPurpose());
            user.setAdditionalСharacteristics(dtoUserToUpdate.getAdditionalСharacteristics());
            userRepository.save(user);
            user.setPassword(null);
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
            userRepository.save(user);
            user.setPassword(null);
            return copyUserEntityToDTOUser(user);
        } catch (Exception exception) {
            return null;
        }
    }

    @Override
    public DTOCustomerForAuth findCustomerByUsername(String username) {
        try {
            User user = userRepository.findCustomerByUsername(username);
            if (user != null) {
                DTOCustomerForAuth dtoCustomerForAuth = new DTOCustomerForAuth();
                dtoCustomerForAuth.setUsername(user.getUsername());
                dtoCustomerForAuth.setPassword(user.getPassword());
                dtoCustomerForAuth.setRole("USER");
                return dtoCustomerForAuth;
            }
            Admin admin = adminRepository.findCustomerByUsername(username);
            if (admin != null) {
                DTOCustomerForAuth dtoCustomerForAuth = new DTOCustomerForAuth();
                dtoCustomerForAuth.setUsername(admin.getUsername());
                dtoCustomerForAuth.setPassword(admin.getPassword());
                dtoCustomerForAuth.setRole("ADMIN");
                return dtoCustomerForAuth;
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
}
