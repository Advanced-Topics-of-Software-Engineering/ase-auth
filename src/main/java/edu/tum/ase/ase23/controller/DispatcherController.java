package edu.tum.ase.ase23.controller;

import edu.tum.ase.ase23.model.User;
import edu.tum.ase.ase23.payload.request.EditUserRequest;
import edu.tum.ase.ase23.payload.response.MessageResponse;
import edu.tum.ase.ase23.payload.response.UserResponse;
import edu.tum.ase.ase23.repository.UserRepository;
import edu.tum.ase.ase23.service.UserService;
import edu.tum.ase.ase23.util.UserEmailNotFoundException;
import edu.tum.ase.ase23.util.UserIdNotFoundException;
import edu.tum.ase.ase23.util.UserRFIDTokenNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dispatcher")
@PreAuthorize("hasRole('DISPATCHER')")
public class DispatcherController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/edit_user/{userid}")
    public ResponseEntity<?> EditUser(@PathVariable String userid, @Valid @RequestBody EditUserRequest editUserRequest) throws UsernameNotFoundException {
        User updatedUser = userService.getUserById(userid).orElseThrow(() -> new UsernameNotFoundException("User cannot find by id " + userid));

        Optional<User> user = userService.getUserByUsername(editUserRequest.getUsername());
        if (user.isPresent() && !user.get().getUsername().equals(updatedUser.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        user = userService.getUserByEmail(editUserRequest.getEmail());
        if (user.isPresent() && !user.get().getUsername().equals(updatedUser.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already taken!"));
        }
        Boolean boolStatus = true;
        if (editUserRequest.getEmail() != null && !editUserRequest.getEmail().equals(updatedUser.getEmail())) {
            updatedUser.setEmail(editUserRequest.getEmail());
            boolStatus = false;
        }
        if (editUserRequest.getPassword() != null) {
            updatedUser.setPassword(encoder.encode(editUserRequest.getPassword()));
            boolStatus = false;
        }
        if (editUserRequest.getUsername() != null && !editUserRequest.getUsername().equals(updatedUser.getUsername())) {
            updatedUser.setUsername(editUserRequest.getUsername());
            boolStatus = false;
        }
        if (!boolStatus)
        {
            userRepository.save(updatedUser);
            return ResponseEntity.ok(new MessageResponse("Success: User has edited!"));
        }
        else {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: No changes made!"));
        }

    }

    @GetMapping("/get_all_customers")
    public ResponseEntity<?> GetAllCustomers() {
        List<User> customers = userService.getCustomers();

        if (customers.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: There is not any customer registered!"));
        } else {
            return ResponseEntity.ok().body(customers.stream().map(
                    customer -> new UserResponse(
                            customer.getId(),
                            customer.getUsername(),
                            customer.getEmail(),
                            customer.getRFIDToken(),
                            customer.getRoles().iterator().next().getRoleEnum().name()
                    )
            ).collect(Collectors.toList()));
        }
    }

    @GetMapping("/get_all_deliverers")
    public ResponseEntity<?> GetAllDeliverers() {
        List<User> deliverers = userService.getDeliverers();

        if (deliverers.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: There is not any deliverer registered!"));
        } else {
            return ResponseEntity.ok().body(deliverers.stream().map(
                            deliverer -> new UserResponse(
                                    deliverer.getId(),
                                    deliverer.getUsername(),
                                    deliverer.getEmail(),
                                    deliverer.getRFIDToken(),
                                    deliverer.getRoles().iterator().next().getRoleEnum().name()
                            )
                    )
            );
        }
    }

    @GetMapping("/get_all_dispatchers")
    public ResponseEntity<?> GetAllDispatchers() {
        List<User> dispatchers = userService.getDispatcher();

        if (dispatchers.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: There is not any dispatcher registered!"));
        } else {
            return ResponseEntity.ok().body(dispatchers.stream().map(
                            dispatcher -> new UserResponse(
                                    dispatcher.getId(),
                                    dispatcher.getUsername(),
                                    dispatcher.getEmail(),
                                    dispatcher.getRFIDToken(),
                                    dispatcher.getRoles().iterator().next().getRoleEnum().name()
                            )
                    )
            );
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> GetUserByUsername(@PathVariable String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User cannot find by username " + username));
        return ResponseEntity.ok(user);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> GetUserByEmail(@PathVariable String email) throws UserEmailNotFoundException {
        User user = userService.getUserByEmail(email).orElseThrow(() -> new UserEmailNotFoundException("User cannot find by email " + email));
        return ResponseEntity.ok(user);
    }

    @GetMapping("/userid/{userid}")
    public ResponseEntity<?> GetUserByUserid(@PathVariable String userid) throws UserIdNotFoundException {
        User user = userService.getUserById(userid).orElseThrow(() -> new UserIdNotFoundException("User cannot find by userid" + userid));
        return ResponseEntity.ok(user);
    }

    @GetMapping("/rfid/{rfidtoken}")
    public ResponseEntity<?> GetUserByRFIDToken(@PathVariable String rfidtoken) throws UserRFIDTokenNotFoundException {
        User user = userService.getUserByRFIDToken(rfidtoken).orElseThrow(() -> new UserRFIDTokenNotFoundException("User cannot find by rfid token" + rfidtoken));
        return ResponseEntity.ok(user);
    }
}
