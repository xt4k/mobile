package com.academy.mobile.soap;

import com.academy.mobile.PropertyManager;
import com.academy.mobile.dao.ConnectionManager;
import com.academy.mobile.dao.SubscriberDAO;
import com.academy.mobile.model.Subscriber;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.sql.Connection;
import java.util.Collection;

@WebService
public class SubscriberWSImpl implements SubscriberWS {

    @WebMethod
    @Override
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

    @WebMethod
    @Override
    public Subscriber getSubscriber(long id) {
        try (ConnectionManager connectionManager = new ConnectionManager(PropertyManager.getInstance().getProperties())) {
            Connection conn = connectionManager.getConn();
            SubscriberDAO subscriberDAO = new SubscriberDAO(conn);
            return  subscriberDAO.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @WebMethod
    @Override
    public long addSubscriber(Subscriber subscriber) {
        try (ConnectionManager connectionManager = new ConnectionManager(PropertyManager.getInstance().getProperties())) {
            Connection conn = connectionManager.getConn();
            SubscriberDAO subscriberDAO = new SubscriberDAO(conn);
            long id = subscriberDAO.insert(subscriber);
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @WebMethod
    @Override
    public void removeSubscriber(long id) {
        try (ConnectionManager connectionManager = new ConnectionManager(PropertyManager.getInstance().getProperties())) {
            Connection conn = connectionManager.getConn();
            SubscriberDAO subscriberDAO = new SubscriberDAO(conn);
            subscriberDAO.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @WebMethod
    @Override
    public Subscriber updateSubsriber(long id, Subscriber subscriber) {
        try (ConnectionManager connectionManager = new ConnectionManager(PropertyManager.getInstance().getProperties())) {
            Connection conn = connectionManager.getConn();
            SubscriberDAO subscriberDAO = new SubscriberDAO(conn);
            return subscriberDAO.update(subscriber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
