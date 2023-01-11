package com.takeeataway.api.controller;

import com.takeeataway.api.entity.Kitchen;
import com.takeeataway.api.service.KitchenService;
import org.hibernate.cfg.NotYetImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class KitchenController {
    private static final Logger log = LoggerFactory.getLogger(KitchenController.class);

    KitchenService service;

    @Autowired
    public KitchenController(KitchenService service) {
        this.service = service;
    }

    @PostMapping("/kitchens")
    public ResponseEntity<Kitchen> createKitchen(@RequestBody Kitchen kitchen) {
        kitchen = this.service.createKitchen(kitchen);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(kitchen.getId())
                .toUri();

        return ResponseEntity.created(location).body(kitchen);
    }
}
