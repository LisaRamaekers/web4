package be.eliizzabethh.corona.service;

import be.eliizzabethh.corona.domain.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> getAll();
    Contact get(Long id);
    void add(Contact contact);
    void update(Contact contact);
    void delete(Long id);
}
