package fr.TpMorphia.TpMorphia;

import java.rmi.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

public class Snippet {
	public static void main( String[] args ) throws UnknownHostException
	{
	    Morphia morphia = new Morphia();    
	    MongoClient mongo = new MongoClient();
	    morphia.map(Person.class).map(Address.class);
	    Datastore ds = morphia.createDatastore(mongo, "my_database");
	    
	    Person p = new Person();
	
	    p.setName("Tintin");
	
	    Address address = new Address();
	    address.setStreet("123 Some street");
	    address.setCity("Some city");
	    address.setPostCode("123 456");
	    address.setCountry("Some country");
	    //set address
	    ArrayList addresses = new ArrayList();
	    addresses.add(address);
	    p.setAddresses(addresses);
	    // Save the POJO
	    ds.save(p);
	   // for (Person e : ds.find(Person.class))
	       // System.err.println(e);
	
	}
	
}

