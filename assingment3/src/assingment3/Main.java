package assingment3;
public class Main {
	public static void main(String[] args) {
		Elettore io = new Elettore("Mario", "Rossi", 10, 12, 1985, "italia", "San Giuliano Terme", 'M', "RSSMRA85T10A562S");
		io.esprimi_voto();
		System.out.println(io);
		//System.out.println("Codice fiscale valido: " + io.validateTaxCode());
		System.out.println("Maggiorenne: " + Elettore.isAdult(10, 12, 1985));
	}
}
