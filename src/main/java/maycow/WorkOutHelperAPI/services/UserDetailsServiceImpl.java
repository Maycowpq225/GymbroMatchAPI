package maycow.WorkOutHelperAPI.services;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import maycow.WorkOutHelperAPI.models.User;
import maycow.WorkOutHelperAPI.repositories.UserRepository;
import maycow.WorkOutHelperAPI.security.UserSpringSecurity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));
        return new UserSpringSecurity(user.getId(), user.getEmail(), user.getPassword(), user.getEmail(), user.getProfiles());
    }
}