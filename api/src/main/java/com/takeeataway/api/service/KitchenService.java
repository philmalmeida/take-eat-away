package com.takeeataway.api.service;

import com.takeeataway.api.controller.KitchenController;
import com.takeeataway.api.entity.Kitchen;
import com.takeeataway.api.repository.KitchenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KitchenService {
    private static final Logger log = LoggerFactory.getLogger(KitchenService.class);

    KitchenRepository repository;

    @Autowired
    public KitchenService(KitchenRepository repository) {
        this.repository = repository;
    }

    public Kitchen createKitchen(Kitchen kitchen) {
        kitchen = this.repository.save(kitchen);
        log.info("Created " + kitchen);
        return kitchen;
    }
}
