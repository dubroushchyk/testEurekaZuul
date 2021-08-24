package my.task.customerservice.service;

import lombok.RequiredArgsConstructor;
import my.task.customerservice.dto.DTOAdmin;
import my.task.customerservice.dto.DTOUserListWithCounter;
import my.task.customerservice.dto.DTOUsersSearching;
import my.task.customerservice.model.Admin;
import my.task.customerservice.model.User;
import my.task.customerservice.repository.AdminRepository;
import my.task.customerservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DTOAdmin createNewAdmin(DTOAdmin dtoAdmin) {
        try {
            Admin admin = copyDTOUserToUserEntity(dtoAdmin);
            admin.setPassword(passwordEncoder.encode(dtoAdmin.getPassword()));
            adminRepository.save(admin);
            dtoAdmin.setPassword(null);
            LOGGER.info("Create new Admin with Username {}", admin.getUsername());
            return dtoAdmin;
        } catch (Exception exception) {
            LOGGER.error("Exception in createNewAdmin method");
            return null;
        }
    }

    @Override
    public DTOUserListWithCounter getAllSearchingUsers(String username, String name, String sex,
                                                       Integer age, String purpose, String additionalСharacteristics) {
        try {
            if (username != null) {
                DTOUserListWithCounter dtoUserListWithCounter = new DTOUserListWithCounter();
                List<DTOUsersSearching> dtoUsersSearchings = userRepository.getAllByUsername(username)
                        .stream()
                        .map(user -> copyUserEntityToDTOUserForSearching(user))
                        .collect(Collectors.toList());
                dtoUserListWithCounter.setUsersSearchingList(dtoUsersSearchings);
                dtoUserListWithCounter.setUsersCounter(dtoUsersSearchings.size());
                LOGGER.info("Found users with searching param Username");
                return dtoUserListWithCounter;
            }

            if (name != null) {
                DTOUserListWithCounter dtoUserListWithCounter = new DTOUserListWithCounter();
                List<DTOUsersSearching> dtoUsersSearchings = userRepository.getAllByName(name)
                        .stream()
                        .map(user -> copyUserEntityToDTOUserForSearching(user))
                        .collect(Collectors.toList());
                dtoUserListWithCounter.setUsersSearchingList(dtoUsersSearchings);
                dtoUserListWithCounter.setUsersCounter(dtoUsersSearchings.size());
                LOGGER.info("Found users with searching param Name");
                return dtoUserListWithCounter;
            }

            if (sex != null) {
                DTOUserListWithCounter dtoUserListWithCounter = new DTOUserListWithCounter();
                List<DTOUsersSearching> dtoUsersSearchings = userRepository.getAllBySex(sex)
                        .stream()
                        .map(user -> copyUserEntityToDTOUserForSearching(user))
                        .collect(Collectors.toList());
                dtoUserListWithCounter.setUsersSearchingList(dtoUsersSearchings);
                dtoUserListWithCounter.setUsersCounter(dtoUsersSearchings.size());
                LOGGER.info("Found users with searching param Sex");
                return dtoUserListWithCounter;
            }

            if (age != null) {
                DTOUserListWithCounter dtoUserListWithCounter = new DTOUserListWithCounter();
                List<DTOUsersSearching> dtoUsersSearchings = userRepository.getAllByAge(age)
                        .stream()
                        .map(user -> copyUserEntityToDTOUserForSearching(user))
                        .collect(Collectors.toList());
                dtoUserListWithCounter.setUsersSearchingList(dtoUsersSearchings);
                dtoUserListWithCounter.setUsersCounter(dtoUsersSearchings.size());
                LOGGER.info("Found users with searching param Age");
                return dtoUserListWithCounter;
            }

            if (purpose != null) {
                DTOUserListWithCounter dtoUserListWithCounter = new DTOUserListWithCounter();
                List<DTOUsersSearching> dtoUsersSearchings = userRepository.getAllByPurpose(purpose)
                        .stream()
                        .map(user -> copyUserEntityToDTOUserForSearching(user))
                        .collect(Collectors.toList());
                dtoUserListWithCounter.setUsersSearchingList(dtoUsersSearchings);
                dtoUserListWithCounter.setUsersCounter(dtoUsersSearchings.size());
                LOGGER.info("Found users with searching param Purpose");
                return dtoUserListWithCounter;
            }

            if (additionalСharacteristics != null) {
                DTOUserListWithCounter dtoUserListWithCounter = new DTOUserListWithCounter();
                List<DTOUsersSearching> dtoUsersSearchings = userRepository.getAllByAdditionalСharacteristics(additionalСharacteristics)
                        .stream()
                        .map(user -> copyUserEntityToDTOUserForSearching(user))
                        .collect(Collectors.toList());
                dtoUserListWithCounter.setUsersSearchingList(dtoUsersSearchings);
                dtoUserListWithCounter.setUsersCounter(dtoUsersSearchings.size());
                LOGGER.info("Found users with searching param AdditionalСharacteristics");
                return dtoUserListWithCounter;
            }
            LOGGER.info("Not found users in getAllSearchingUsers method");
            return null;
        } catch (Exception e) {
            LOGGER.error("Exception in getAllSearchingUsers method");
            return null;
        }
    }

    private Admin copyDTOUserToUserEntity(DTOAdmin dtoAdmin) {
        Admin admin = new Admin();
        BeanUtils.copyProperties(dtoAdmin, admin);
        return admin;
    }

    private DTOUsersSearching copyUserEntityToDTOUserForSearching(User user) {
        DTOUsersSearching dtoUserSearching = new DTOUsersSearching();
        dtoUserSearching.setUsername(user.getUsername());
        dtoUserSearching.setName(user.getName());
        dtoUserSearching.setSex(user.getSex());
        dtoUserSearching.setAge(user.getAge());
        dtoUserSearching.setPurpose(user.getPurpose());
        dtoUserSearching.setAdditionalСharacteristics(user.getAdditionalСharacteristics());
        return dtoUserSearching;
    }
}
