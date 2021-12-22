package dbConnection;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Questa classe fornisce metodi per criptare stringhe.
 */

public class Encryption {
	
	/**
	 * Cripta la stringa passata in input con l'algoritmo SHA512
	 * @param s La stringa da criptare.
	 * @return La stringa criptata con SHA512.
	 */
	public static String Sha512(String s) {
		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		m.update(s.getBytes(), 0, s.length());
		return new BigInteger(1, m.digest()).toString(16);
	}

}
