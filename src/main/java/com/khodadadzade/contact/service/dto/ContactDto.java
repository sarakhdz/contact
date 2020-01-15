package com.khodadadzade.contact.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ContactDto implements Serializable {

    private String name;
    private String phoneNumber;
    private String email;
    private String organization;
    private String github;

}
