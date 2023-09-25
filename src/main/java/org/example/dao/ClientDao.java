package org.example.dao;

import org.example.bl.HibernateUtil;
import org.example.bl.Util;
import org.example.model.PersonInfo;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class ClientDao extends Util {

    private Logger logger;

    public ClientDao() throws SQLException {
        this.logger = LoggerFactory.getLogger(ClientDao.class);
    }

    public PersonInfo getPersonId(String phone) {
        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            PersonInfo personInfo1 = session.get(PersonInfo.class, 6);
            System.out.println(personInfo1);
            PersonInfo personInfo = session.get(PersonInfo.class, phone);
            return personInfo;
        } catch (Throwable throwable) {
            System.out.println("Что-то пошло не так");
            throw new RuntimeException();
        } finally {
            HibernateUtil.close();
        }


//        PreparedStatement preparedStatement = null;
//        PersonInfo personInfo = new PersonInfo();
//
//        String SQL = "SELECT * FROM public.\"person_info\"" +
//                "Where phone = ?";
//
//        try (Connection connection = ) {
//            logger.info("select personName");
//            preparedStatement = connection.prepareStatement(SQL);
//            preparedStatement.setString(1, phone);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                long id = resultSet.getLong("id");
//                long personId = resultSet.getLong("person_id");
//                String phoneNumber = resultSet.getString("phone");
//                personInfo.setId(id);
//                personInfo.setPersonId(personId);
//                personInfo.setPhone(phoneNumber);
//                logger.info("Successful select personName");
//            }
//
//        } catch (SQLException e) {
//            logger.info("Exception in getPersonId");
//            throw new RuntimeException(e);
//        }
//        return personInfo;
    }

}
