package com.academy.mobile.service.rest.xml;

import com.academy.mobile.model.Subscriber;
import com.academy.mobile.service.db.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Collection;

@Path("/subscribers")
public class SubscriberServiceXML {

    @Autowired
    private SubscriberService subscriberService;

    @GET
//    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_XML })
    public Collection<Subscriber> getSubscribers() {
        return subscriberService.findAll();
    }

    @GET
    @Path("/{id}")
//    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_XML })
    public Subscriber getSubscriber(@PathParam("id") long id) {
        return subscriberService.getById(id);
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response addSubscriber(Subscriber subscriber) {
        try {
            long id = subscriberService.save(subscriber).getId();
            return Response.created(new URI(String.valueOf(id))).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(500).build();
    }

    @PUT
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Subscriber updateSubscriber(Subscriber subscriber) {
        return subscriberService.save(subscriber);
    }

    @DELETE
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response deleteSubscriber(@PathParam("id") long id) {
        try {
            subscriberService.remove(id);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(500).build();
    }

    @DELETE
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    // TODO
    public Response deleteAllSubscribers() {
        try {
            System.out.println("delete all");
            //subscriberDAO.deleteAll(id);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(500).build();
    }
}
