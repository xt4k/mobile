package com.academy.mobile.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "subscribers")
public class Subscribers {

    @JacksonXmlProperty(localName = "subscriber")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Subscriber> subscribers;

    public Subscribers(List<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }
}
