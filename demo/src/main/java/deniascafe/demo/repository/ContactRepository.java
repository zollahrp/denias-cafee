package deniascafe.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import deniascafe.demo.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}