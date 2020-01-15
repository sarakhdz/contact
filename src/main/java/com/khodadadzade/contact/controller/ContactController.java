package com.khodadadzade.contact.controller;

import com.khodadadzade.contact.domain.Contact;
import com.khodadadzade.contact.service.ContactService;
import com.khodadadzade.contact.service.dto.ContactDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RepositoryRestController
@RestController
public class ContactController {

    @Autowired
    private ContactService contactService;
    @RequestMapping(method = RequestMethod.GET, path = "/contact/search", produces = "application/json")
    public ResponseEntity<List<Contact>> find(ContactDto contactDto){
        return ResponseEntity.ok(contactService.filter(contactDto));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/contact", produces = "application/json")
    public ResponseEntity<Contact> create(@RequestBody ContactDto contactDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(contactService.create(contactDto));
    }
}
