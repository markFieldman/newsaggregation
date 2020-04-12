package news.top.newsaggregation.security;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import news.top.newsaggregation.models.User;
import news.top.newsaggregation.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email : " + email)
                );
        return Principal.create(user);
    }

    public UserDetails loadUserById(Long id) {
        log.info(String.valueOf(userRepository.findById(id)));
        User user = userRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("User not found with id : " + id)
        );
        return Principal.create(user);
    }
}