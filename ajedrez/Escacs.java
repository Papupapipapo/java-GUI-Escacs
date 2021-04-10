package ajedrez;
import java.util.Scanner;

public class Escacs  {

	public static void main(String[] args) {
		System.setProperty("file.encoding", "UTF-8");
		Taulell taulellActual = new Taulell();
		
		Scanner teclado = new Scanner(System.in);
		String nomTorn;
		String posicioInicial;
		String posicioDesti;
		
		System.out.println("Benvingut als escacs!\nPer a moure peçes s'utilizara notació algebraica (a2, h3, c6..)\nEl joc acabara cuan mori algun dels dos reis");
		while(taulellActual.getGameStatus().equals("Playing")) {
			nomTorn = ((taulellActual.isTorn()) ?  "Blanques(Mayus)" : "Negres(Minus)"); //Segons de qui sigui el torn retornara un color o altres
			taulellActual.printTaulell();
			
			System.out.println("Torn de les " + nomTorn + "\nQuina fitxa vols moure?:");
			posicioInicial = teclado.next();
			
			System.out.println("A on la vols moure?:");
			posicioDesti = teclado.next();
			
			if(!taulellActual.moure(posicioInicial, posicioDesti)) {
				System.out.println("Moviment invalid");
			}else { //En cas de valid, li toca al altre jugador
				taulellActual.switchTorn();
				
			}
		}
		System.out.print("HAN GUANYAT EL JUGADOR AMB LES PECES ");
		if(taulellActual.getGameStatus().equals("WHITEWIN")){
			System.out.print("BLANQUES ");
		}else {
			System.out.print("NEGRES ");
		}
	
		teclado.close();
	}

}
