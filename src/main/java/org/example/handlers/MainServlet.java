package org.example.handlers;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.example.exceptions.ProcessException;
import org.example.model.ClientTransferRequest;
import org.example.model.ClientTransferResponse;
import org.example.model.PersonInfo;
import org.example.model.PersonWithPhoneResponse;
import org.example.service.ClientService;
import org.example.utils.Common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class MainServlet extends HttpServlet
{
    private static Logger log = LoggerFactory.getLogger(MainServlet.class.getSimpleName());
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String body = IOUtils.toString(req.getInputStream());
        if(StringUtils.isBlank(body))
        {
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println(Common.getPrettyGson().toJson(new ClientTransferResponse("404", "body is blank")));
            return;
        }
        ClientTransferRequest r = Common.getPrettyGson().fromJson(body, ClientTransferRequest.class);
        ClientService clientService = null;
        try {
            clientService = new ClientService();
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            resp.setContentType("application/json");
            ProcessException processException = new ProcessException("code", "слишком много запросов", 417);
            resp.getWriter().println(Common.getPrettyGson().toJson(processException));
            return;
        }
        PersonInfo fromPersonId = clientService.getPersonId(r.getFromPhone());
        PersonInfo toPersonId = clientService.getPersonId(r.getToPhone());
        PersonWithPhoneResponse personInfoResponse = new PersonWithPhoneResponse(fromPersonId, toPersonId);


        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println(Common.getPrettyGson().toJson(personInfoResponse));
    }



}
