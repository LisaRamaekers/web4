package be.eliizzabethh.corona.repository;

import be.eliizzabethh.corona.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepo extends JpaRepository<Contact, Long> {
}
