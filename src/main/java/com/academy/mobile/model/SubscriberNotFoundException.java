package com.academy.mobile.model;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="This subscriber is not found in the mobile")
public class SubscriberNotFoundException extends Exception {
}
