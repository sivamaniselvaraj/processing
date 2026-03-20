package org.assignments.processing.controller;

import org.assignments.processing.service.ProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/processing")
public class ProcessingController {

    @Autowired
    ProcessingService processingService;

    @PutMapping("/{orderId}/status")
    public ResponseEntity updateStatus(
            @PathVariable Long orderId,
            @RequestParam String status){

        processingService.updateStatus(orderId,status);

        return ResponseEntity.ok("updated");
    }
}
