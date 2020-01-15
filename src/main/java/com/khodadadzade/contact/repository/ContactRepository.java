package com.khodadadzade.contact.repository;

import com.khodadadzade.contact.domain.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "contact", path = "contact")

public interface ContactRepository extends MongoRepository<Contact, String>, CrudRepository<Contact, String> {
}
