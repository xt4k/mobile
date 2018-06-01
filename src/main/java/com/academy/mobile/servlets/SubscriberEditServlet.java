package com.academy.mobile.servlets;

import com.academy.mobile.PropertyManager;
import com.academy.mobile.dao.ConnectionManager;
import com.academy.mobile.dao.SubscriberDAO;
import com.academy.mobile.model.Subscriber;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class SubscriberEditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            req.getRequestDispatcher("/subscriber/subscriberEdit.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("/index.jsp");
        }
    }
}
