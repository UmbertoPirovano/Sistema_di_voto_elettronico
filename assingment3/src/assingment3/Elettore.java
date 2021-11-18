package assingment3;
import java.util.Date;
import java.util.regex.Pattern;
public class Elettore {
	private final String nome, cognome;
	private int gg, mm, aa;
	private final String luogo, paese;
	private char sesso;
	private final char [] cod;
	private /*@ spec_public @*/ boolean voto;
	
	/*@ requires nome != null && cognome != null && (sesso == 'M' || sesso == 'F') && maggiorenne(gg, mm, aa) && this.voto == false && checkFiscalCode(toCharArray(cod)) ;@*/
	public Elettore(String nome, String cognome, int gg, int mm, int aa, String luogo, String paese, char sesso, String cod){
		this.nome = nome;
		this.cognome = cognome;
		this.gg = gg;
		this.mm = mm;
		this.aa = aa;
		this.luogo = luogo;
		this.paese = paese;
		this.sesso = sesso;
		this.cod = toCharArray(cod);
		voto = false;
	}
	
	/*
	 * Post-condizioni: restituisce true se il codice fiscale combacia con i dati anagrafici di this, false altrimenti.
	 */
	public boolean match(String nome, String cognome, String cod, int gg, int mm, int aa, String luogo, String paese, char sesso){
		String name = new String(nome);
		String surname = new String(cognome);
		
		name = name.replace("a", "");
		name = name.replace("e", "");
		name = name.replace("i", "");
		name = name.replace("o", "");
		name = name.replace("u", "");
		surname = surname.replace("a", "");
		surname = surname.replace("e", "");
		surname = surname.replace("i", "");
		surname = surname.replace("o", "");
		surname = surname.replace("u", "");
		
		while(surname.length() < 3){
			surname = surname + "X";
		}
		if(!(cod.substring(0, 3).equals(surname.substring(0,3))))
			return false;
		
		while(name.length() < 3){
			name = name + "X";
		}
		if(!(cod.substring(3, 6).equals(name.substring(0,3))))
			return false;
		
		if(!(cod.substring(6, 8).equals(String.valueOf(aa))))
			return false;
		
		switch()
	}
	
	/*
	 * Post-condizioni: se tra la data fornita in input e quella corrente fornita dal sistema sono passati almeno 18 anni restituisce true,
	 * 					altrimenti false.
	 */
	public static /*@ pure @*/ boolean maggiorenne(int gg, int mm, int aa){
		Date age = new Date(aa, mm, gg);
		Date current = new Date(System.currentTimeMillis());
		
		long mAge = age.getTime();
		long mCurr = current.getTime();
		
		return mCurr - mAge >= 18 * 365 * 24 * 60 * 1000;		
	}
	
	/*
	 * Post-condizioni: trasforma la stringa s in un array di caratteri.
	 */
	private /*@ spec_public @*/ static /*@ pure @*/ char [] toCharArray(String s){
		char[] ch = new char[s.length()];

	    // Copy character by character into array
	    for (int i = 0; i < s.length(); i++) {
	        ch[i] = s.charAt(i);
	    }
	    
	    return ch;
	}
	
	/*
	 * Post-condizioni: restituisce true se il codice fiscale è formattato correttamente, false altrimenti.
	 */
	public /*@ spec_public @*/ static /*@ pure @*/ boolean checkFiscalCode(char [] code){
		String regexp = "/^(?:[A-Z][AEIOU][AEIOUX]|[B-DF-HJ-NP-TV-Z]{2}[A-Z]){2}(?:[\\dLMNP-V]{2}(?:[A-EHLMPR-T](?:[04LQ][1-9MNP-V]|[15MR][\\dLMNP-V]|[26NS][0-8LMNP-U])|[DHPS][37PT][0L]|[ACELMRT][37PT][01LM]|[AC-EHLMPR-T][26NS][9V])|(?:[02468LNQSU][048LQU]|[13579MPRTV][26NS])B[26NS][9V])(?:[A-MZ][1-9MNP-V][\\dLMNP-V]{2}|[A-M][0L](?:[1-9MNP-V][\\dLMNP-V]|[0L][1-9MNP-V]))[A-Z]$/i";
		CharSequence seq = java.nio.CharBuffer.wrap(code);
		return Pattern.matches(regexp, seq);
	}
	
	
    

}
