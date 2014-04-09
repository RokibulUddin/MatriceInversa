import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.math3.fraction.Fraction;
import org.apache.commons.math3.fraction.FractionFormat;

/***
 * 
 * @author Rokibul Uddin
 *
 */
public class Matrice {
	private int righe;
	private int colonne;
	private Fraction[][] A;
	private Fraction[][] B; // estesa
	private Fraction[][] inversa;
	private class Point{
		public int x;
		public int y;
		public Point(){
			x = 0;
			y = 0;
		}
		public void increment(){
			this.x++;
			this.y++;
		}
	}
	
	public Matrice(int righe, int colonne){
		this.righe = righe;
		this.colonne = colonne;
		A = new Fraction[righe][colonne];
	}
	
	public void askForInput() throws IOException{
		Scanner sc = new Scanner(System.in);
		FractionFormat ff = new FractionFormat();
		
		for(int i=0; i<righe; i++){
			System.out.print("Riga " + (i+1) + " :  ");
			String input = sc.nextLine();
			String part[] = input.trim().split(" ", colonne);
			if(part[colonne-1].contains(" ")){
				part[colonne-1] = part[colonne-1].split(" ")[0];
			}
			for(int j=0; j<colonne; j++){
				A[i][j] = ff.parse(part[j]);
			}
		}		
	}
	
	public void calcolaInversa(){
		int colonneB = colonne*2;
		B = new Fraction[righe][colonneB];
		initiateB(B);
		
		Point pivot = new Point();
		
		for(int i=0; i<righe; i++){
			if(i == pivot.y){
				elaboraColonna(B, pivot);
				pivot.increment();
			}
		}
		
		inversa = new Fraction[righe][colonne];
		for(int i=0; i<righe; i++){
			for(int j=0; j<colonne; j++){
				inversa[i][j] = B[i][colonne+j];
			}
		}
		
		print(inversa);
	}	
	
	private void elaboraColonna(Fraction[][] B, Point p){
		Fraction[] riga = calcolaRigaPivot(B, p);
		for(int i=0; i<righe; i++){
			if(i == p.y){
				continue;
			}else{
				calcolaRiga(B[i], riga, p.x);
			}
		}
	}
	
	private void calcolaRiga(Fraction[] B, Fraction[] riga, int x) {
		Fraction n = B[x].negate();
		for(int i=0; i<riga.length; i++){
			B[i] = n.multiply(riga[i]).add(B[i]);
		}
	}

	private Fraction[] calcolaRigaPivot(Fraction[][] B, Point p) {
		Fraction divisore = B[p.y][p.x];
		for(int i=0; i<B[0].length; i++){
			try{
				B[p.y][i] = B[p.y][i].divide(divisore);
			}catch(Exception e){
				System.err.println("Determinante = 0!");
				System.exit(1);
			}
		}
		return B[p.y];
	}

	private void initiateB(Fraction[][] B){
		int onePosX = colonne;
		int onePosY = 0;
		for(int i=0; i<righe; i++){
			for(int j=0; j<B[0].length; j++){
				if(j < colonne){
					B[i][j] = A[i][j];
				}else if((j == onePosX) && (i == onePosY)){
					B[i][j] = new Fraction(1);
					onePosX++;
					onePosY++;
				}else{
					B[i][j] = new Fraction(0);
				}
			}
		}
	}
	
	private void print(Fraction[][] A){
		for(int i=0; i<righe; i++){
			for(int j=0; j<A[0].length; j++){
				System.out.printf("%10s", A[i][j]);
			}
			System.out.println();
		}		
	}
	
	
	public void print(){		
		print(A);		
	}

}
