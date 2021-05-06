package main.java.Vitebsk.ATP4.repo;

import org.springframework.data.repository.CrudRepository;

import main.java.Vitebsk.ATP4.models.Contact;

public interface ContactRepository extends CrudRepository<Contact, Long>
{
    
}
