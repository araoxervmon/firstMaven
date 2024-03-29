package com.dwbook.phonebook;
import javax.validation.constraints.Max;

import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import javax.validation.constraints.Max;
import io.dropwizard.db.DataSourceFactory;
import com.dwbook.phonebook.dao.ContactDAO;

public class PhonebookConfiguration extends Configuration {
  @JsonProperty
  private String message;
  @JsonProperty
  private DataSourceFactory database = new DataSourceFactory();
  
     public DataSourceFactory getdatabase() {
		return database;
  }
  

  @JsonProperty
  @Max(10)
  private int messageRepetitions;
  
  public String getMessage() {
    return message;
  }

  public int getMessageRepetitions() {
    return messageRepetitions;
  }
  
  @JsonProperty
  private String additionalMessage = "This is optional";
  public String getAdditionalMessage() 
  {
	return additionalMessage;
  }
  
}