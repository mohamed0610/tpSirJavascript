package fr.TpMorphia.TpMorphia;

import java.util.Collection;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;
@Entity
public class Person {
	@Id
	private ObjectId id;
	private  String name;
	@Embedded
	private Collection<Address> addresses;
	public Person() {
	
		// TODO Auto-generated constructor stub
	}


	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Collection<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(Collection<Address> addresses) {
		this.addresses = addresses;
	}
}
