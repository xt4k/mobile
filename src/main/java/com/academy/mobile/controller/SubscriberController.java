package com.academy.mobile.controller;

import com.academy.mobile.model.Subscriber;
import com.academy.mobile.service.db.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SubscriberController {

    @Autowired
    private SubscriberService subscriberService;

    @RequestMapping(value = "/subscribers", method = RequestMethod.GET)
    public String subscribers(Model model) {
        model.addAttribute("subscribers", subscriberService.findAll());
        return "/subscriber/subscribers";
    }

    @RequestMapping(value = "/subscriber/add", method = RequestMethod.GET)
    public String subscriberAddForm(Model model) {
        model.addAttribute("subscriberForm", new Subscriber());
        return "/subscriber/subscriberAdd";
    }

    @RequestMapping(value = "/subscriber/add", method = RequestMethod.POST)
    public String createSubscriber(@ModelAttribute("subscriberForm") Subscriber subscriberForm, BindingResult bindingResult) {
//        LOG.info("Try register a new user '{}'", userForm.getUsername());
//        userValidator.validate(userForm, bindingResult);

//        if (bindingResult.hasErrors()) {
//            LOG.info("Reject of creation user '{}' - not valid form data", userForm.getUsername());
//            return "registration";
//        }

        subscriberService.save(subscriberForm);
//        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

//        LOG.info("The user '{}' registered successful", userForm.getUsername());
        return "redirect:/subscribers";
    }
}
