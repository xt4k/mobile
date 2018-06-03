package com.academy.mobile.rest;

import com.academy.mobile.PropertyManager;
import com.academy.mobile.dao.ConnectionManager;
import com.academy.mobile.dao.SubscriberDAO;
import com.academy.mobile.model.Subscriber;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.sql.Connection;
import java.util.Collection;

@Path("/subscribers")
public class SubscriberService {

    @GET
//    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_XML })
    public Collection<Subscriber> getSubscribers() {
        try (ConnectionManager connectionManager = new ConnectionManager(PropertyManager.getInstance().getProperties())) {
            Connection conn = connectionManager.getConn();
            SubscriberDAO subscriberDAO = new SubscriberDAO(conn);
            Collection<Subscriber> subscribers = subscriberDAO.getAll();

            return subscribers;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GET
    @Path("/{id}")
//    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_XML })
    public Subscriber getSubscriber(@PathParam("id") long id) {
        try (ConnectionManager connectionManager = new ConnectionManager(PropertyManager.getInstance().getProperties())) {
            Connection conn = connectionManager.getConn();
            SubscriberDAO subscriberDAO = new SubscriberDAO(conn);
            return  subscriberDAO.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response addSubscriber(Subscriber subscriber) {
        try (ConnectionManager connectionManager = new ConnectionManager(PropertyManager.getInstance().getProperties())) {
            Connection conn = connectionManager.getConn();
            SubscriberDAO subscriberDAO = new SubscriberDAO(conn);
            long id = subscriberDAO.insert(subscriber);
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
        try (ConnectionManager connectionManager = new ConnectionManager(PropertyManager.getInstance().getProperties())) {
            Connection conn = connectionManager.getConn();
            SubscriberDAO subscriberDAO = new SubscriberDAO(conn);
            return subscriberDAO.update(subscriber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @DELETE
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response deleteSubscriber(@PathParam("id") long id) {
        try (ConnectionManager connectionManager = new ConnectionManager(PropertyManager.getInstance().getProperties())) {
            Connection conn = connectionManager.getConn();
            SubscriberDAO subscriberDAO = new SubscriberDAO(conn);
            subscriberDAO.delete(id);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(500).build();
    }

    @DELETE
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response deleteAllSubscribers() {
        try (ConnectionManager connectionManager = new ConnectionManager(PropertyManager.getInstance().getProperties())) {
            Connection conn = connectionManager.getConn();
            SubscriberDAO subscriberDAO = new SubscriberDAO(conn);
            System.out.println("delete all");
            //subscriberDAO.deleteAll(id);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(500).build();
    }
}
