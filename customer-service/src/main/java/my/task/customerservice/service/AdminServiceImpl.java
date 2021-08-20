package my.task.customerservice.service;

import lombok.RequiredArgsConstructor;
import my.task.customerservice.dto.DTOAdmin;
import my.task.customerservice.dto.DTOUser;
import my.task.customerservice.model.Admin;
import my.task.customerservice.model.User;
import my.task.customerservice.repository.AdminRepository;
import my.task.customerservice.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DTOAdmin createNewAdmin(DTOAdmin dtoAdmin) {
        try {
            Admin admin = copyDTOUserToUserEntity(dtoAdmin);
            admin.setPassword(passwordEncoder.encode(dtoAdmin.getPassword()));
            adminRepository.save(admin);
            dtoAdmin.setPassword(null);
            return dtoAdmin;
        } catch (Exception exception) {
            return null;
        }
    }

    @Override
    public List<DTOUser> getAllSearchingUsers(String username, String name, String sex,
                                              Integer age, String purpose, String additionalСharacteristics) {
        try {
            if (username != null) {
                return userRepository.getAllByUsername(username)
                        .stream().map(user -> {
                            user.setPassword(null);
                            return copyUserEntityToDTOUser(user);
                        }).collect(Collectors.toList());
            }
            if (name != null) {
                return userRepository.getAllByName(name)
                        .stream().map(user -> {
                            user.setPassword(null);
                            return copyUserEntityToDTOUser(user);
                        }).collect(Collectors.toList());
            }
            if (sex != null) {
                return userRepository.getAllBySex(sex)
                        .stream().map(user -> {
                            user.setPassword(null);
                            return copyUserEntityToDTOUser(user);
                        }).collect(Collectors.toList());
            }
            if (age != null) {
                return userRepository.getAllByAge(age)
                        .stream().map(user -> {
                            user.setPassword(null);
                            return copyUserEntityToDTOUser(user);
                        }).collect(Collectors.toList());
            }
            if (purpose != null) {
                return userRepository.getAllByPurpose(purpose)
                        .stream().map(user -> {
                            user.setPassword(null);
                            return copyUserEntityToDTOUser(user);
                        }).collect(Collectors.toList());
            }
            if (additionalСharacteristics != null) {
                return userRepository.getAllByAdditionalСharacteristics(additionalСharacteristics)
                        .stream().map(user -> {
                            user.setPassword(null);
                            return copyUserEntityToDTOUser(user);
                        }).collect(Collectors.toList());
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

    private DTOAdmin copyAdminEntityToDTOAdmin(Admin admin) {
        DTOAdmin dtoAdmin = new DTOAdmin();
        BeanUtils.copyProperties(admin, dtoAdmin);
        return dtoAdmin;
    }

    private Admin copyDTOUserToUserEntity(DTOAdmin dtoAdmin) {
        Admin admin = new Admin();
        BeanUtils.copyProperties(dtoAdmin, admin);
        return admin;
    }
}
