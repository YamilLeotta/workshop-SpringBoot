package grupo.artifact.controller;

import org.springframework.web.bind.annotation.*;

import grupo.artifact.model.User;
import grupo.artifact.model.dto.UserDTO;
import grupo.artifact.service.UserService;

import java.util.List;

import org.springframework.http.MediaType;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User saveUser(@RequestBody UserDTO userDTO) {
        return this.userService.saveUser(userDTO);
    }
    
    @GetMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO loginUser(@RequestParam String username, @RequestParam String password){
        return this.userService.loginUserWithCredentials(username, password);
    }

    @GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUsers(){
        return this.userService.getListUsersInBD();
    }

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO updateUser(@PathVariable Integer id, @RequestBody UserDTO userDTO){
        return this.userService.updateUserWithId(id, userDTO);
    }

}
