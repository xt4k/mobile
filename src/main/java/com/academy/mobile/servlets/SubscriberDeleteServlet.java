package com.academy.mobile.servlets;

import com.academy.mobile.PropertyManager;
import com.academy.mobile.dao.ConnectionManager;
import com.academy.mobile.dao.SubscriberDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubscriberDeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try(ConnectionManager connectionManager = new ConnectionManager(PropertyManager.getInstance().getProperties());) {
            if (!req.getParameter("ids").isEmpty()) {
                String[] idArr = req.getParameter("ids").split(",");
                List<Long> idList = new ArrayList<>();
                SubscriberDAO subscriberDAO = new SubscriberDAO(connectionManager.getConn());
                Arrays.stream(idArr).map(Long::parseLong).forEach(idList::add);
                subscriberDAO.deleteAll(idList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resp.sendRedirect("/subscribers");
        }
    }
}
