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
            admin.setRole("ADMIN");
            adminRepository.save(admin);
            return copyAdminEntityToDTOAdmin(admin);
        } catch (Exception exception) {
            return null;
        }
    }

    @Override
    public List<DTOUser> getAllUsersByAdditionalСharacteristics(String characteristics) {
        try {
            return userRepository.getAllByAdditionalСharacteristics(characteristics)
                    .stream().map(this::copyUserEntityToDTOUser).collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<DTOUser> getAllUsersByPurpose(String purpose) {
        try {
            return userRepository.getAllByPurpose(purpose)
                    .stream().map(this::copyUserEntityToDTOUser).collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<DTOUser> getAllUsersByAge(Integer age) {
        try {
            return userRepository.getAllByAge(age)
                    .stream().map(this::copyUserEntityToDTOUser).collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<DTOUser> getAllUsersBySex(String sex) {
        try {
            return userRepository.getAllBySex(sex)
                    .stream().map(this::copyUserEntityToDTOUser).collect(Collectors.toList());
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
