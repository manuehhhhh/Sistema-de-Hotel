package ingenieria.de.software._0.albergo.services.auth;

import ingenieria.de.software._0.albergo.dto.SignupRequest;
import ingenieria.de.software._0.albergo.dto.UserDto;

public interface AuthService {
    UserDto createUser(SignupRequest signupRequest);
}
