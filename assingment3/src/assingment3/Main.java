package assingment3;

public class Main {

	public static void main(String[] args) {
		Elettore io = new Elettore("Rossi", "Mario", 10, 12, 1985, "italia", "San Giuliano Terme", 'M', "RSSMRA85T10A562S");
		System.out.println(io);
		io.esprimi_voto();
	}

}
