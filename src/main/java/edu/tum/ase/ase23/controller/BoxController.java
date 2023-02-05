package edu.tum.ase.ase23.controller;

import edu.tum.ase.ase23.model.User;
import edu.tum.ase.ase23.payload.request.BoxRequest;
import edu.tum.ase.ase23.payload.response.MessageResponse;
import edu.tum.ase.ase23.service.UserService;
import edu.tum.ase.ase23.util.UserRFIDTokenNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/box")
public class BoxController {

    @Autowired
    UserService userService;
    @Value("${edu.tum.ase.ase23.app.boxSecret}")
    private String boxSecret;

    @GetMapping("/get_user/{boxId}")
    public ResponseEntity<?> GetUserId(@Valid @RequestBody BoxRequest boxRequest, @RequestHeader HttpHeaders headers) throws Exception {
        String authenticationKey = headers.toSingleValueMap().get("x-authentication");
        if (!authenticationKey.equals(boxSecret)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Unauthorized request!"));
        }

        User user = userService.getUserByRFIDToken(boxRequest.getRFIDToken()).orElseThrow(
                () -> new UserRFIDTokenNotFoundException("User cannot find by rfid token" + boxRequest.getRFIDToken()));

        return ResponseEntity.ok().body(user);
    }

    @GetMapping("")
    @PreAuthorize("hasRole('DISPATCHER')")
    public ResponseEntity<?> getAllBoxes() {
        return ResponseEntity.ok(new MessageResponse("Success: Access Granted!"));
    }

    @PostMapping("")
    @PreAuthorize("hasRole('DISPATCHER')")
    public ResponseEntity<?> createBox() {
        return ResponseEntity.ok(new MessageResponse("Success: Access Granted!"));
    }

    @GetMapping("/name/{Name}")
    @PreAuthorize("hasRole('DISPATCHER')")
    public ResponseEntity<?> findBoxByName(@PathVariable String Name) throws Exception {
        return ResponseEntity.ok(new MessageResponse("Success: Access Granted!"));
    }

    @GetMapping("/address/{StreetAddress}")
    @PreAuthorize("hasRole('DISPATCHER')")
    public ResponseEntity<?> findBoxByStreetAddress(@PathVariable String StreetAddress)  {
        return ResponseEntity.ok(new MessageResponse("Success: Access Granted!"));
    }

    @GetMapping("/{Id}")
    @PreAuthorize("hasRole('DISPATCHER')")
    public ResponseEntity<?> findBoxById(@PathVariable String Id) {
        return ResponseEntity.ok(new MessageResponse("Success: Access Granted!"));
    }

    @PostMapping("/update/{Id}")
    @PreAuthorize("hasRole('DISPATCHER')")
    public ResponseEntity<?> updateBox(@PathVariable String Id) {
        return ResponseEntity.ok(new MessageResponse("Success: Access Granted!"));
    }

    @PostMapping("/delete/{Id}")
    @PreAuthorize("hasRole('DISPATCHER')")
    public ResponseEntity<?> deleteBox(@PathVariable String Id)  {
        return ResponseEntity.ok(new MessageResponse("Success: Access Granted!"));
    }

    @GetMapping("/available/{userid}")
    @PreAuthorize("hasRole('DISPATCHER')")
    public ResponseEntity<?> getAvailableBoxesForCustomer(@PathVariable String userid) {
        return ResponseEntity.ok(new MessageResponse("Success: Access Granted!"));
    }
}


