package edu.tum.ase.ase23.controller;

import edu.tum.ase.ase23.model.User;
import edu.tum.ase.ase23.payload.request.ChangeEmailRequest;
import edu.tum.ase.ase23.payload.request.ChangePasswordRequest;
import edu.tum.ase.ase23.payload.response.MessageResponse;
import edu.tum.ase.ase23.repository.UserRepository;
import edu.tum.ase.ase23.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('CUSTOMER') and hasRole('DELIVERER') and hasRole('DISPATCHER')")
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/change_password/{username}")
    @PreAuthorize("#username == authentication.name")
    public ResponseEntity<?> ChangePassword(@PathVariable String username, @Valid @RequestBody ChangePasswordRequest changePasswordRequest) throws UsernameNotFoundException {

        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getNewPasswordRepeat())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: New password and New Password Repeat must be same!"));
        }

        User user =  userService.getUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User cannot find by username " + username));

        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, changePasswordRequest.getOldPassword()));

        } catch (BadCredentialsException e) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Old password does not match with current password!"));
        }

        user.setPassword(encoder.encode(changePasswordRequest.getNewPassword()));

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Success: Password has changed!"));
    }

    @PostMapping("/change_email/{username}")
    @PreAuthorize("#username == authentication.name")
    public ResponseEntity<?> ChangeEmail(@PathVariable String username, @Valid @RequestBody ChangeEmailRequest changeEmailRequest) throws UsernameNotFoundException {

        if (!changeEmailRequest.getNewEmail().equals(changeEmailRequest.getNewEmailRepeat())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: New e-mail and New e-mail Repeat must be same!"));
        }

        User user =  userService.getUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User cannot find by username " + username));

        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(changeEmailRequest.getUsername(), changeEmailRequest.getPassword()));

        } catch (BadCredentialsException e) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Password does not correct!"));
        }

        if (!changeEmailRequest.getOldEmail().equals(user.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Specified old e-mail does not correct!"));
        }

        user.setEmail(changeEmailRequest.getNewEmail());
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Success: Password has changed!"));
    }

    @GetMapping("{username}")
    @PreAuthorize("#username == authentication.name")
    public ResponseEntity<?> GetUserByUsername(@PathVariable String username) throws UsernameNotFoundException{
        User user = userService.getUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User cannot find by username " + username));
        return ResponseEntity.ok(user);
    }

}
