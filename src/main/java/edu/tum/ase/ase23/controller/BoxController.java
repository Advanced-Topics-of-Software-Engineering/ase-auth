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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/box")
public class BoxController {

    @Autowired
    UserService userService;
    @Value("${edu.tum.ase.ase23.app.boxSecret}")
    private String boxSecret;

    @GetMapping("/{boxId}")
    public ResponseEntity<?> GetUserId(@Valid @RequestBody BoxRequest boxRequest, @RequestHeader HttpHeaders headers) throws Exception {
        String authenticationKey = headers.toSingleValueMap().get("x-authentication");
        if (!authenticationKey.equals(boxSecret)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Unauthorized request!"));
        }

        User user = userService.getUserByRFIDToken(boxRequest.getRFIDToken()).orElseThrow(
                () -> new UserRFIDTokenNotFoundException("User cannot find by rfid token" + boxRequest.getRFIDToken()));

        return ResponseEntity.ok().body(user);
    }

}
