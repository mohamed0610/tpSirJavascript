# TP 5 de Systemes d'informations répartis

## Objectif

1.L’objectif de ce TP consiste à créer une application Web pour faire du dessin vectoriel, i.e. pour dessiner des rectangles, des lignes, ainsi que définir leur couleur et leur épaisseur de trait en utilisant le principe MVC avec JavaScript. 

2. Voir l interet d un framework comme AngularJS par la suite.  

## La rÃ©alisation de TP

L objectif de ce TP consiste Ã  creer une application Web pour faire un dessin vectoriel:dessiner des rectangles, des lignes avec leurs couleurs et épaisseurs. La figure ci-dessous montre l'état de l'application et donc l'objectif Ã  atteindre.


## La partie MVC 

MVC (Modéle-Vue-Controleur) est un patron d'architecture IHM.La figure ci-dessous nous montre le modele de ce parton.

![model4](https://cloud.githubusercontent.com/assets/15005875/25064249/d7f55d38-21f6-11e7-85fc-8ec9a2cfd9de.png)


## La partie Javascript

Javascript est actuellement le langage de programmation de scripts utilisÃ© pour apporter du comportement Ã  des pages Web. 

## Interaction : le glisser-dÃ©poser (Drag-n-Drop)

Pour La crÃ©ation d'un Dnd nous avons utilisÃ© un canvas et un interacteur.

1. Nous avons defini 4 variables pour les coordonnÃ©es initiales et finals. 
```
this.cordinitx = 0;
this.cordinity = 0;
this.cordfinalx = 0;
this.cordfinaly = 0;
```

2. On a dÃ©clarÃ© 3 fonction.

```	
this.maFctGerantLaPression = function(evt) {	
  this.cordinitx = getMousePosition(canvas,evt).x;
  this.cordinity= getMousePosition(canvas,evt).y;
  console.log("presser");
  console.log(this.cordinitx);
  console.log(this.cordinity);	
	}.bind(this);
  .
  .
  .
```
3. Nous avons liÃ©s ces fonctions Ã  des evenements(listener)                                                          
```
canvas.addEventListener('mousedown', this.maFctGerantLaPression, false);
canvas.addEventListener('mouseup', this.maFctGerantLeRelachement, false);
canvas.addEventListener('mousemove',this.maFctGerantLeDeplacement, false);	
```
4. On a appelé dans chacune de ces 3 fonctions console.log pour afficher dans la console Javascript de notre navigateur les coordonnées de chaque événement lors de l'exécution de l'interaction.                                                                                    

## Le modéle

Nous avons dÃ©fini 4 classes; 
1. Line
2. Rectangle
3. Form
4. Drawing

Puis qu'une forme possÃ¨de une couleur et une Ã©paisseur de trait.
```
function Form(epaisseur, couleur) {
    this.epaisseur=epaisseur;
    this.couleur=couleur;
};
```

Un rectangle possede des coordonnÃ©es de son point haut-gauche, une largeur et une hauteur. 
```
function RectangleY(X, , largeur, hauteur, epaisseur, couleur) {
    Form.call(this, epaisseur, couleur);
    this.X=X;
    this.Y=Y;
    this.largeur = largeur;
    this.hauteur = hauteur;
};
```
Une ligne possÃ¨de les coordonnÃ©es de ses deux points.
```
function Line(x1, y1, x2, y2, epaisseur, couleur) {
    Form.call(this, epaisseur, couleur);
    this.x1=x1;
    this.y1=y1;
    this.x2=x2;
    this.y2=y2;
};
```

La classe Drawing nous permet de crÃ©er ou enlever une form.
```
function Drawing() {
    //creer un array forms
    this.forms = new Array();
    //MÃ©thode pour l'ajout dune forme dans le tableau
    this.addForm = function(form) {
        this.forms.push(form);
    };
    // enlever un form Ã  partir d'index
    this.removeForm = function(index) {
        this.forms.splice(index,1);
    };
    this.getForms = function(){
    	return forms;
    }
};
```

## La vue

On a fait l'implementation de 4 fonctions dans le fichier de view.js pour ajouter les fonctions d'affichage (fonction paint) dans chacune des classes.


## Le contrÃ´leur

Pour la partie contrÃ´leur nous avons fait; 
1. L'implementation de la classe l'interacteur qui sera un crayon et pour notifier l'interacteur d'une modification de l'interaction.

```
function Pencil(ctx, drawing, canvas) {
	this.currEditingMode = editingMode.line;
	this.currLineWidth = 5;
	this.currColour = '#000000';
	this.currentShape = 0;
  .
  .
  .
this.onInteractionStart = function(DnD) {
		// recuperer les bouton de canvas.html
		var butRect = document.getElementById('butRect');
		var butLine = document.getElementById('butLine');
		var spinnerWidth = document.getElementById('spinnerWidth');
		var colour = document.getElementById('colour');

		this.currLineWidth = spinnerWidth.value;
		this.currColour = colour.value;
		
		// test sur la forme sÃ©lectionnÃ©e
		if (butRect.checked) {
			this.currEditingMode = editingMode.rect;
		} else if (butLine.checked) {
			this.currEditingMode = editingMode.line;
		}
    .
    .
    .
```

2. Pour la creation des differents formes 
```
		// test sur la forme sÃ©lectionnÃ©e
		if (butRect.checked) {
			this.currEditingMode = editingMode.rect;
		} else if (butLine.checked) {
			this.currEditingMode = editingMode.line;
		}

		// creation de la forme selectionnÃ©e
		switch (this.currEditingMode) {

		// rectangle
		case editingMode.rect: {

			var largeur = DnD.cordfinalx - DnD.cordinitx;
			var hauteur =  DnD.cordfinaly - DnD.cordinity;
			this.currentShape = new Rectangle(DnD.cordinitx, DnD.cordinity,largeur, hauteur, this.currLineWidth, this.currColour);
			break;
		}
			// CrÃ©ation de la ligne
		case editingMode.line: {

			this.currentShape = new Line(DnD.cordinitx, DnD.cordinity,DnD.cordfinalx, DnD.cordfinaly, this.currLineWidth, this.currColour);
			break;
		}
		}

	}.bind(this);
```


3. Pour reinitialiser le canvas 
```
ctx.clearRect(0, 0, canvas.width, canvas.height);
drawing.paint(ctx);
this.currentShape.paint(ctx);
```

## Liste des modifications

1. Nous avons rajoutÃ© la fonction "updateShapeList" dans le fichier de view.js 

```
Drawing.prototype.updateShapeList = function(form){
	  //RÃ©cupÃ©ration de l'Ã©lÃ©ment shapelist
    var myShapeList = document.getElementById('shapeList');
}
```
2. On a fait la modification de button pour "updateShapeList" .  
