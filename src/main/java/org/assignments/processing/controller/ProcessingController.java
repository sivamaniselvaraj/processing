package org.assignments.processing.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.assignments.processing.service.ProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/processing")
public class ProcessingController {

    @Autowired
    ProcessingService processingService;

    @PutMapping("/{orderId}/status")
    @Operation(summary = "Update order status")
    public ResponseEntity<?> updateStatus(
            @PathVariable Long orderId,
            @RequestParam String status){
        log.info("Received update status request for: {} status: {}", orderId, status);
        processingService.updateStatus(orderId,status);
        log.info("update status successfully for: {}", orderId );
        return ResponseEntity.ok("update status successfully for: " + orderId + "to: " + status);
    }
}
