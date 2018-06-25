package com.academy.mobile.soap;

import com.academy.mobile.model.Subscriber;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Collection;

@WebService
public interface SubscriberWS {

    @WebMethod
    Collection<Subscriber> getSubscribers();

    @WebMethod
    Subscriber getSubscriber(long id);

    @WebMethod
    long addSubscriber(Subscriber subscriber);

    @WebMethod
    void removeSubscriber(long id);

    @WebMethod
    Subscriber updateSubsriber(long id, Subscriber subscriber);
}
