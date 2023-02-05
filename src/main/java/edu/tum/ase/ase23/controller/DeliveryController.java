package edu.tum.ase.ase23.controller;

import edu.tum.ase.ase23.model.User;
import edu.tum.ase.ase23.payload.request.BoxRequest;
import edu.tum.ase.ase23.payload.response.MessageResponse;
import edu.tum.ase.ase23.service.UserService;
import edu.tum.ase.ase23.util.UserRFIDTokenNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Reference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getDeliveriesOfUserFromDelivererId()  {
        return ResponseEntity.ok(new MessageResponse("Success: Access Granted!"));
    }
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getDeliveriesOfUserFromCustomerId() {
        return ResponseEntity.ok(new MessageResponse("Success: Access Granted!"));
    }

    // Get Delivery info by ID
    @GetMapping("/id/{deliveryId}")
    @PreAuthorize("hasRole('DISPATCHER')")
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

    @PostMapping("/delete/{deliveryID}")
    public ResponseEntity<?> deleteDelivery(@PathVariable String deliveryID) {
        return ResponseEntity.ok(new MessageResponse("Success: Access Granted!"));
    }

}
