package com.academy.mobile.service.soap;

import com.academy.mobile.model.Subscriber;
import com.academy.mobile.service.db.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Collection;

@WebService
public class SubscriberWSImpl implements SubscriberWS {

    @Autowired
    private SubscriberService subscriberService;

    @WebMethod
    @Override
    public Collection<Subscriber> getSubscribers() {
        return subscriberService.findAll();
}

    @WebMethod
    @Override
    public Subscriber getSubscriber(long id) {
        return subscriberService.getById(id);
    }

    @WebMethod
    @Override
    public long addSubscriber(Subscriber subscriber) {
        return subscriberService.save(subscriber).getId();
    }

    @WebMethod
    @Override
    public void removeSubscriber(long id) {
        subscriberService.remove(id);
    }

    @WebMethod
    @Override
    public Subscriber updateSubscriber(long id, Subscriber subscriber) {
        return subscriberService.save(subscriber);
    }
}
