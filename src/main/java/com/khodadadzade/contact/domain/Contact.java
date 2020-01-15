package com.khodadadzade.contact.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "contacts")
public class Contact implements Serializable {
    @Id
    private String id;
    private String name;
    private String phoneNumber;
    private String email;
    private String organization;
    private String github;
    private String repository;
}
