package fr.TpMorphia.TpMorphia;

import java.util.Collection;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity
public class Article {
	private int stars;
	@Id
	private ObjectId id;
	private  String name;
	@Embedded
	private Collection<Person> personnes;
	
	public Article() {
		
		// TODO Auto-generated constructor stub
	}
	
	
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
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

}
