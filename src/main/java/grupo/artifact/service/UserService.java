package grupo.artifact.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import grupo.artifact.exception.custom.EmptyElementException;
import grupo.artifact.exception.custom.NotCreatedException;
import grupo.artifact.exception.custom.UnauthorizedException;
import grupo.artifact.exception.custom.NotFoundException;
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

    public UserDTO updateUserWithId(Integer id, UserDTO userDTO){
        if (this.checkUserDTO(userDTO, Boolean.FALSE)) {
            User modifying = this.userRepository.findById(id)
                .orElseThrow(
                    () -> new NotFoundException("User not found")
                );
            
            modifying.setName(userDTO.getName());
            modifying.setUsername(userDTO.getUsername());
            modifying.setPassword(userDTO.getPassword());

            return this.userRepository.save(modifying).toDTO();
        }

        throw new NotFoundException("User not found");
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

    public List<UserDTO> getListUsersInBD() {
        return this.userRepository
            .findAll()
            .stream()
            .map(User::toDTO)
            .collect(Collectors.toList());
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
