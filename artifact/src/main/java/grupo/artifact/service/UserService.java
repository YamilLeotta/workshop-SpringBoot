package grupo.artifact.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import grupo.artifact.exception.custom.EmptyElementException;
import grupo.artifact.exception.custom.NotCreatedException;
import grupo.artifact.exception.custom.UnauthorizedException;
import grupo.artifact.model.User;
import grupo.artifact.model.dto.UserDTO;
import grupo.artifact.repository.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User saveUser(UserDTO userDTO){
        if (checkUserDTO(userDTO, Boolean.FALSE)){
            return this.userRepository.save(new User(userDTO));
        }

        throw new NotCreatedException("User hasn't been created");
    }

    public UserDTO loginUserWithCredentials(String username, String password){
        if (
            this.checkUserDTO(
                UserDTO.builder()
                    .username(username)
                    .password(password)
                    .build()
                , Boolean.TRUE)
        ) {
            return this.userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(
                    () -> new UnauthorizedException("Invalid credentials")
                ).toDTO();
        }

        throw new UnauthorizedException("Invalid credentials");
    }

    private Boolean checkUserDTO(UserDTO userDTO, Boolean isForLogin){
        if (!isForLogin){
            if(StringUtils.isEmpty(userDTO.getName())){
                throw new EmptyElementException("Name is empty");
            }
        }

        if(StringUtils.isEmpty(userDTO.getUsername())){
            throw new EmptyElementException("Username is empty");
        }
        if(StringUtils.isEmpty(userDTO.getPassword())){
            throw new EmptyElementException("Password is empty");
        }

        return Boolean.TRUE;
    }

}
