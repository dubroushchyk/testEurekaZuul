package my.task.authservice.service;

import lombok.RequiredArgsConstructor;
import my.task.authservice.Enum.EnumCharacteristics;
import my.task.authservice.model.JwtResponse;
import my.task.authservice.model.User;
import my.task.authservice.repository.UserRepository;
import my.task.authservice.security.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtResponse authenticate (User user) {
        final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(), user.getPassword()
                );
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(user.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);
        return new JwtResponse(token);
    }

    @Override
    public User findByUsername(String token) {
        try {
            return userRepository.findByUsername(jwtTokenUtil.getUsernameFromJwtTokenWithoutBearer(token));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User updateUserByUsername(String token, User userToUpdate) {
        try {
            String username = jwtTokenUtil.getUsernameFromJwtTokenWithoutBearer(token);
            User user = userRepository.findByUsername(username);
            user.setUsername(userToUpdate.getUsername());
            user.setName(userToUpdate.getName());
            user.setAge(userToUpdate.getAge());
            user.setSex(userToUpdate.getSex());
            user.setRole(userToUpdate.getRole());
            user.setPurpose(userToUpdate.getPurpose());
            user.setAdditionalСharacteristics(userToUpdate.getAdditionalСharacteristics());
            return userRepository.save(user);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User createNewUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } catch (Exception exception) {
            return null;
        }
    }

    @Override
    public List<User> getAllUsersByAdditionalСharacteristics(EnumCharacteristics characteristics) {
        try {
            return userRepository.getAllByAdditionalСharacteristics(characteristics);
        } catch (Exception e) {
            return null;
        }
    }
}
