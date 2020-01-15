package com.khodadadzade.contact.config;

import com.khodadadzade.contact.controller.ContactController;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

public class RootResourceProcessor implements RepresentationModelProcessor<RepositoryLinksResource> {
    @Override
    public RepositoryLinksResource process(RepositoryLinksResource model) {
        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ContactController.class).find(null)).withRel("contact"));
        return model;
    }
}
