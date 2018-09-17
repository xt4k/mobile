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

import java.util.ArrayList;
import java.util.List;

@Controller
public class SubscriberController {

    @Autowired
    private SubscriberService subscriberService;

    @RequestMapping(value = "/subscribers", method = RequestMethod.GET)
    public String subscribers(Model model) {
        model.addAttribute("subscribers", subscriberService.findAll());
        model.addAttribute("idList", new ArrayList<Long>());
        return "subscriber/subscribers";
    }

    @RequestMapping(value = "/subscriber/add", method = RequestMethod.GET)
    public String subscriberAddForm(Model model) {
        model.addAttribute("subscriberForm", new Subscriber());
        return "subscriber/subscriberAdd";
    }

    @RequestMapping(value = "/subscriber/add", method = RequestMethod.POST)
    public String createSubscriber(@ModelAttribute("subscriberForm") Subscriber subscriberForm, BindingResult bindingResult) {
        subscriberService.save(subscriberForm);
        return "redirect:/subscribers";
    }

    // TODO id = error handling
    @RequestMapping(value = "/subscriber/edit", method = RequestMethod.GET)
    public String subscriberEditForm(Model model, Long id) {
        model.addAttribute("subscriberForm", subscriberService.getById(id));
        return "subscriber/subscriberEdit";
    }

    @RequestMapping(value = "/subscriber/edit", method = RequestMethod.POST)
    // TODO check if exists
    public String editSubscriber(@ModelAttribute("subscriberForm") Subscriber subscriberForm, BindingResult bindingResult) {
        subscriberService.saveById(subscriberForm.getId(), subscriberForm);
        return "redirect:/subscribers";
    }

    @RequestMapping(value = "/subscriber/del", method = RequestMethod.POST)
    // TODO check if exists
    public String deleteSubscribers(@ModelAttribute("idList") List<Long> idList, BindingResult bindingResult) {
        idList.forEach(subscriberService::remove);
        return "redirect:/subscribers";
    }
}
