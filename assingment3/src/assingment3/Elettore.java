package assingment3;
import java.sql.Date;
import java.util.regex.Pattern;

public class Elettore {
	
	public static void main(String[] args) {
		//Caso nome null
		//Elettore e = new Elettore(null, "Rossi", 10, 12, 1995, "italia", "San Giuliano Terme", 'M', "RSSMRA95T10A562S");
		
		
		//Caso genere elettore non valido
		//Elettore e = new Elettore(null, "Rossi", 10, 12, 1995, "italia", "San Giuliano Terme", '4', "RSSMRA95T10A562S");
		
		//Caso cognome null
		//Elettore e = new Elettore("Mario", null, 10, 12, 1995, "italia", "San Giuliano Terme", 'M', "RSSMRA95T10A562S");
		
		
		//Caso elettore non maggiorenne
		//Elettore e = new Elettore("Mario", "Rossi", 10, 12, 2020, "italia", "San Giuliano Terme", 'M', "RSSMRA20T10A562S");
		
		
		//Caso elettore che vota piu' di una volta
		/*Elettore e = new Elettore("Mario", "Rossi", 10, 12, 1995, "italia", "San Giuliano Terme", 'M', "RSSMRA95T10A562S");
		  e.esprimi_voto();
		  e.esprimi_voto();
		 */
		
		
		//Caso nascita in Italia ma comune non specificato
		//Elettore e = new Elettore("Mario", "Rossi", 10, 12, 1995, "italia", null, 'M', "RSSMRA95T10A562S");
		
		
		//Caso data di nascita successiva alla data corrente
		//Elettore e = new Elettore("Mario", "Rossi", 10, 12, 2022, "italia", "San Giuliano Terme", 'M', "RSSMRA22T10A562S");
		
		
		//Caso codice fiscale non valido
		//Elettore e1 = new Elettore("Mario", "Rossi", 10, 12, 1995, "italia", "San Giuliano Terme", 'M', "RSSMRA95T10A5624");
		//Elettore e2 = new Elettore("Mario", "Rossi", 10, 12, 1995, "italia", "San Giuliano Terme", 'M', "RSSMRI95T10A562S");
	}
	
	private /*@ spec_public @*/ final String nome, cognome;
	private /*@ spec_public @*/ int gg, mm, aa;
	private /*@ spec_public @*/ final String nazione, comune; 
	private /*@ spec_public @*/ char sesso;
	private /*@ spec_public @*/ final char [] code;
	private /*@ spec_public @*/ boolean voto;
	
	/*@ public invariant nome != null && cognome != null && (sesso == 'M' || sesso == 'F') && (equalStrings(nazione, "italia") ==> comune != null) && (validDate(gg, mm, aa)) && validateTaxCode();@*/
	
	public Elettore(String nome, String cognome, int gg, int mm, int aa, String nazione, String comune, char sesso, String code){
		this.nome = nome;
		this.cognome = cognome;
		this.gg = gg;
		this.mm = mm;
		this.aa = aa;
		this.nazione = nazione;
		this.comune = comune;
		this.sesso = sesso;
		this.code = toCharArray(code);
		voto = false;
	}
	
	//@ requires (voto == false && isAdult(gg, mm, aa));
	//@ ensures (voto == true);
	public void esprimi_voto(){
		voto = true;
		return;
	}
	
	/**
	 * Genera i primi 11 caratteri del codice fiscale a partire dai dati anagrafici forniti come argomento.
	 */
	public static String generateTaxCode(String name, String surname, int gg, int mm, int aa, char sesso){
		surname = surname.toUpperCase(); //Conterrà il cognome formattato per il codice fiscale
		name = name.toUpperCase(); //Conterrà il nome formattato per il codice fiscale
		
		//primi tre caratteri
		String surname_cons = removeVowels(surname);
		String surname_vo = removeConsonants(surname);
		if(surname_cons.length() > 3) 
			surname_cons= surname_cons.substring(0, 3); 
		int j = 0;
		while(surname_cons.length() < 3){
			if(j < surname_vo.length())
				surname_cons += surname_vo.charAt(j++);
			else
				surname_cons += "X";
		}
		
		//successivi 3 caratteri
		String name_cons = removeVowels(name);
		String name_vo = removeConsonants(name);
		if(name_cons.length() > 3)
			name_cons = name_cons.charAt(0) + "" + name_cons.charAt(2) + "" + name_cons.charAt(3);
		j = 0;
		while(name_cons.length() < 3){
			if(j < name_vo.length())
				name_cons += name_vo.charAt(j++) ;
			else
				name_cons += "X";
		}
		
		//2 caratteri per lánno
		String year = String.valueOf(aa).substring(2); //Conterrà l'anno formattato per il codice fiscale

		//lettera per il mese
		char month = ' ';
		switch(mm){
			case 1:
				month = 'A';
				break;
			case 2:
				month = 'B';
				break;
			case 3:
				month = 'C';
				break;
			case 4:
				month = 'D';
				break;
			case 5:
				month = 'E';
				break;
			case 6:
				month = 'H';
				break;
			case 7:
				month = 'L';
				break;
			case 8:
				month = 'M';
				break;
			case 9:
				month = 'P';
				break;
			case 10:
				month = 'R';
				break;
			case 11:
				month = 'S';
				break;
			case 12:
				month = 'T';
		}
		
		//due caratteri per il giorno di nascita
		String day = "";
		if(sesso == 'M'){
			if(gg < 10) day += "0" + gg;
		    else day += gg;
		}
		if(sesso == 'F'){
			day += (gg + 40);
		}
			
			
		return surname_cons + "" + name_cons + "" + year + "" + month + "" + day;
	}
	
	/**
	 * Restituisce true se il codice fiscale fornito inizialmente è compatibile con i dati anagrafici inseriti, false altrimenti.
	 */
	private /*@ spec_public pure helper @*/ boolean validateTaxCode(){
		//controllo formato
		boolean flag = checkTaxCodeFormat(code);
		if(!flag) return false;
		
		//controllo primi 11 caratteri
		flag = String.valueOf(code).substring(0, 11).equals(generateTaxCode(nome, cognome, gg, mm, aa, sesso));
		if(!flag) return false;
		
		//controllo semplificato degli ultimi 5 caratteri
		flag = Character.isLetter(code[11]);
		if(nazione != "IT" && nazione != "Italia" && nazione != "italia" && nazione != "Italy") flag = (code[11] == 'Z');
		flag = Character.isDigit(code[12]);
		flag = Character.isDigit(code[13]);
		flag = Character.isDigit(code[14]);
		flag = Character.isLetter(code[15]);
		return flag;		
	}
	
	//Restituisce true se il formato del codice fiscale inserito è corretto.
	private static boolean checkTaxCodeFormat(char [] code){
		String regexp = "[A-Z]{6}[0-9]{2}[A-E|H|L|M|P|R-T][01-31|41-71]{2}[A-Z][0-9]{3}[A-Z]";
		CharSequence seq = java.nio.CharBuffer.wrap(code);
		return Pattern.matches(regexp, seq);
	}
	
	@SuppressWarnings("deprecation")
	public /*@ pure @*/ static boolean isAdult(int gg, int mm, int aa){
		Date present = new Date(2021, 11-1, 20);
		if(gg == present.getDate() && mm == present.getMonth() && present.getYear() - aa == 18) return true;
		Date birth = new Date(aa+18, mm-1, gg);
		return birth.before(present);
	}
	
	/**
	 * Restituisce true se la data fornita come argomento è antecedente a quella corrente, false altrimenti.
	 */
	@SuppressWarnings("deprecation")
	private /*@ spec_public pure helper @*/ static boolean validDate(int gg, int mm, int aa){
		return new Date(aa, mm, gg).before(new Date(2021, 11, 20));
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Converte la stringa passata come parametro in un array di caratteri.
	 * @param s
	 * @return L'array caratteri ottenuto da s.
	 */
	//@ requires (s != null);
	//@ ensures (\result != null);
	public static char [] toCharArray(String s){
		char[] ch = new char[s.length()];

	    for (int i = 0; i < s.length(); i++) {
	        ch[i] = s.charAt(i);
	    }
	    
	    return ch;
	}
	
	/**
	 * Restituisce una copia della stringa s dalla quale sono state rimosse le vocali.
	 * @param s
	 * @return Una copia di s dalla quale sono state rimosse le vocali. 
	 */
	//@ requires (s != null);
	//@ ensures (\result != null);
	public static String removeVowels(String s){
		String s_cons = s.replace("A", "");
		s_cons = s_cons.replace("E", "");
		s_cons = s_cons.replace("I", "");
		s_cons = s_cons.replace("O", "");
		s_cons = s_cons.replace("U", "");
		return s_cons;
	}
	
	/**
	 * Restituisce true se il carattere c corrisponde ad una vocale, false altrimenti.
	 * @param c
	 * @return true se c è una vocale, false altrimenti.
	 */
	public static boolean isVowel(char c){
		if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U')
			return true;
		return false;
	}
	
	/**
	 * Restituisce una copia della stringa s dalla quale sono state rimosse le consonanti.
	 * @param s
	 * @return Una copia di s dalla quale sono state rimosse le consonanti.
	 */
	//@ requires (s != null);
	//@ ensures (\result != null);
	public static String removeConsonants(String s){
		String s_vo = "";
		for(int i = 0 ; i < s.length() ; i++){
			if(isVowel(s.charAt(i))) s_vo += s.charAt(i);
		}
		return s_vo;
	}
	
	/**
	 * Restituisce true se le due stringhe sono uguali.
	 */
	private /*@ spec_public pure helper @*/ static boolean equalStrings(String s1, String s2){
		return s1.equals(s2);
	}
	
	@Override
	public String toString() {
		return "" + nome + " " + cognome + ", sesso: " + sesso + ", nato a: " + comune + " il: " + gg + "/" + mm + "/" + aa + "\n codice fiscale: " + new String(code) + "\n voto: " + voto;
	}
}