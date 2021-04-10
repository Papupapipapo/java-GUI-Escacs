package ajedrez;

public class Fitxa{ //Les fitxes solament contindra quin color son i quin nom tenen, es el unic que hem de saber de elles
	private char nomCurt; // Nom per a mostrar en pantalla
	private boolean color; // Si es 0 es blanca, 1 es negra
	  
	//Constructors 
	Fitxa(char nomCurt, boolean color) {
	    this.nomCurt = nomCurt;
	    this.color = color;
	  } 
	// Methods

	public char getNomCurt() {
		return nomCurt;
	}

	public boolean isColor() {
		return color;
	}
}
