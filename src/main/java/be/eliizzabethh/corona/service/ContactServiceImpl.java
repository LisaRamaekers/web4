package be.eliizzabethh.corona.service;

import be.eliizzabethh.corona.domain.Contact;
import be.eliizzabethh.corona.repository.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service("ContactServiceImpl")
public class ContactServiceImpl implements ContactService{
    private final ContactRepo repository;

    @Autowired
    public ContactServiceImpl(ContactRepo repository) {

        this.repository = repository;
    }


    @Override
    public List<Contact> getAll() {
        return repository.findAll();
    }

    @Override
    public Contact get(Long id) {
        try {
            Contact c = repository.getOne(id);
            return c;
        }catch (EntityNotFoundException | NullPointerException exc){
            return null;
        }
    }

    @Override
    public void add(Contact contact) {
        repository.save(contact);
    }

    @Override
    public void update(Contact contact) {

    }

    @Override
    public void delete(Long id) {

    }
}
