import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Operacion: ");
		Scanner s = new Scanner(in.readLine());
		try {
			System.out.println(parseSet(s));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Set<Integer> parseSet(Scanner s) throws Exception {
		Set<Integer> set = new HashSet<Integer>();
		s.useDelimiter("[,\\s\\]]"); //o bien la coma \\s(cualquier espacio en blanco)
		int estado = 0;
		while (estado != 4) {
			switch (estado) {
			case 0:
				try {
					s.skip("\\s*\\[\\s*");//para omitir varios espacios en blanco delante y detrás del primer corchete, este método lanza una excepción sino se cumple
					estado = 1;
				} catch (NoSuchElementException e){ //lanzamos una checked (unchecked no estas obligado a capturarla y si se escribe mal la operación se para el programa (runtime:un checked, exception: checked)) 
					throw new Exception("Se esperaba '['");
				}
				break;
			case 1:
				try {
					s.skip("\\s*\\]\\s*");
					estado = 4;
				} catch (NoSuchElementException e){  
					try {
						set.add(s.nextInt());
						estado = 2;
					} catch (NoSuchElementException e2) {
						throw new Exception("Se esperaba un numero o ']'");
					}
				}
				break;
			case 2:
				try {
					s.skip("\\s*\\,\\s*");
					estado = 3;
				} catch (NoSuchElementException e1){  
					try {
						s.skip("\\s*\\]\\s*");
						estado = 4;
					} catch (NoSuchElementException e2) {
						throw new Exception("Se esperaba una ',' o ']'");
					}
				}
				break;
			case 3:
				try {
					set.add(s.nextInt());
					estado = 2;
				} catch (NoSuchElementException e2) {
					throw new Exception("Se esperaba un numero");
				}
				break;
			}
		}
		return set;
	}
}
