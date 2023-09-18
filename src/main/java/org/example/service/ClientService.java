package org.example.service;

import org.example.dao.ClientDao;
import org.example.model.PersonInfo;

import java.sql.SQLException;


public class ClientService {
    private ClientDao dao;

    public ClientService() throws SQLException {
        this.dao = new ClientDao();
    }
    public PersonInfo getPersonId(String phone) {
        return dao.getPersonId(phone);
    }
}
