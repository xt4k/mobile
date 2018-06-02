package com.academy.mobile.dao;

import com.academy.mobile.model.Gender;
import com.academy.mobile.model.Subscriber;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class SubscriberDAO {
    private static final String QUERY_GET_RANGE = "SELECT * FROM abonent ORDER BY abonent_id LIMIT ? OFFSET ?;";
    private static final String QUERY_GET_ALL = "SELECT * FROM abonent ORDER BY abonent_id;";
    private static final String QUERY_GET_BY_ID = "SELECT * FROM abonent WHERE abonent_id=?;";
    private static final String QUERY_INSERT_SUBSCRIBER = "INSERT INTO abonent(first_name, last_name, gender, age) VALUES(?, ?, ?, ?);";
    private static final String QUERY_DELETE_MULT_SUBSCRIBER = "DELETE FROM abonent WHERE abonent_id IN ?;";
    private static final String QUERY_UPDATE_SUBSCRIBER = "UPDATE abonent SET first_name=?, last_name=?, gender=?, age=? WHERE abonent_id=?;";

    private Connection connection;

    public SubscriberDAO(Connection connection) {
        this.connection = connection;
    }

    public Collection<Subscriber> getAll() {
        Collection<Subscriber> result = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(QUERY_GET_ALL)) {

            while (rs.next()) {
                Subscriber subscriber = new Subscriber()
                        .withId(rs.getLong("abonent_id"))
                        .withFirstName(rs.getString("first_name"))
                        .withLastName(rs.getString("last_name"))
                        .withGender(Gender.valueOf(rs.getString("gender").charAt(0)))
                        .withAge(rs.getInt("age"));

                result.add(subscriber);
            }

        } catch (SQLException e) {
            throw new DAOException("Can't find entity", e);
        }
        return result;
    }

    public Subscriber getById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(QUERY_GET_BY_ID)){
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            Subscriber subscriber = new Subscriber()
                    .withId(rs.getLong("abonent_id"))
                    .withFirstName(rs.getString("first_name"))
                    .withLastName(rs.getString("last_name"))
                    .withGender(Gender.valueOf(rs.getString("gender").charAt(0)))
                    .withAge(rs.getInt("age"));

            return subscriber;

        } catch (SQLException e) {
            throw new DAOException("Can't find entity", e);
        }
    }

    public void insert(Subscriber subscriber) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT_SUBSCRIBER);) {
            preparedStatement.setString(1, subscriber.getFirstName());
            preparedStatement.setString(2, subscriber.getLastName());
            preparedStatement.setString(3, Character.toString(subscriber.getGender().getEng()));
            preparedStatement.setInt(4, subscriber.getAge());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException("Can't find entity", e);
        }
    }

    public void deleteAll(Collection<Long> idList) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(QUERY_DELETE_MULT_SUBSCRIBER
                    .replaceFirst("\\?", extractIdForQuery(idList)));

        } catch (SQLException e) {
            throw new DAOException("Can't find entity", e);
        }
    }

    public void update(Subscriber subscriber) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE_SUBSCRIBER)) {
            preparedStatement.setString(1, subscriber.getFirstName());
            preparedStatement.setString(2, subscriber.getLastName());
            preparedStatement.setString(3, Character.toString(subscriber.getGender().getEng()));
            preparedStatement.setInt(4, subscriber.getAge());
            preparedStatement.setLong(5, subscriber.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException("Can't find entity", e);
        }
    }

    private String extractIdForQuery(Collection<Long> idList) {
        return Arrays.toString(idList.toArray())
                .replaceFirst("\\[", "(")
                .replaceFirst("\\]", ")");
    }
}
