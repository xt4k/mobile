package com.academy.mobile.model;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.IdentityGenerator;

import java.io.Serializable;
import java.sql.*;

public class FilterIdentifierGenerator extends IdentityGenerator implements IdentifierGenerator {

    private final String DEFAULT_SEQUENCE_NAME = "hibernate_sequence";

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object)
            throws HibernateException {

        long id = (Long) session.getEntityPersister(null, object)
                .getClassMetadata().getIdentifier(object, session);

        return generateNextIfNullOrUpdateId(id, session);
    }

    private long generateNextIfNullOrUpdateId(long id, SharedSessionContractImplementor session) {
        Connection connection = session.connection();
        Statement statement;
        ResultSet resultSet;
        try {
            /*
             * uncomment below line if you are using mysql and the sequence DOES NOT EXIST.
             * As we all know MySql does not support sequence, instead there is AUTO INCREMENT
             * if you are using other databases change SQL according to that
             * e.g. Oracle: "SELECT "+sequenceName+".NEXTVAL FROM DUAL"
             * PostgreSQL: "SELECT  NEXTVAL('+sequenceName+"')
             * */
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT next_val FROM  " + DEFAULT_SEQUENCE_NAME);
            if (resultSet.next()) {
                long nextVal = resultSet.getLong(1);
                if (id == 0L)
                    updateSequence(nextVal+1, session);
                else if (id >= nextVal)
                    updateSequence(id+1, session);

                return id == 0L ? nextVal : id;
            }

        } catch (Exception e) {

            System.out.println("In catch, cause : Table is not available.");
            // if sequence is not found then creating the sequence
            // Below code is for MySql database you change according to your database
//            statement.execute("CREATE table " + DEFAULT_SEQUENCE_NAME + " (next_val INT NOT NULL)");
//            statement.executeUpdate("INSERT INTO " + DEFAULT_SEQUENCE_NAME + " VALUES(0)");
//            //==> LAST_INSERT_ID(next_val+1)  -> this is inbuilt function of MySql so by using this we can achieve our custom sequence like auto increment
//            statement.executeUpdate("UPDATE " + DEFAULT_SEQUENCE_NAME + " SET next_val=LAST_INSERT_ID(next_val+1)");
//            resultSet = statement.executeQuery("SELECT next_val FROM  " + DEFAULT_SEQUENCE_NAME);
            e.printStackTrace();
            throw new HibernateException(e);
        }
        throw new RuntimeException("Error auto incrementing");
    }

    private void updateSequence(long value,  SharedSessionContractImplementor session) {
        try {
            Connection connection = session.connection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + DEFAULT_SEQUENCE_NAME + " SET next_val= ?");
            preparedStatement.setLong(1, value);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new HibernateException(e);
        }
    }
}