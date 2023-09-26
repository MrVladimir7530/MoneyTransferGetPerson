package org.example.dao;

import org.example.bl.HibernateUtil;
import org.example.exceptions.ProcessException;
import org.example.model.PersonInfo;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class ClientDao extends HibernateUtil {

    private Logger logger;

    public ClientDao() throws SQLException {
        this.logger = LoggerFactory.getLogger(ClientDao.class);
    }

    public PersonInfo getPersonId(String phoneNumber) {
        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            String nql = "select p from PersonInfo p where phone = :phoneNumber";
            Query<PersonInfo> query = session.createQuery(nql, PersonInfo.class);
            query.setParameter("phoneNumber", phoneNumber);
            List<PersonInfo> list = query.list();
            if (list.size() > 1) {
                logger.debug("This number is not unique");
                throw new ProcessException("code", "This number is not unique", 400);
            }
            return list.get(0);

        } catch (Exception exception) {
            logger.debug(exception.getMessage());
            throw new RuntimeException();
        }

    }

}
