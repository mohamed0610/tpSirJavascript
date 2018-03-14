# TP 3 de Systèmes d'informations répartis					
											
## Les objectifs de ce travail pratique sont les suivants :

1. Comprendre les bénéfices et les limites des bases nosql
2. Comprendre les différents types de bases nosql
3. Réaliser une application en utilisant une API comme Morphia proche de JPA en se plaçant dans un cadre classique de développement au dessus d’une base orientée document comme Mongo.
4. Comparer avec une base de données clé valeur haute performance comme Redis
							
							
## Sujet
Création d’une application simple qui utilise une base de données MongoDB

## Installation de la base de données MongoDB

Une fois que nous avons décompressé le fichier mongo-db qui est sur le share , on a créé un repertoire comme au-dessous

c:\mongodb\data
c:\mongodb\data\db

## Utilisation de morphia pour la connexion à la base de données

## Question 1
Quelles sont les limites d’une base données orientées document ?

### Réponse 1
MongoDB est particulièrement apprécié pour sa capacité à passer en mode distribué pour répartir le stockage et les traitements: nous verrons cela ultéieurement.

Elle a des limites qui sont les suivantes:

1. La disparition des jointures.
2. L'intégrité des données n'est plus garantie.
3. Les requêtes qu'on peut effectuer sur cette base sont moins riches qu'en SQL classique ce qui limite les requêtes complexes par rapport à un SGBDR. 

## Question 2
Quelles sont les types de données stockés dans Redis, que peut on faire comme types de requêtes ?
### Réponse 2

	
Redis est une base de données open source de type clefs-valeurs. Il permet de manipuler des types de données simples : chaînes de caractères, tableaux associatifs, listes, ensembles et ensembles ordonnés.
	
On peut faire des requêtes en lecture/ écriture avec des temps de réponse très rapide car une des principales caractéristiques de Redis est de conserver l'intégralité des données en RAM . Il est possible d'insérer, mettre à jour des clés-valeur mais aussi de les filtrer et de donner une durée de vie à une clé.							



#### La classe Person.java

La classe Person.java contient 3 variables, de type ObjectId avec une id identique pour chaque personne,une variable de type String pour le nom de la personne et une variable de type  Collection pour l'adresse de la personne.

```
	@Id
	private ObjectId id;
	private  String name;
	@Embedded
	private Collection<Address> addresses;

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
```
#### La classe Article.java

La classe Article.java contient 3 variables, de type ObjectId avec une id identique pour chaque article,une variable de type String pour le nom de l'article et une variable de type  Collection pour le propriétaire de l'article

```							
	private int stars;
	@Id
	private ObjectId id;
	private  String name;
	@Embedded
	private Collection<Person> personnes;						
```							
						
#### La classe Address.java

La classe Adress.java contient 1 variable de type ObjectId avec une id identique pour chaque adresse,4 variables de type String pour le nom de la rue,de la ville,du code postale et du pays.
```
@Id
private ObjectId id;	
private String street;
private String city;
private String postCode;
private String country;							
						
```						
#### La classe Snippet.java	
Pour la manipulation des données  nous utilisons la librarie Morphia:
Pour ce tp on utilise la base de donnée "my_database".
```
 Morphia morphia = new Morphia();    
 MongoClient mongo = new MongoClient();
 morphia.map(Person.class).map(Address.class);
 Datastore ds = morphia.createDatastore(mongo, "my_database");
```
##### Les chargement des données sur la base
```
Person p = new Person();
p.setName("Tintin");	
Address address = new Address();
address.setStreet("123 Some street");
address.setCity("Some city");
address.setPostCode("123 456");
address.setCountry("Some country");	   
ArrayList addresses = new ArrayList();
üaddresses.add(address);
p.setAddresses(addresses);
ds.save(p);
```
						
						
						
