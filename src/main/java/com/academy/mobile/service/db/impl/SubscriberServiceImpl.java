package com.academy.mobile.service.db.impl;

import com.academy.mobile.model.Subscriber;
import com.academy.mobile.repository.SubscriberRepository;
import com.academy.mobile.service.db.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriberServiceImpl implements SubscriberService {

    @Autowired
    private SubscriberRepository subscriberRepository;

    @Override
    public List<Subscriber> findAll() {
        return subscriberRepository.findAll();
    }

    @Override
    public Subscriber save(Subscriber subscriber) {
        return subscriberRepository.save(subscriber);
    }

    @Override
    public Subscriber getById(long id) {
        return subscriberRepository.findById(id).orElse(null);
    }

    @Override
    public void remove(long id) {
        subscriberRepository.deleteById(id);
    }
}
