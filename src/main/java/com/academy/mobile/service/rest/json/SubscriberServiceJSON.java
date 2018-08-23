package com.academy.mobile.service.rest.json;

import com.academy.mobile.model.Subscriber;
import com.academy.mobile.service.db.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/rest/json/subscribers")
public class SubscriberServiceJSON {

    @Autowired
    private SubscriberService subscriberService;

    @GetMapping(produces = { "application/json" })
    public Collection<Subscriber> getSubscribers() {
        return subscriberService.findAll();
    }

    @GetMapping("/{id}")
    public Subscriber getSubscriber(@PathVariable long id) {
        return subscriberService.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> addSubscriber(@RequestBody Subscriber subscriber) {

        try {
            long id = subscriberService.save(subscriber).getId();
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(id).toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public Subscriber updateSubscriber(@PathVariable("id") long id, @RequestBody Subscriber subscriber) {
        if (subscriberService.getById(id) == null)
            return null;

        if (id != subscriber.getId())
            return null;

        return subscriberService.save(subscriber);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubscriber(@PathVariable("id") long id) {
        try {
            subscriberService.remove(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAllSubscribers() {
        try {
            System.out.println("delete all");
            subscriberService.findAll().stream()
                    .map(Subscriber::getId)
                    .forEach(subscriberService::remove);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
