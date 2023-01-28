package br.com.flare.controller;

import br.com.flare.controller.dto.UserDto;
import br.com.flare.controller.dto.UserForm;
import br.com.flare.model.User;
import br.com.flare.repositories.FeedRepository;
import br.com.flare.repositories.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FeedRepository feedRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDto userDto){

        User user = new User(userDto.getName(), userDto.getEmail());

        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<?> listAllUsers(){

        List<User> users = userRepository.findAll();

        List<UserDto> userDtos = new ArrayList<>();
        users.forEach(user -> userDtos.add(user.toDTO()));

        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("/list/{name}")
    public ResponseEntity<?> listUserInformation(@PathVariable String name){

        Optional<User> byName = userRepository.findByName(name);

        if (byName.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");

        List<String> feeds = feedRepository.findByUser(name);
        return ResponseEntity.ok(new UserForm(byName.get().getName(), byName.get().getEmail(), feeds));

    }

    @DeleteMapping("/remove/{name}")
    public ResponseEntity<?> removeUser(@PathVariable @NotEmpty String name){

        Optional<User> byName = userRepository.findByName(name);
        if (byName.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");

        userRepository.delete(byName.get());
        return ResponseEntity.noContent().build();

    }

}
