package be.eliizzabethh.corona.controller;

import be.eliizzabethh.corona.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contacts")
public class ContactController {
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {this.contactService = contactService; }

    @GetMapping
    public String getTasks(Model model){
        model.addAttribute("contacts", contactService.getAll());
        return "contacts";
    }
}
