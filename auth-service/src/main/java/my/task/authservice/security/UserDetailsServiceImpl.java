package my.task.authservice.security;

import my.task.authservice.dto.DTOUserOrAdmin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    RestTemplate restTemplate;

    public UserDetailsServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        DTOUserOrAdmin dtoUserOrAdmin = restTemplate
                .getForObject("http://localhost:8765/customer/requestLogin/" + username, DTOUserOrAdmin.class);
        if (dtoUserOrAdmin != null) {
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                    .commaSeparatedStringToAuthorityList("ROLE_" + dtoUserOrAdmin.getRole());
            return new org.springframework.security.core.userdetails.User(dtoUserOrAdmin.getUsername(),
                    dtoUserOrAdmin.getPassword(), grantedAuthorities);
        }
        throw new UsernameNotFoundException("Username: " + dtoUserOrAdmin.getUsername() + " not found");
    }
}
