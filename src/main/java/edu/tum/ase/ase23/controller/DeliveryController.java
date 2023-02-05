package edu.tum.ase.ase23.controller;

import edu.tum.ase.ase23.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
@PreAuthorize("hasRole('DISPATCHER')")
public class DeliveryController {

    @GetMapping("")
    public ResponseEntity<?> getAllDeliveries() {
        return ResponseEntity.ok(new MessageResponse("Success: Access Granted!"));
    }

    @PostMapping("")
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
    public ResponseEntity<?> getDeliveryById()  {
        return ResponseEntity.ok(new MessageResponse("Success: Access Granted!"));
    }

    // Get Delivery information by trackingID
    @GetMapping("/trackingID/{trackingID}")
    public ResponseEntity<?> getDeliveryByTrackingID()  {
        return ResponseEntity.ok(new MessageResponse("Success: Access Granted!"));
    }

    // Update Delivery
    @PostMapping("/update/{deliveryID}")
    public ResponseEntity<?> updateDeliveryByDeliveryID() {
        return ResponseEntity.ok(new MessageResponse("Success: Access Granted!"));
    }

    @PostMapping("/delete/{deliveryID}")
    public ResponseEntity<?> deleteDelivery() {
        return ResponseEntity.ok(new MessageResponse("Success: Access Granted!"));
    }

}
