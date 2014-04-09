import java.io.IOException;
import java.util.Scanner;

/***
 * 
 * @author Rokibul Uddin
 *
 */
public class MatriceInversa {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("N dimensione aXa:  ");
		int righe = sc.nextInt();
		
		Matrice m = new Matrice(righe, righe);
		m.askForInput();
		System.out.println();
		System.out.println("Matrice inversa:");
		m.calcolaInversa();
	}

}
