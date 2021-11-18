package assingment3;
import java.util.Calendar;
import java.util.regex.Pattern;
public class Elettore {
	private /*@ spec_public @*/ final String nome, cognome;
	private /*@ spec_public @*/ int gg, mm, aa;
	private final /*@ spec_public @*/ String paese, luogo; 
	private /*@ spec_public @*/ char sesso;
	private final /*@ spec_public @*/ char [] code;
	private /*@ spec_public @*/ boolean voto;
	
	/*@ public invariant nome != null && cognome != null && (sesso == 'M' || sesso == 'F') && maggiorenne(gg, mm, aa) && (stringEquality(toLowerCase(paese),"italia") ==> luogo != null) && checkFiscalCode(code) 
	 && match(nome, cognome, gg, mm, aa, sesso, code); @*/
	
	public Elettore(String nome, String cognome, int gg, int mm, int aa, String luogo, String paese, char sesso, String code){
		this.nome = nome;
		this.cognome = cognome;
		this.gg = gg;
		this.mm = mm;
		this.aa = aa;
		this.luogo = luogo;
		this.paese = paese;
		this.sesso = sesso;
		this.code = toCharArray(code);
		voto = false;
	}
	
	/**
	 * Restituisce la porzione di codice fiscale calcolata tramite nome, cognome, data di nascita e sesso.
	 * @param nome
	 * @param cognome
	 * @param gg
	 * @param mm
	 * @param aa
	 * @param sesso
	 * @return La porzione di codice fiscale.
	 */
	private static String getCode(String nome, String cognome, int gg, int mm, int aa, char sesso){
		String name_cons = nome.toUpperCase(); //Conterrà il nome formattato per il codice fiscale
		String surname_cons = cognome.toUpperCase(); //Conterrà il cognome formattato per il codice fiscale
		String name_vo = "", surname_vo = "";
		
		name_cons = name_cons.replace("A", "");
		name_cons = name_cons.replace("E", "");
		name_cons = name_cons.replace("I", "");
		name_cons = name_cons.replace("O", "");
		name_cons = name_cons.replace("U", "");
		surname_cons = surname_cons.replace("A", "");
		surname_cons = surname_cons.replace("E", "");
		surname_cons = surname_cons.replace("I", "");
		surname_cons = surname_cons.replace("O", "");
		surname_cons = surname_cons.replace("U", "");
		
		for(int i = 0 ; i < nome.length(); i++)
			if(nome.charAt(i) == 'a' || nome.charAt(i) == 'e' || nome.charAt(i) == 'i' || nome.charAt(i) == 'o' || nome.charAt(i) == 'u')
				name_vo += Character.toUpperCase(nome.charAt(i));
		
		for(int i = 0 ; i < cognome.length(); i++)
			if(cognome.charAt(i) == 'a' || cognome.charAt(i) == 'e' || cognome.charAt(i) == 'i' || cognome.charAt(i) == 'o' || cognome.charAt(i) == 'u')
				surname_vo += Character.toUpperCase(cognome.charAt(i));
		
		int j = 0;
		while(surname_cons.length() < 3){
			if(j < surname_vo.length())
				surname_cons += surname_vo.charAt(j++);
			else
				surname_cons += "X";
		}
		
		if(name_cons.length() >= 4)
			name_cons = name_cons.charAt(0) + "" + name_cons.charAt(2) + "" + name_cons.charAt(3);
		else{
			j = 0;
			while(name_cons.length() < 3){
				if(j < name_vo.length())
					name_cons += name_vo.charAt(j++) ;
				else
					name_cons += "X";
			}
		}
			
		
		String year = String.valueOf(aa).substring(2); //Conterrà l'anno formattato per il codice fiscale
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
		
		String day = sesso == 'M' ? day = String.valueOf(gg) : String.valueOf(gg + 40);
			
		return surname_cons + "" + name_cons + "" + year + "" + month + "" + day; 
	}
	
	/**
	 * Valuta se i primi 11 caratteri del codice fiscale passato come parametro matchano la parte di codice fiscale ottenuta tramite nome, cognome, data di nascita e sesso.
	 * @param nome
	 * @param cognome
	 * @param gg
	 * @param mm
	 * @param aa
	 * @param sesso
	 * @param code
	 * @return true se i primi 11 caratteri di code matchano con quelli ricavati tramite gli altri parametri, false altrimenti.
	 */
	private /*@ spec_public @*/ /*@ pure @*/ static boolean match(String nome, String cognome, int gg, int mm, int aa, char sesso, char [] code){
		return stringEquality(String.valueOf(code).substring(0, 11),getCode(nome, cognome, gg, mm, aa, sesso));
	}
	
	/**
	 * Valuta se tra la data passata come parametro e quella attuale sono trascorsi almeno 18 anni. 
	 * @param gg
	 * @param mm
	 * @param aa
	 * @return true se la differenza delle date e' di almeno 18 anni, false altrimenti.
	 */
	private /*@ spec_public @*/ /*@ pure @*/ static boolean maggiorenne(int gg, int mm, int aa){
		Calendar age = Calendar.getInstance();
		age.clear();
		age.set(aa, mm, gg);
		Calendar current = Calendar.getInstance();
		
		long mAge = age.getTimeInMillis();
		long mCurr = current.getTimeInMillis();
		
		return mCurr - mAge >= 18 * 365 * 24 * 60 * 60 * 1000;		
	}
	
	/**
	 * Converte la stringa passata come parametro in un array di caratteri.
	 * @param s
	 * @return L'array caratteri ottenuto da s.
	 */
	private /*@ spec_public @*/ /*@ pure @*/ static char [] toCharArray(String s){
		char[] ch = new char[s.length()];

	    for (int i = 0; i < s.length(); i++) {
	        ch[i] = s.charAt(i);
	    }
	    
	    return ch;
	}
	
	/**
	 * Valuta se il codice fiscale passato come parametro e' formattato correttamente. 
	 * @param code
	 * @return true se code e' formattato correttamente, false altrimenti.
	 */
	private /*@ spec_public @*/ /*@ pure @*/ static boolean checkFiscalCode(char [] code){
		String regexp = "[A-Z]{6}[0-9]{2}[A-E|H|L|M|P|R-T][01-31|41-71]{2}[A-Z][0-9]{3}[A-Z]";
		CharSequence seq = java.nio.CharBuffer.wrap(code);
		return Pattern.matches(regexp, seq);
	}
    
	/**
	 * Rende minuscoli tutti i caratteri alfabetici della stringa passata come parametro
	 * @param s
	 * @return La stringa s con tutti i caratteri alfabetici minuscoli.
	 */
	private /*@ spec_public @*/ /*@ pure @*/ static String toLowerCase(String s){
		return s.toLowerCase();
	}
	
	/**
	 * Esegue il confronto tra due stringhe a livello logico.
	 * @param s1
	 * @param s2
	 * @return true se il contenuto di s1 e' lo stesso di s2, false altrimenti.
	 */
	private /*@ spec_public @*/ /*@ pure @*/ static boolean stringEquality(String s1, String s2){
		return s1.equals(s2);
	}
}