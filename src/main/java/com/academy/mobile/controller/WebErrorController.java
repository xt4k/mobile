package com.academy.mobile.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Controller for 'ErrorPage'
 */
@Controller
public class WebErrorController {
    private static final Logger LOG = LoggerFactory.getLogger(WebErrorController.class);


    /**
     * Error page. Mapped for url '/templates/error' and represent by invalid
     * 'get' request
     *
     * @param model the model for 'error page' defines a holder for model attributes
     * @return name of view as String (404.html)
     */
    @RequestMapping("/templates/error")
    public String pageNotFound (Model model) {
        LOG.info("Redirect request to 'Error Page'");
        return "error/404";
    }

}
