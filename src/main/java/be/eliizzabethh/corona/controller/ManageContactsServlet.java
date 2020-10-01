package be.eliizzabethh.corona.controller;

import be.eliizzabethh.corona.domain.Contact;
import be.eliizzabethh.corona.service.ContactService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/ManageContactsServlet")
public class ManageContactsServlet extends HttpServlet {

    private final ContactService contactService;

    @Autowired
    public ManageContactsServlet(ContactService contactService) {this.contactService = contactService; }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Contact> contacts = contactService.getAll();
        String contactsJSON = this.toJSON(contacts);
        response.setContentType("application/json");
        response.getWriter().write(contactsJSON);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Contact contact = new Contact();
        contact.setFirstname((String)request.getParameter("firstname"));
        contact.setLastname((String)request.getParameter("lastname"));
        contact.setEmail((String)request.getParameter("email"));
        //contact.setDate((String)request.getParameter("date"));
        contact.setGsm((String)request.getParameter("gsm"));

        contactService.add(contact);
    }

    private String toJSON (List<Contact> contacts) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(contacts);
    }
}
