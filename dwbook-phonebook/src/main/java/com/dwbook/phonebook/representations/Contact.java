package com.dwbook.phonebook.representations;
import io.dropwizard.validation.ValidationMethod;

import org.hibernate.validator.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Contact {
  private final int id;
  @NotBlank @Length(min=2, max=255)
  private final String firstName;
  @NotBlank @Length(min=2, max=255)
  private final String lastName;
  @NotBlank @Length(min=2, max=30)
  private final String phone;
  
  @NotBlank
  @Length(min=2, max=255)
  private final String email;
  
  public Contact() {
    this.id= 0;
    this.firstName = null;
    this.lastName = null;
    this.phone = null;
	this.email = null;
  }
  
  public Contact(int id, String firstName, String lastName,String phone,String email) {
    this.id= id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phone = phone;
	this.email = email;
  }
   
  public int getId() {
    return id;
  }
  public String getFirstName() {
    return firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public String getPhone() {
    return phone;
  }
  public String getEmail() {
	return email;
  }
  @JsonIgnore 
  @ValidationMethod(message="John Doe is not a valid person!")
  public boolean isValidPerson() {
	  if (firstName.equals("John") && lastName.equals("Doe")) {
	    return false;
	  }
	  else {
	    return true;
	  }
  }
}