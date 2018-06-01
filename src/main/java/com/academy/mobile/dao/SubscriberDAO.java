package com.academy.mobile.dao;

import com.academy.mobile.model.Gender;
import com.academy.mobile.model.Subscriber;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class SubscriberDAO {
    private static final String queryGetRange = "SELECT * FROM mobile.abonent ORDER BY abonent_id LIMIT ? OFFSET ?;";
    private static final String queryGetAll = "SELECT * FROM mobile.abonent ORDER BY abonent_id;";

    private Connection connection;

    public SubscriberDAO(Connection connection) {
        this.connection = connection;
    }

    public Collection<Subscriber> getAll() {
        Collection<Subscriber> result = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(queryGetAll);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Subscriber subscriber = new Subscriber();
                subscriber.setId(rs.getLong("abonent_id"));
                subscriber.setFirstName(rs.getString("first_name"));
                subscriber.setLastName(rs.getString("last_name"));
                subscriber.setAge(rs.getInt("age"));
                subscriber.setGender(Gender.valueOf(rs.getString("gender").charAt(0)));
                result.add(subscriber);
            }

        } catch (SQLException e) {
            throw new DAOException("Can't find entity", e);
        }
        return result;
    }
}
