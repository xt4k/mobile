package com.academy.mobile.service.db;

import com.academy.mobile.model.Subscriber;

import java.util.List;

public interface SubscriberService  {
    List<Subscriber> findAll();
    Subscriber save(Subscriber subscriber);
    Subscriber getById(long id);
    void remove(long id);
}
