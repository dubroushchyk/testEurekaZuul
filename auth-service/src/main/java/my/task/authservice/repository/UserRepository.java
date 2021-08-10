package my.task.authservice.repository;

import my.task.authservice.Enum.EnumCharacteristics;
import my.task.authservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    List<User> getAllByAdditionalСharacteristics(EnumCharacteristics characteristics);
}