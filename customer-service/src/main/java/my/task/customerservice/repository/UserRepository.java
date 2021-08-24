package my.task.customerservice.repository;

import my.task.customerservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findCustomerByUsername(String username);

    List<User> getAllByUsername(String name);

    List<User> getAllByName(String name);

    List<User> getAllBySex(String sex);

    List<User> getAllByAge(Integer age);

    List<User> getAllByPurpose(String purpose);

    List<User> getAllByAdditionalСharacteristics(String characteristics);

}