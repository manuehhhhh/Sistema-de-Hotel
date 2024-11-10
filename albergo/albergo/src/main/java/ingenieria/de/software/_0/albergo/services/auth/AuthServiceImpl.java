package ingenieria.de.software._0.albergo.services.auth;

import ingenieria.de.software._0.albergo.dto.SignupRequest;
import ingenieria.de.software._0.albergo.dto.UserDto;
import ingenieria.de.software._0.albergo.entidad.User;
import ingenieria.de.software._0.albergo.enums.UserRole;
import ingenieria.de.software._0.albergo.repo.UserRepo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityExistsException;

import lombok.RequiredArgsConstructor;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepo userRepository;

    @PostConstruct
    public void createAnAdminAccount() {
        Optional<User> adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
        if (adminAccount.isEmpty()) {
            User user = new User();
            user.setEmail("admin@hotel.com");
            user.setName("Admin");
            user.setUserRole(UserRole.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
            System.out.println("Cuenta de administrador creada con éxito");
        } else {
            throw new IllegalStateException("Ya existe la cuenta de administrador");
        }
    }

    @Override
    public UserDto createUser(SignupRequest signupRequest) {
        if (userRepository.findFirstByEmail(signupRequest.getEmail()).isPresent()) {
            throw new EntityExistsException("Este correo ya está asociado a una cuenta");
        }
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setUserRole(UserRole.USER);
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        User createdUser = userRepository.save(user);
        return createdUser.getUserDto();
    }
}
