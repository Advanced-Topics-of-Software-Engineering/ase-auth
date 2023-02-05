package edu.tum.ase.ase23.controller;

import edu.tum.ase.ase23.model.User;
import edu.tum.ase.ase23.payload.request.EmailRequest;
import edu.tum.ase.ase23.payload.request.BoxRequest;
import edu.tum.ase.ase23.payload.response.MessageResponse;
import edu.tum.ase.ase23.service.UserService;
import edu.tum.ase.ase23.util.UserIdNotFoundException;
import edu.tum.ase.ase23.util.UserRFIDTokenNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
    @Value("${edu.tum.ase.ase23.app.boxSecret}")
    private String boxSecret;

    @Autowired
    UserService userService;

    @GetMapping("")
    @PreAuthorize("hasRole('DISPATCHER')")
    public ResponseEntity<?> getAllDeliveries() {
        return ResponseEntity.ok(new MessageResponse("Success: Access Granted!"));
    }

    @PostMapping("")
    @PreAuthorize("hasRole('DISPATCHER')")
    public ResponseEntity<?> createDelivery() {
        return ResponseEntity.ok(new MessageResponse("Success: Access Granted!"));
        // new Delivery(dto.get(userId), .. , )
    }

    @GetMapping("/deliverer/{delivererId}")
    @PreAuthorize("hasRole('DISPATCHER') or hasRole('DELIVERER')")
    public ResponseEntity<?> getDeliveriesOfUserFromDelivererId(@PathVariable String delivererId)  {
        return ResponseEntity.ok(new MessageResponse("Success: Access Granted!"));
    }
    @GetMapping("/customer/{customerId}")
    @PreAuthorize("hasRole('DISPATCHER') or hasRole('CUSTOMER')")
    public ResponseEntity<?> getDeliveriesOfUserFromCustomerId() {
        return ResponseEntity.ok(new MessageResponse("Success: Access Granted!"));
    }

    @GetMapping("/deliverer/updateStatus/{trackingID}")
    @PreAuthorize("hasRole('DISPATCHER') or hasRole('DELIVERER')")
    public ResponseEntity<?> updateStatusToPickedUpByTrackingID(@PathVariable String trackingID) {
        return ResponseEntity.ok(new MessageResponse("Success: Access Granted!"));
    }

    // Get Delivery info by ID
    @GetMapping("/id/{deliveryId}")
    public ResponseEntity<?> getDeliveryById()  {
        return ResponseEntity.ok(new MessageResponse("Success: Access Granted!"));
    }

    // Get Delivery information by trackingID
    @GetMapping("/trackingID/{trackingID}")
    public ResponseEntity<?> getDeliveryByTrackingID(@PathVariable String trackingID)  {
        int b =5;
        int a =5;
        return ResponseEntity.ok().body(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    // Update Delivery
    @PostMapping("/update/{deliveryID}")
    @PreAuthorize("hasRole('DISPATCHER')")
    public ResponseEntity<?> updateDeliveryByDeliveryID() {
        return ResponseEntity.ok(new MessageResponse("Success: Access Granted!"));
    }

    @PostMapping("/delete/{deliveryID}")
    @PreAuthorize("hasRole('DISPATCHER')")
    public ResponseEntity<?> deleteDelivery() {
        return ResponseEntity.ok(new MessageResponse("Success: Access Granted!"));
    }

    @PostMapping("/box/close")
    public ResponseEntity<?> updateStatusOfDeliveries(@RequestHeader HttpHeaders headers) {
        String authenticationKey = headers.toSingleValueMap().get("x-authentication");
        if (!authenticationKey.equals(boxSecret)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Unauthorized request!"));
        }
        return ResponseEntity.ok(new MessageResponse("Success: Access Granted!"));
    }

    @PostMapping("/box/getEmail")
    public ResponseEntity<?> getEmails(@RequestBody Map<String,String> emailRequest, @RequestHeader HttpHeaders headers) throws UserIdNotFoundException {
        String authenticationKey = headers.toSingleValueMap().get("x-authentication");
        if (!authenticationKey.equals(boxSecret)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Unauthorized request!"));
        }
        Map<String, String> responseMap = new HashMap<>();
        String customerId = emailRequest.get("customerId");
        User user = userService.getUserById(customerId).orElseThrow(() -> new UserIdNotFoundException());
        responseMap.put("email",user.getEmail());
        return ResponseEntity.ok().body(responseMap);
    }

    @PostMapping("/box/validate")
    public ResponseEntity<?> updateBoxValidation(@Valid @RequestBody BoxRequest boxRequest, @RequestHeader HttpHeaders headers) throws UserRFIDTokenNotFoundException {
        String authenticationKey = headers.toSingleValueMap().get("x-authentication");
        if (!authenticationKey.equals(boxSecret)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Unauthorized request!"));
        }

        User user = userService.getUserByRFIDToken(boxRequest.getRFIDToken()).orElseThrow(
                () -> new UserRFIDTokenNotFoundException("User cannot find by rfid token" + boxRequest.getRFIDToken()));

        return ResponseEntity.ok().body(user);
    }
}
