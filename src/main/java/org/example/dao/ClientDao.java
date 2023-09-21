package org.example.dao;

import org.example.model.PersonInfo;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDao  {
    @PersistenceContext
    private EntityManager entityManager;
    private Connection connection;
    private Logger logger;

    public ClientDao(EntityManager manager) throws SQLException{
        SessionImpl session = (SessionImpl) manager.unwrap(Session.class);
        this.connection = session.connection();
        this.logger  = LoggerFactory.getLogger(ClientDao.class);
    }

    public PersonInfo getPersonId(String phone) {
        PreparedStatement preparedStatement = null;
        PersonInfo personInfo = new PersonInfo();

        String SQL = "SELECT * FROM public.\"person_info\"" +
                "Where phone = ?";

        try {
            logger.info("select personName");
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, phone);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            long id = resultSet.getLong("id");
            long personId = resultSet.getLong("person_id");
            String phoneNumber = resultSet.getString("phone");
            personInfo.setId(id);
            personInfo.setPersonId(personId);
            personInfo.setPhone(phoneNumber);
                logger.info("Successful select personName");
            }

        } catch (SQLException e) {
            logger.info("Exception in getPersonId");
            throw new RuntimeException(e);
        }
        return personInfo;
    }

}
