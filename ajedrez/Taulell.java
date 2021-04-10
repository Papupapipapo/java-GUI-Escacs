package ajedrez;

public class Taulell{
	private String gameStatus = "Playing";
	private  int pecesB = 16;
	private  int pecesN = 16;
	private boolean torn = true;
	
	
	public Fitxa taulell[] [] = new Fitxa[8] [8]; //Aqui contindrem totes les fitxes
	//Constructors
	Taulell() {
		this.inicialitzarTaulell(); //Cada vegada que creem un taulell es com que inicialitzem una partida
	}
	

	//---------------------------METODES DE TAULA----------------
	private void inicialitzarTaulell() {
		//Fitxes Negres
		taulell[0][0] = new Fitxa('t',false); 
        taulell[0][1] = new Fitxa('c',false);
        taulell[0][2] = new Fitxa('a',false); 
        taulell[0][3] = new Fitxa('q',false); 
        taulell[0][4] = new Fitxa('k',false); 
        taulell[0][5] = new Fitxa('a',false); 
        taulell[0][6] = new Fitxa('c',false); 
        taulell[0][7] = new Fitxa('t',false);
        
        //PEONS Negres
        taulell[1][0] = new Fitxa('p',false);
        taulell[1][1] = new Fitxa('p',false); 
        taulell[1][2] = new Fitxa('p',false); 
        taulell[1][3] = new Fitxa('p',false); 
        taulell[1][4] = new Fitxa('p',false); 
        taulell[1][5] = new Fitxa('p',false); 
        taulell[1][6] = new Fitxa('p',false); 
        taulell[1][7] = new Fitxa('p',false); 
  
        // Peces blanques
        taulell[7][0] = new Fitxa('T',true); 
        taulell[7][1] = new Fitxa('C',true);
        taulell[7][2] = new Fitxa('A',true); 
        taulell[7][3] = new Fitxa('Q',true); 
        taulell[7][4] = new Fitxa('K',true); 
        taulell[7][5] = new Fitxa('A',true); 
        taulell[7][6] = new Fitxa('C',true); 
        taulell[7][7] = new Fitxa('T',true); 
        
        
        //Peons blancs
        taulell[6][0] = new Fitxa('P',true); 
        taulell[6][1] = new Fitxa('P',true); 
        taulell[6][2] = new Fitxa('P',true); 
        taulell[6][3] = new Fitxa('P',true); 
        taulell[6][4] = new Fitxa('P',true);
        taulell[6][5] = new Fitxa('P',true); 
        taulell[6][6] = new Fitxa('P',true); 
        taulell[6][7] = new Fitxa('P',true); 
        
  
        // Inicialitzarem els espais en blanc
        for (int i = 2; i < 6; i++) { 
            for (int j = 0; j < 8; j++) { 
            	taulell[i][j] = null; 
            } 
        } 
        
	}
	public Fitxa mirarPosicio(int fila, int col) { //Simplement mirem una posicio
		return taulell [fila] [col];
	}
	
	public void printTaulell() {
		char lletres[] = {'a','b','c','d','e','f','g','h'};
		System.out.print("    ");
		for(int o = 0; o < lletres.length; o++) {
			System.out.print(lletres[o] + " ");
		}
		System.out.println();
		System.out.print("  ╔");
		
		for(int o = 0; o < taulell[0].length*2.1; o++) {
			System.out.print("═");
		}
		System.out.print("╗");
		System.out.println();
		for(int i = 0; i < taulell.length; i++) {
			System.out.print((i + 1) +" ║ ");
			for(int o = 0; o < taulell[0].length; o++) {
				if (taulell[i][o] != null) {
					System.out.print(taulell[i][o].getNomCurt() + " ");
            	} else {
            		System.out.print(". ");
            	}
				
			}
			System.out.print("║");
			System.out.println();
		}
		
		System.out.print("  ╚");
		for(int o = 0; o < taulell[0].length*2.1; o++) {
			System.out.print("═");
		}
		System.out.print("╝");
		System.out.println();
	}
	
	public void setNovaPosicio(Fitxa fitxaActual, int colInicial, int filaInicial,int colFinal, int filaFinal) { //Aqui definirem la nova posicio de la fitxa, deixant la posicio anterior com null
		Fitxa copiaFitxa = fitxaActual;
		this.taulell [colInicial] [filaInicial] = null;
		this.taulell [colFinal] [filaFinal] = copiaFitxa;
	}
	public String getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(String gameStatus) {
		this.gameStatus = gameStatus;
	}

	public  int getPecesB() {
		return pecesB;
	}

	public  void restarPecesB() {
		this.pecesB--;
	}

	public  int getPecesN() {
		return pecesN;
	}

	public void restarPecesN() {
		this.pecesN--;
	}
	public boolean isTorn() {
		return torn;
	}

	public void switchTorn() { //Servira per a saber de qui es el torn
		this.torn = !(this.torn);
	}
	
	
	
	//----------------------------METODES DE MOVIMENT-------------------------
	
	//Aquesta funcio filtrara tot el que li entra de texte sobretot que la notacio algebraica sigui correcte
		//Primer mirara la lletra si existeix dins del tauler i no se l'ha inventat
		//Segon comprova el numero complementari i mira que estigui en el rang de 0 i 7
		//Si es correcte, retornara true, sino arribara el false indicant que no ha pasat el filtre correctament

		private boolean filtreMoviment(String posicioInicial, String posicioDesti) {
			if(!posicioInicial.equals(posicioDesti)) { //Si son la mateixa posicio, no es realitzara 
					
				int max = 7;
				char equivalencies[] = {'a','b','c','d','e','f','g','h'};
				boolean inicial = false;
				boolean desti = false;
				 posicioInicial = posicioInicial.toLowerCase();
				 posicioDesti = posicioDesti.toLowerCase();
				 
				for(int i = 0; i < equivalencies.length;i++) {
					if(posicioInicial.indexOf(equivalencies[i]) != -1) {
						inicial= true;
					}
					if(posicioDesti.indexOf(equivalencies[i]) != -1) {
						desti = true;
					}
					
				}
				if(inicial && desti){
					
					int filaInicial = Integer.parseInt(posicioInicial.substring(1));
					int filaFinal = Integer.parseInt(posicioInicial.substring(1));
					
					if ((filaInicial >= 0) && (filaInicial - 1<= max ) && (filaFinal >= 0) && (filaFinal - 1 <= max )) {
							return true;
						}
					}
			}
			return false;
			
		}
		
		//Moure es la accio principal, la que sempre invocarem per a moure les nostres fitxes
		//Li donarem el taulell on estem jugant, la notacio algebraica inicial i despres a on volem anar
		//D'alli anirem treient tot
		
		public boolean moure(String posicioInicial, String posicioDesti) {
				if(filtreMoviment(posicioInicial,posicioDesti)) {  //Abans que se li pugui donar haura de pasar un filtre de errors
				
				int filaInicial = (Integer.parseInt(posicioInicial.substring(1)) - 1); //S'ha de restar ja que aixi el jugador pot posar comodament el numero sense que comenci per 0
				int filaFinal = (Integer.parseInt(posicioDesti.substring(1))- 1);
				int columnaInicial = numeroEquivalent(posicioInicial.charAt(0)); //Buscara a la funcio quin es el equivalent de la lletra
				int columnaFinal = numeroEquivalent(posicioDesti.charAt(0));		
				boolean kill = false;
				
				Fitxa fitxaActual = mirarPosicio(filaInicial, columnaInicial); //Aixi tindrem la nostre fitxa que estem utilitzant i manegarem
					
					if(fitxaActual == null) { //Primer que tot que no sigui null, ja que no te sentit diure de moure un espai en blanc
						return false;
					}
				
					if(fitxaActual.isColor() != isTorn()) { //Comprovem que la nostra fitxa agafada sigui del color corresponent al torn
						return false;
					}
					
				Fitxa fitxaDesti = mirarPosicio(filaFinal, columnaFinal); //El desti 

				
				if(fitxaDesti != null) { //Aqui comprovem si la fitxa es null, per a saber si sera un moviment invalid o un d'atac
					if(fitxaActual.isColor() == fitxaDesti.isColor()){ //Si el color es el mateix no es realitzara el moviment, no es pot posar al damunt de la mateix color
						return false;
					}else { //Si no son del mateix color significa que sera un moviment per a matar
						kill = true;
					}		
				}
				char nomCurtConvertit = Character.toLowerCase(fitxaActual.getNomCurt()); //Convertirem a lowercase el nom de fitxa, ja que axii no tindrem que fer molts equalIgnoreCase
				
				int columnaResultat = Math.abs(columnaInicial- columnaFinal); //Ens servira per a calculs unidireccionals
				int filaResultat = Math.abs(filaInicial- filaFinal);
				
				if(nomCurtConvertit == 't') { //Torre
					if((columnaResultat >= 1 && filaResultat == 0)||(columnaResultat == 0 && filaResultat >= 1)) { //Que es mogui en fila
						if(comprovarRecorregut(filaInicial,columnaInicial,filaFinal,columnaFinal)) { //Comprova que el cami esta lliure
							 if(kill) {																		//Que si es un moviment de matar que activi el restarFitxa
									restarFitxa(fitxaActual.isColor(),fitxaDesti);
								}
								setNovaPosicio(fitxaActual,filaInicial,columnaInicial,filaFinal,columnaFinal); //Si es valid moura la fitxa a la nova posicio
							return true;
						}
					}
				} else if(nomCurtConvertit == 'c') { //Caballo
					 if(columnaResultat * filaResultat == 2) { //Es mou en L
						 if(kill) {
								restarFitxa(fitxaActual.isColor(),fitxaDesti);
							}
							setNovaPosicio(fitxaActual,filaInicial,columnaInicial,filaFinal,columnaFinal);
						return true;
					 };
					 
				} else if(nomCurtConvertit == 'a') { //Alfil
					if(columnaResultat == filaResultat) { //Es mou en diagonal
						if(comprovarRecorregut(filaInicial,columnaInicial,filaFinal,columnaFinal)) { //Comprova que el cami esta lliure
							 if(kill) {																		//Que si es un moviment de matar que activi el restarFitxa
									restarFitxa(fitxaActual.isColor(),fitxaDesti);
								}
								setNovaPosicio(fitxaActual,filaInicial,columnaInicial,filaFinal,columnaFinal); //Si es valid moura la fitxa a la nova posicio
							return true;
						}
					}
					
				} else if(nomCurtConvertit == 'q') { //Reina
					if((columnaResultat == filaResultat)||(columnaResultat >= 1 && filaResultat == 0)||(columnaResultat == 0 && filaResultat >= 1)) { //Es mou de totes maneres
						if(comprovarRecorregut(filaInicial,columnaInicial,filaFinal,columnaFinal)) { //Comprova que el cami esta lliure
							 if(kill) {																		//Que si es un moviment de matar que activi el restarFitxa
									restarFitxa(fitxaActual.isColor(),fitxaDesti);
								}
								setNovaPosicio(fitxaActual,filaInicial,columnaInicial,filaFinal,columnaFinal); //Si es valid moura la fitxa a la nova posicio
							return true;
						}
						
					}
					
				} else if(nomCurtConvertit == 'k') { //Rey
					
					if((columnaResultat == 1 && filaResultat == 1) || (columnaResultat == 1 && filaResultat == 0)||(columnaResultat == 0 && filaResultat == 1) ) { //Solament es moura en espais de 1
						 if(kill) {
								restarFitxa(fitxaActual.isColor(),fitxaDesti);
							}
							setNovaPosicio(fitxaActual,filaInicial,columnaInicial,filaFinal,columnaFinal);
						return true;
					}else if (columnaInicial == 4 && (filaInicial == 0 || filaInicial == 7 )){ //Aixi podrem enroscar
						//Primer descobrim la orientacio, despres d'alli podrem saber quina torre comprovar que estigui on li demanem
						if ((columnaFinal == (columnaInicial + 2)) && filaResultat == 0) {
							Fitxa posibleTorre = mirarPosicio(filaInicial,7);
							if (Character.toLowerCase(posibleTorre.getNomCurt()) == 't') {
								setNovaPosicio(fitxaActual,filaInicial,columnaInicial,filaFinal,columnaFinal);
								setNovaPosicio(posibleTorre,filaInicial,7,filaFinal,5);
								return true;
							}
						}else if ((columnaFinal == (columnaInicial - 2)) && filaResultat == 0) {
							Fitxa posibleTorre = mirarPosicio(filaInicial,0);
							if (Character.toLowerCase(posibleTorre.getNomCurt()) == 't') {
								setNovaPosicio(fitxaActual,filaInicial,columnaInicial,filaFinal,columnaFinal);
								setNovaPosicio(posibleTorre,filaInicial,0,filaFinal, 3);
								return true;
							}
						}
						
					}
					
				} else if(nomCurtConvertit == 'p') {//Peon
					if(fitxaActual.isColor()) {//Peon blanco
						//Per el moviment hi ha dues parts, el primer que sera solament si vol moures, avanÃ§ara cap al enemic en vertical i si hi ha un enemic no es podra moure a aquell lloc
						//tambe que si esta a la primera posicio del peo que pugui moures 2 files enlloc de solament una
						if( (fitxaDesti == null) && ((filaFinal == (filaInicial - 1)) && columnaResultat == 0 ) || ((filaFinal == (filaInicial - 2)) && filaInicial == 6 ) ) { 
								setNovaPosicio(fitxaActual,filaInicial,columnaInicial,filaFinal,columnaFinal);
							return true;
						}else if((columnaResultat == 1 && filaFinal == (filaInicial - 1)) && kill )  {
							
							//Aqui anira a matar el persontaje ja que va cap a una ficha enemiga, lo cual fa que es pugui moure en diagonal
							restarFitxa(fitxaActual.isColor(),fitxaDesti);
							setNovaPosicio(fitxaActual,filaInicial,columnaInicial,filaFinal,columnaFinal);
							return true;
						}
					}else { //Peon negro
						
						if( (fitxaDesti == null) && ((filaFinal == (filaInicial + 1)) && columnaResultat == 0 ) || ((filaFinal == (filaInicial + 2)) && filaInicial == 1 )) { 
								setNovaPosicio(fitxaActual,filaInicial,columnaInicial,filaFinal,columnaFinal);
							return true;
						}else if((columnaResultat == 1 && filaFinal == (filaInicial + 1)) && kill ) { //Solament funcionara si es un atac i si es en diagonal
							restarFitxa(fitxaActual.isColor(),fitxaDesti);
							setNovaPosicio(fitxaActual,filaInicial,columnaInicial,filaFinal,columnaFinal);
							return true;
						}
					}
				} 
			}
			return false;
		}
		//Mirara tot el recorregut que fara la fitxa i comprova que no es choca, solament sera fet per la torre, alfil i reina
		private boolean comprovarRecorregut(int filaInicial, int colInicial, int filaFinal , int colFinal) { 
			Fitxa fitxaActual;
			
			int filaMajor;
			int filaMenor;
			int colMajor;
			int colMenor;
			
			if(filaFinal > filaInicial) {
				filaMajor = filaFinal ;
				filaMenor = filaInicial;
			}else {
				filaMajor = filaInicial ;
				filaMenor = filaFinal;
			}
			
			if(colFinal > colInicial) {
				colMajor = colFinal;
				colMenor = colInicial;
			}else {
				 colMajor = colInicial;
				 colMenor = colFinal;
			}
			
			//Una vegada definits quin es el valor menor y major, direm de mirar mes enlla de la primera posicio i que acabi una abans, aixi evitant les fitxes que ja sabem,
			//i que vagi mirant que estigui null el cami, indicant que esta buit
			
			//Segons com es mogui no haurem de modificar la columa o la linea, solament la que varia, pero en diagonal si que mirarem les dues
			
			if(filaInicial == filaFinal) { //Horitzontal
				for(int columnaActual = colMenor +1 ; columnaActual <= (colMajor - 1 ); columnaActual++ ) {
					fitxaActual = mirarPosicio(filaInicial, columnaActual);
					if(fitxaActual != null) {
						return false;
					}
				}
			}else if(colInicial == colFinal) { //Vertical
				
				for(int filaActual = filaMenor + 1  ; filaActual <= (filaMajor - 1); filaActual++ ) {
					fitxaActual = mirarPosicio(filaActual, colInicial);
					if(fitxaActual != null) {
						return false;
					}
				}
			}else { //Diagonal
				
				if(colFinal > colInicial) {
					colMajor = colFinal;
					 colMenor = colInicial;
				}else {
					 
					 colMajor = colInicial;
					colMenor = colFinal;
				}
				
				for(int filaActual = filaMenor + 1; filaActual <= (filaMajor - 1); filaActual++ ) {
					for(int columnaActual = colMenor + 1  ; columnaActual <= (colMajor - 1); columnaActual++ ) {
						fitxaActual = mirarPosicio(filaActual, columnaActual);
						if(fitxaActual != null) {
							return false;
						}
					}
				}
			}		
			return true;
		}
		
		//Aquesta funcio ens servira per a treure fitxes del contar de peces de cadascun i per a veure si guanyem
		private void restarFitxa(boolean color,Fitxa fitxaRival) {
			char nomCurtConvertitEnemic = Character.toLowerCase(fitxaRival.getNomCurt());
			
			if(nomCurtConvertitEnemic == 'k') {
				if(color) {
					setGameStatus("WHITEWIN");
				}else {
					setGameStatus("BLACKWIN");
				}
				return;
			}
			
			if(color) { //blanc
				restarPecesN();
				
			}else {
				restarPecesB();
			}
		}
		
		//Buscara la equivalencia per a cada lletra a numero, per a poder buscar dins del array
		private int numeroEquivalent(char columLLetra) {
			int columna = 0;
			char equivalencies[] = {'a','b','c','d','e','f','g','h'};
			
			for(int i = 0; i < equivalencies.length;i++) {
				if(equivalencies[i] == columLLetra) {
					columna = i;
				}
			}
			return columna;
		}
		public String lletraEquivalent(int numCol) {
			String equivalencies[] = {"a","b","c","d","e","f","g","h"};
			
			
			return equivalencies[(numCol)];
		}
}
