
package com.dwbook.phonebook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.dwbook.phonebook.PhonebookAuthenticator;
import com.dwbook.phonebook.PhonebookConfiguration;
import com.dwbook.phonebook.resources.ContactResource;
import org.skife.jdbi.v2.DBI;
import io.dropwizard.jdbi.DBIFactory;
import com.sun.jersey.api.client.Client;
import io.dropwizard.client.JerseyClientBuilder;
import com.dwbook.phonebook.resources.ClientResource;
import io.dropwizard.auth.basic.BasicAuthProvider;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import io.dropwizard.views.ViewBundle;

/**
 * Dropwizard web service
 *
 */
public class App extends Application<PhonebookConfiguration>
{
	private static final Logger LOGGER =LoggerFactory.getLogger(App.class);
	@Override
	public void initialize(Bootstrap<PhonebookConfiguration> b) {
	      b.addBundle(new ViewBundle());
    }
	 // build the client and add the resource to the environment
	  
	  
	@Override
	public void run(PhonebookConfiguration c, Environment e)throws Exception {
		LOGGER.info("Method App#run() called");
		for (int i=0; i < c.getMessageRepetitions(); i++) {
			System.out.println(c.getMessage());
		}
		System.out.println(c.getAdditionalMessage());
    
		// Create a DBI factory and build a JDBI instance
		final DBIFactory factory = new DBIFactory();
		final DBI jdbi = factory
       .build(e, c.getdatabase(), "hsqldb");
		// Add the resource to the environment
		e.jersey().register(new ContactResource(jdbi, e.getValidator()));
		
	    final Client client = new JerseyClientBuilder(e).using(e).build("REST Client");
	    //username and psswd fetched from user table
	    client.addFilter(new HTTPBasicAuthFilter("abhilash", "abhilash"));
		  e.jersey().register(new ClientResource(client));
		// Register the authenticator with the environment
		  e.jersey().register(new BasicAuthProvider<Boolean>(
	      new PhonebookAuthenticator(jdbi), "Web Service Realm"));
	}
	
    public static void main( String[] args ) throws Exception
    {
        new App().run(args);
    }
}
