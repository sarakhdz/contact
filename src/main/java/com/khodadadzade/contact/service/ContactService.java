package com.khodadadzade.contact.service;

import com.khodadadzade.contact.domain.Contact;
import com.khodadadzade.contact.restclient.GithubRestClient;
import com.khodadadzade.contact.service.dto.ContactDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    @Autowired
    MongoOperations mongoOperations;
    @Autowired
    ModelMapper mapper;
    @Autowired
    GithubRestClient githubRestClient;


    public List<Contact> filter(ContactDto contactDto) {
        Contact contact = mapper.map(contactDto, Contact.class);
        Query query = new Query();
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreNullValues();
        Example<Contact> of = Example.of(contact, exampleMatcher);
        query.addCriteria(Criteria.byExample(of));
        List<Contact> contacts = mongoOperations.find(query, Contact.class);
        return contacts;
    }

    public Contact create(ContactDto contactDto){
        Optional<String> publicRepoByUserName = githubRestClient.getPublicRepoByUserName(contactDto.getGithub());
        Contact contact = mapper.map(contactDto, Contact.class);
        if (publicRepoByUserName.isPresent())
            contact.setRepository(publicRepoByUserName.get());
        contact = mongoOperations.save(contact);
        return contact;
    }

}
