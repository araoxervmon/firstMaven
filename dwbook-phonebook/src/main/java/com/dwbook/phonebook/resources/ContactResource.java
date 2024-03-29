package com.dwbook.phonebook.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import com.dwbook.phonebook.representations.Contact;
import org.skife.jdbi.v2.DBI;
import java.util.Set;
import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import javax.validation.Validator;
import javax.ws.rs.core.Response.Status;
import io.dropwizard.jdbi.DBIFactory;
import com.dwbook.phonebook.dao.ContactDAO;
import java.net.URI;
import java.net.URISyntaxException;
import io.dropwizard.auth.Auth;

@Path("/contact")
@Produces(MediaType.APPLICATION_JSON)
public class ContactResource  
{
  
  private final ContactDAO contactDao;  private final Validator validator;
  public ContactResource(DBI jdbi, Validator validator) {
    contactDao = jdbi.onDemand(ContactDAO.class);    this.validator = validator;
  }
  
  @GET
  @Path("/{id}")
  public Response getContact(@PathParam("id") int id, @Auth Boolean isAuthenticate) {
    // retrieve information about the contact with the provided id
    Contact contact = contactDao.getContactById(id);
    return Response
      .ok(contact)
      .build();
  }

   @POST
  public Response createContact(Contact contact, @Auth   Boolean isAuthenticated) throws URISyntaxException {
    // Validate the contact's data
    Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
    // Are there any constraint violations?
    if (violations.size() > 0) {
      // Validation errors occurred
      ArrayList<String> validationMessages = new ArrayList<String>();
      for (ConstraintViolation<Contact> violation : violations) {
validationMessages.add(violation.getPropertyPath().toString() +": " + violation.getMessage());
     }
     return Response
          .status(Status.BAD_REQUEST)
          .entity(validationMessages)
          .build();
    }
    else {
      // OK, no validation errors
      // Store the new contact
      int newContactId = contactDao.createContact(contact.getFirstName(), contact.getLastName(), contact.getPhone(), contact.getEmail());
      return Response.created(new URI(String.valueOf(newContactId))).build();
    }
  }
  
  @DELETE
  @Path("/{id}")
  public Response deleteContact(@PathParam("id") int id, @Auth   Boolean isAuthenticated) 
  {
    // delete the contact with the provided id
    contactDao.deleteContact(id);
    return Response.noContent().build();
  }	
	
  @PUT
  @Path("/{id}")
  public Response updateContact(@PathParam("id") int id, Contact contact,  @Auth Boolean isAuthenticated) {
    // Validate the updated data
    Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
    // Are there any constraint violations?
    if (violations.size() > 0) {
      // Validation errors occurred
      ArrayList<String> validationMessages = new ArrayList<String>();
      for (ConstraintViolation<Contact> violation : violations) {
		validationMessages.add(violation.getPropertyPath().toString() +": " + violation.getMessage());
      }
      return Response
            .status(Status.BAD_REQUEST)
            .entity(validationMessages)
            .build();
    }
    else {
      // No errors
      // update the contact with the provided ID
      contactDao.updateContact(id, contact.getFirstName(),contact.getLastName(), contact.getPhone(), contact.getEmail());
      return Response.ok(
          new Contact(id, contact.getFirstName(), contact.getLastName(),
          contact.getPhone(), contact.getEmail())).build();
    }
  }
  
}