package es.us.agoraus.counting.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.us.agoraus.counting.security.Token;
import main.java.AuthorityImpl;

public class Transformations {

	/**
	 * The next method is used to transform a Byte Array inside a String into a
	 * real Byte Array. This is needed due to the way we obtain the votes in
	 * certain cases. We receive a string with "[12, 51, 34]" when we really
	 * need it to a byte[] with [12, 51, 34].
	 * 
	 * @param votesList
	 * @return the votes converted to byte[] in a list
	 */
	public static List<byte[]> transformByteArrayStringToByteArray(List<String> votesList) {
		// Variables initialized
		List<byte[]> result;
		result = new ArrayList<byte[]>();

		for (String vote : votesList) {
			// Each number in the string is splitted by ","
			String[] byteValues = vote.substring(1, vote.length() - 1).split(",");
			byte[] bytes = new byte[byteValues.length];

			for (int i = 0, len = bytes.length; i < len; i++) {
				// Trimming the white spaces and parsing to Byte is the last
				// step
				bytes[i] = Byte.parseByte(byteValues[i].trim());
			}
			result.add(bytes);
		}

		return result;
	}

	/**
	 * The next method is used to transform a crypted vote inside a String into
	 * a Byte Array. This is needed due to the way we decrypt the vote We
	 * receive a string with "dfse51sfd13sdf30" when we really need it to a
	 * byte[] to decrypt.
	 * 
	 * @param votesList
	 * @return the votes converted to byte[] in a list
	 */
	public static List<byte[]> transformStringToByteArray(List<String> votesList) {
		List<byte[]> result;
		result = new ArrayList<byte[]>();

		for (String vote : votesList) {
			// getBytes() transform the String into a Byte[]
			result.add(vote.getBytes());
		}

		return result;
	}

	/**
	 * The following method was used to check that the methods above were
	 * working correctly
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// AuthorityImpl allows us to decrypt the votes
		AuthorityImpl auth;
		auth = new AuthorityImpl();

		// Generates a token for security reasons
		Integer token;
		token = Token.calculateToken(1);

		// Some votes are initialized and added into a votes list
		String vote1 = "[99, 57, 102, 51, 51, 54, 98, 55, 49, 101, 56, 99, 57, 102, 102, 55, 99, 56, 57, 101, 56, 102, 99, 56, 98, 51, 102, 56, 50, 98, 101, 51, 100, 98, 101, 97, 48, 52, 97, 97, 63, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 55, 49, 56, 55, 55, 48, 49, 48, 54, 56, 49, 49, 55, 49, 55, 50, 53, 53, 49, 54, 56, 48, 48, 50, 51, 56, 49, 51, 48, 48, 51, 52, 52, 54, 56, 49, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 54, 53, 50, 57, 49, 52, 56, 54, 53, 50, 52, 53, 51, 54, 51, 54, 56, 49, 57, 56, 50, 48, 52, 57, 49, 51, 53, 52, 50, 52, 52, 57, 56, 50, 57, 57, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 54, 56, 57, 57, 53, 51, 56, 49, 52, 51, 52, 51, 52, 54, 51, 49, 56, 57, 57, 57, 48, 48, 53, 54, 55, 53, 57, 56, 51, 56, 55, 52, 56, 53, 51, 49, 48, 56, 51, 50, 57, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 54, 55, 51, 52, 56, 48, 53, 55, 51, 51, 56, 54, 55, 48, 57, 53, 55, 54, 49, 49, 50, 51, 55, 52, 52, 52, 49, 52, 56, 52, 51, 57, 54, 51, 52, 48, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 50, 53, 55, 57, 51, 52, 53, 49, 49, 57, 48, 57, 49, 53, 56, 53, 53, 53, 54, 52, 53, 48, 52, 56, 57, 55, 49, 51, 53, 52, 49, 55, 51, 48, 52, 55, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 54, 53, 49, 50, 54, 55, 49, 51, 54, 51, 55, 54, 51, 56, 51, 56, 51, 51, 53, 53, 48, 57, 57, 55, 52, 53, 54, 55, 53, 52, 56, 49, 50, 55, 51, 56, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 50, 52, 54, 53, 55, 49, 49, 48, 56, 51, 55, 55, 53, 56, 49, 49, 49, 49, 49, 49, 48, 49, 54, 57, 53, 48, 54, 56, 50, 52, 54, 52, 56, 56, 48, 55, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 53, 57, 53, 55, 49, 55, 54, 56, 48, 51, 54, 48, 57, 50, 53, 51, 56, 54, 56, 52, 54, 56, 52, 56, 50, 50, 50, 53, 49, 51, 57, 55, 50, 56, 53, 52, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 54, 48, 48, 54, 50, 53, 57, 52, 54, 55, 49, 48, 57, 52, 50, 50, 50, 53, 51, 48, 54, 48, 52, 48, 48, 54, 48, 54, 53, 49, 54, 51, 50, 52, 57, 51, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 50, 53, 55, 49, 51, 55, 50, 57, 51, 48, 49, 56, 56, 54, 48, 52, 55, 55, 49, 57, 55, 53, 55, 54, 55, 51, 50, 55, 53, 57, 50, 48, 53, 54, 56, 52, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 50, 53, 56, 48, 57, 53, 57, 55, 50, 55, 48, 54, 50, 52, 55, 48, 54, 51, 48, 57, 57, 54, 55, 50, 49, 50, 48, 49, 49, 55, 50, 55, 57, 56, 48, 55, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 51, 54, 55, 56, 56, 56, 57, 55, 50, 55, 54, 51, 49, 48, 52, 49, 56, 53, 48, 48, 55, 53, 49, 53, 51, 48, 57, 55, 51, 51, 54, 56, 51, 49, 49, 55, 52, 52, 53]";
		String vote2 = "[50, 54, 52, 101, 50, 54, 55, 54, 98, 52, 50, 56, 50, 56, 98, 55, 101, 54, 51, 98, 102, 49, 55, 97, 97, 102, 52, 56, 50, 99, 98, 50, 54, 99, 56, 101, 50, 100, 56, 102, 63, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 55, 49, 56, 55, 55, 48, 49, 48, 54, 56, 49, 49, 55, 49, 55, 50, 53, 53, 49, 54, 56, 48, 48, 50, 51, 56, 49, 51, 48, 48, 51, 52, 52, 54, 56, 49, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 54, 53, 50, 57, 49, 52, 56, 54, 53, 50, 52, 53, 51, 54, 51, 54, 56, 49, 57, 56, 50, 48, 52, 57, 49, 51, 53, 52, 50, 52, 52, 57, 56, 50, 57, 57, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 54, 56, 57, 57, 53, 51, 56, 49, 52, 51, 52, 51, 52, 54, 51, 49, 56, 57, 57, 57, 48, 48, 53, 54, 55, 53, 57, 56, 51, 56, 55, 52, 56, 53, 51, 49, 48, 56, 51, 50, 57, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 54, 55, 51, 52, 56, 48, 53, 55, 51, 51, 56, 54, 55, 48, 57, 53, 55, 54, 49, 49, 50, 51, 55, 52, 52, 52, 49, 52, 56, 52, 51, 57, 54, 51, 52, 48, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 50, 53, 55, 57, 51, 52, 53, 49, 49, 57, 48, 57, 49, 53, 56, 53, 53, 53, 54, 52, 53, 48, 52, 56, 57, 55, 49, 51, 53, 52, 49, 55, 51, 48, 52, 55, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 54, 53, 49, 50, 54, 55, 49, 51, 54, 51, 55, 48, 51, 54, 55, 53, 51, 56, 54, 53, 49, 56, 50, 49, 53, 50, 56, 55, 53, 50, 53, 54, 52, 48, 51, 52, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 50, 52, 54, 53, 55, 49, 49, 48, 56, 51, 55, 55, 53, 56, 49, 49, 49, 49, 49, 49, 48, 49, 54, 57, 53, 48, 54, 56, 50, 52, 54, 52, 56, 56, 48, 55, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 53, 57, 53, 55, 49, 55, 54, 56, 48, 51, 54, 48, 57, 50, 53, 51, 56, 54, 56, 52, 54, 56, 52, 56, 50, 50, 50, 53, 49, 51, 57, 55, 50, 56, 53, 52, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 54, 48, 48, 54, 50, 53, 57, 52, 54, 55, 49, 48, 57, 52, 50, 50, 50, 53, 51, 48, 54, 48, 52, 48, 48, 54, 48, 54, 53, 49, 54, 51, 50, 52, 57, 51, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 50, 53, 55, 49, 51, 55, 50, 57, 51, 48, 49, 56, 56, 54, 48, 52, 55, 55, 49, 57, 55, 53, 55, 54, 55, 51, 50, 55, 53, 57, 50, 48, 53, 54, 56, 52, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 50, 53, 56, 48, 57, 53, 57, 55, 50, 55, 48, 54, 50, 52, 55, 48, 54, 51, 48, 57, 57, 54, 55, 50, 49, 50, 48, 49, 49, 55, 50, 55, 57, 56, 48, 55, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 51, 54, 55, 56, 56, 56, 57, 55, 50, 55, 54, 51, 49, 48, 52, 49, 56, 53, 48, 48, 55, 53, 49, 53, 51, 48, 57, 55, 51, 51, 54, 56, 51, 49, 49, 55, 52, 52, 53]";
		String vote3 = "[101, 98, 51, 102, 99, 57, 53, 99, 53, 97, 48, 52, 51, 53, 100, 54, 102, 49, 98, 55, 101, 56, 54, 50, 48, 55, 102, 48, 102, 99, 97, 54, 49, 51, 57, 101, 50, 54, 56, 99, 63, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 55, 49, 56, 55, 55, 48, 49, 48, 54, 56, 49, 49, 55, 49, 55, 50, 53, 53, 49, 54, 56, 48, 48, 50, 51, 56, 49, 51, 48, 48, 51, 52, 52, 54, 56, 49, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 54, 53, 50, 57, 49, 52, 56, 54, 53, 50, 52, 53, 51, 54, 51, 54, 56, 49, 57, 56, 50, 48, 52, 57, 49, 51, 53, 52, 50, 52, 52, 57, 56, 50, 57, 57, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 54, 56, 57, 57, 53, 51, 56, 49, 52, 51, 52, 51, 52, 54, 51, 49, 56, 57, 57, 57, 48, 48, 53, 54, 55, 55, 57, 51, 56, 56, 48, 54, 51, 48, 55, 57, 56, 56, 56, 52, 55, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 54, 56, 57, 50, 54, 48, 55, 48, 52, 51, 49, 53, 55, 51, 54, 48, 48, 54, 50, 53, 54, 50, 56, 55, 55, 48, 53, 50, 48, 55, 57, 48, 56, 50, 48, 49, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 54, 53, 50, 57, 49, 52, 56, 54, 53, 50, 52, 53, 50, 55, 48, 54, 57, 54, 49, 57, 57, 51, 55, 55, 57, 49, 57, 53, 51, 57, 51, 52, 52, 49, 55, 48, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 51, 56, 49, 50, 54, 57, 54, 57, 50, 52, 52, 55, 53, 54, 48, 51, 49, 54, 57, 48, 56, 57, 57, 55, 49, 49, 57, 49, 51, 57, 55, 53, 49, 50, 50, 53, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 51, 48, 56, 53, 55, 56, 54, 53, 57, 51, 51, 56, 55, 53, 52, 52, 56, 52, 52, 55, 48, 55, 52, 52, 50, 54, 57, 54, 51, 49, 55, 49, 49, 51, 53, 49, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 54, 52, 55, 54, 48, 48, 55, 49, 56, 50, 52, 55, 51, 55, 50, 57, 57, 55, 51, 49, 50, 51, 50, 48, 48, 49, 57, 51, 50, 48, 57, 50, 52, 53, 50, 49, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 54, 52, 50, 53, 54, 57, 50, 53, 51, 48, 48, 49, 50, 53, 55, 48, 49, 54, 49, 56, 55, 51, 49, 53, 54, 50, 49, 49, 51, 57, 54, 50, 49, 54, 57, 56, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 50, 52, 54, 50, 54, 57, 53, 48, 54, 50, 56, 50, 49, 50, 48, 54, 48, 53, 57, 48, 55, 50, 50, 52, 55, 49, 48, 54, 57, 49, 54, 54, 49, 54, 56, 49, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 54, 48, 48, 53, 56, 53, 54, 57, 54, 57, 53, 56, 51, 54, 56, 50, 53, 57, 49, 53, 49, 53, 57, 56, 55, 55, 54, 50, 54, 49, 49, 57, 55, 49, 56, 57]";
		String vote4 = "[101, 98, 51, 102, 99, 57, 53, 99, 53, 97, 48, 52, 51, 53, 100, 54, 102, 49, 98, 55, 101, 56, 54, 50, 48, 55, 102, 48, 102, 99, 97, 54, 49, 51, 57, 101, 50, 54, 56, 99, 63, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 55, 49, 56, 55, 55, 48, 49, 48, 54, 56, 49, 49, 55, 49, 55, 50, 53, 53, 49, 54, 56, 48, 48, 50, 51, 56, 49, 51, 48, 48, 51, 52, 52, 54, 56, 49, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 54, 53, 50, 57, 49, 52, 56, 54, 53, 50, 52, 53, 51, 54, 51, 54, 56, 49, 57, 56, 50, 48, 52, 57, 49, 51, 53, 52, 50, 52, 52, 57, 56, 50, 57, 57, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 54, 56, 57, 57, 53, 51, 56, 49, 52, 51, 52, 51, 52, 54, 51, 49, 56, 57, 57, 57, 48, 48, 53, 54, 55, 55, 57, 51, 56, 56, 48, 54, 51, 48, 55, 57, 56, 56, 56, 52, 55, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 54, 56, 57, 50, 54, 48, 55, 48, 52, 51, 49, 53, 55, 51, 54, 48, 48, 54, 50, 53, 54, 50, 56, 55, 55, 48, 53, 50, 48, 55, 57, 48, 56, 50, 48, 49, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 54, 53, 50, 57, 49, 52, 56, 54, 53, 50, 52, 53, 50, 55, 48, 54, 57, 54, 49, 57, 57, 51, 55, 55, 57, 49, 57, 53, 51, 57, 51, 52, 52, 49, 55, 48, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 51, 56, 49, 50, 54, 57, 54, 57, 50, 52, 52, 55, 53, 54, 48, 51, 49, 54, 57, 48, 56, 57, 57, 55, 49, 49, 57, 49, 51, 57, 55, 53, 49, 50, 50, 53, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 51, 48, 56, 53, 55, 56, 54, 53, 57, 51, 51, 56, 55, 53, 52, 52, 56, 52, 52, 55, 48, 55, 52, 52, 50, 54, 57, 54, 51, 49, 55, 49, 49, 51, 53, 49, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 54, 52, 55, 54, 48, 48, 55, 49, 56, 50, 52, 55, 51, 55, 50, 57, 57, 55, 51, 49, 50, 51, 50, 48, 48, 49, 57, 51, 50, 48, 57, 50, 52, 53, 50, 49, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 54, 52, 50, 53, 54, 57, 50, 53, 51, 48, 48, 49, 50, 53, 55, 48, 49, 54, 49, 56, 55, 51, 49, 53, 54, 50, 49, 49, 51, 57, 54, 50, 49, 54, 57, 56, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 50, 52, 54, 50, 54, 57, 53, 48, 54, 50, 56, 50, 49, 50, 48, 54, 48, 53, 57, 48, 55, 50, 50, 52, 55, 49, 48, 54, 57, 49, 54, 54, 49, 54, 56, 49, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 53, 50, 50, 54, 48, 48, 53, 56, 53, 54, 57, 54, 57, 53, 56, 51, 54, 56, 50, 53, 57, 49, 53, 49, 53, 57, 56, 55, 55, 54, 50, 54, 49, 49, 57, 55, 49, 56, 57]";

		List<String> votes = new ArrayList<String>();

		votes.add(vote1);
		votes.add(vote2);
		votes.add(vote3);
		votes.add(vote4);

		List<byte[]> votesByte = new ArrayList<byte[]>();

		// We call the method above to check its function
		votesByte = transformByteArrayStringToByteArray(votes);

		System.out.println("---------- Prueba transformacion votacion 1 ----------");

		for (byte[] vote : votesByte) {
			System.out.println("--------- New element ---------");
			System.out.println(Arrays.toString(vote));
			String strVote = new String(vote);
			System.out.println(strVote);
			System.out.println(Arrays.toString(strVote.getBytes()));
			try {
				System.out.println(auth.decrypt("1", vote, token));
			} catch (Exception e) {
				System.out.println("An error has ocurred");
			}
			System.out.println("--------- Next element --------");
		}

		Integer token2;
		token2 = Token.calculateToken(2);

		String vote5 = "[99, 57, 102, 51, 51, 54, 98, 55, 49, 101, 56, 99, 57, 102, 102, 55, 99, 56, 57, 101, 56, 102, 99, 56, 98, 51, 102, 56, 50, 98, 101, 51, 100, 98, 101, 97, 48, 52, 97, 97, 63, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 50, 49, 56, 48, 50, 54, 48, 57, 52, 53, 56, 52, 55, 52, 51, 51, 54, 55, 53, 55, 53, 52, 57, 57, 52, 55, 53, 55, 50, 55, 52, 55, 50, 50, 48, 51, 54, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 50, 49, 49, 52, 52, 48, 53, 55, 48, 52, 50, 56, 49, 48, 56, 48, 49, 48, 50, 53, 54, 57, 48, 52, 49, 53, 49, 49, 51, 57, 56, 56, 55, 53, 54, 53, 52, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 54, 49, 50, 50, 53, 52, 56, 52, 50, 57, 50, 53, 49, 56, 53, 51, 53, 52, 55, 49, 53, 52, 50, 54, 50, 54, 50, 57, 55, 54, 50, 53, 48, 56, 50, 55, 52, 56, 53, 54, 56, 52, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 50, 49, 51, 52, 57, 55, 49, 52, 49, 50, 52, 50, 50, 52, 50, 53, 57, 57, 54, 54, 57, 57, 51, 54, 54, 56, 49, 55, 52, 53, 56, 55, 55, 51, 54, 57, 53, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 49, 55, 49, 57, 52, 50, 53, 51, 53, 48, 57, 52, 52, 56, 55, 52, 57, 55, 54, 50, 51, 50, 48, 52, 49, 51, 52, 55, 51, 50, 56, 53, 53, 48, 52, 48, 50, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 50, 49, 49, 50, 55, 53, 55, 57, 55, 53, 52, 49, 50, 49, 48, 48, 50, 53, 52, 49, 51, 55, 57, 56, 57, 56, 51, 50, 55, 50, 57, 49, 57, 48, 48, 57, 51, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 49, 55, 48, 56, 48, 54, 49, 57, 52, 55, 52, 49, 51, 50, 57, 55, 53, 51, 49, 54, 57, 55, 49, 54, 49, 56, 56, 50, 55, 57, 57, 48, 50, 54, 49, 54, 50, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 50, 48, 53, 55, 50, 48, 56, 53, 49, 57, 51, 57, 54, 54, 52, 49, 56, 48, 55, 52, 51, 51, 56, 52, 48, 53, 57, 56, 52, 56, 56, 51, 53, 48, 50, 48, 57, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 50, 48, 54, 50, 49, 49, 54, 55, 56, 53, 55, 52, 54, 54, 53, 56, 54, 52, 53, 56, 57, 51, 48, 51, 50, 52, 51, 54, 54, 50, 54, 48, 48, 57, 56, 52, 56, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 49, 55, 49, 56, 54, 50, 56, 49, 51, 50, 48, 53, 52, 53, 55, 54, 56, 57, 55, 55, 56, 52, 53, 54, 57, 49, 48, 56, 55, 51, 51, 53, 56, 51, 48, 51, 57, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 49, 55, 49, 57, 53, 56, 54, 56, 49, 49, 55, 52, 49, 57, 54, 51, 52, 56, 51, 54, 56, 54, 54, 54, 52, 52, 57, 54, 48, 57, 49, 54, 53, 55, 49, 54, 50, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 54, 50, 55, 51, 53, 48, 52, 54, 51, 54, 48, 50, 49, 51, 57, 57, 48, 49, 52, 50, 56, 49, 48, 50, 51, 48, 50, 49, 48, 57, 54, 53, 55, 52, 57, 52, 56, 48, 48]";
		String vote6 = "[50, 54, 52, 101, 50, 54, 55, 54, 98, 52, 50, 56, 50, 56, 98, 55, 101, 54, 51, 98, 102, 49, 55, 97, 97, 102, 52, 56, 50, 99, 98, 50, 54, 99, 56, 101, 50, 100, 56, 102, 63, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 50, 49, 56, 48, 50, 54, 48, 57, 52, 53, 56, 52, 55, 52, 51, 51, 54, 55, 53, 55, 53, 52, 57, 57, 52, 55, 53, 55, 50, 55, 52, 55, 50, 50, 48, 51, 54, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 50, 49, 49, 52, 52, 48, 53, 55, 48, 52, 50, 56, 49, 48, 56, 48, 49, 48, 50, 53, 54, 57, 48, 52, 49, 53, 49, 49, 51, 57, 56, 56, 55, 53, 54, 53, 52, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 54, 49, 50, 50, 53, 52, 56, 52, 50, 57, 50, 53, 49, 56, 53, 51, 53, 52, 55, 49, 53, 52, 50, 54, 50, 54, 50, 57, 55, 54, 50, 53, 48, 56, 50, 55, 52, 56, 53, 54, 56, 52, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 50, 49, 51, 52, 57, 55, 49, 52, 49, 50, 52, 50, 50, 52, 50, 53, 57, 57, 54, 54, 57, 57, 51, 54, 54, 56, 49, 55, 52, 53, 56, 55, 55, 51, 54, 57, 53, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 49, 55, 49, 57, 52, 50, 53, 51, 53, 48, 57, 52, 52, 56, 55, 52, 57, 55, 54, 50, 51, 50, 48, 52, 49, 51, 52, 55, 51, 50, 56, 53, 53, 48, 52, 48, 50, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 50, 49, 49, 50, 55, 53, 55, 57, 55, 53, 52, 48, 54, 48, 56, 51, 57, 53, 57, 50, 51, 56, 56, 49, 51, 57, 48, 52, 55, 50, 54, 57, 52, 49, 51, 56, 57, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 49, 55, 48, 56, 48, 54, 49, 57, 52, 55, 52, 49, 51, 50, 57, 55, 53, 51, 49, 54, 57, 55, 49, 54, 49, 56, 56, 50, 55, 57, 57, 48, 50, 54, 49, 54, 50, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 50, 48, 53, 55, 50, 48, 56, 53, 49, 57, 51, 57, 54, 54, 52, 49, 56, 48, 55, 52, 51, 51, 56, 52, 48, 53, 57, 56, 52, 56, 56, 51, 53, 48, 50, 48, 57, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 50, 48, 54, 50, 49, 49, 54, 55, 56, 53, 55, 52, 54, 54, 53, 56, 54, 52, 53, 56, 57, 51, 48, 51, 50, 52, 51, 54, 54, 50, 54, 48, 48, 57, 56, 52, 56, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 49, 55, 49, 56, 54, 50, 56, 49, 51, 50, 48, 53, 52, 53, 55, 54, 56, 57, 55, 55, 56, 52, 53, 54, 57, 49, 48, 56, 55, 51, 51, 53, 56, 51, 48, 51, 57, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 49, 55, 49, 57, 53, 56, 54, 56, 49, 49, 55, 52, 49, 57, 54, 51, 52, 56, 51, 54, 56, 54, 54, 54, 52, 52, 57, 54, 48, 57, 49, 54, 53, 55, 49, 54, 50, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 54, 50, 55, 51, 53, 48, 52, 54, 51, 54, 48, 50, 49, 51, 57, 57, 48, 49, 52, 50, 56, 49, 48, 50, 51, 48, 50, 49, 48, 57, 54, 53, 55, 52, 57, 52, 56, 48, 48]";
		String vote7 = "[101, 98, 51, 102, 99, 57, 53, 99, 53, 97, 48, 52, 51, 53, 100, 54, 102, 49, 98, 55, 101, 56, 54, 50, 48, 55, 102, 48, 102, 99, 97, 54, 49, 51, 57, 101, 50, 54, 56, 99, 63, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 50, 49, 56, 48, 50, 54, 48, 57, 52, 53, 56, 52, 55, 52, 51, 51, 54, 55, 53, 55, 53, 52, 57, 57, 52, 55, 53, 55, 50, 55, 52, 55, 50, 50, 48, 51, 54, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 50, 49, 49, 52, 52, 48, 53, 55, 48, 52, 50, 56, 49, 48, 56, 48, 49, 48, 50, 53, 54, 57, 48, 52, 49, 53, 49, 49, 51, 57, 56, 56, 55, 53, 54, 53, 52, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 54, 49, 50, 50, 53, 52, 56, 52, 50, 57, 50, 53, 49, 56, 53, 51, 53, 52, 55, 49, 53, 52, 50, 54, 50, 54, 52, 57, 51, 49, 49, 56, 50, 50, 56, 50, 51, 54, 54, 50, 48, 50, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 50, 49, 53, 48, 55, 53, 49, 53, 52, 51, 51, 53, 49, 52, 53, 50, 52, 50, 54, 56, 52, 51, 50, 56, 48, 48, 56, 49, 49, 56, 50, 50, 56, 53, 53, 53, 54, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 50, 49, 49, 52, 52, 48, 53, 55, 48, 52, 50, 56, 48, 57, 56, 55, 49, 49, 54, 55, 56, 54, 51, 55, 48, 50, 57, 53, 53, 49, 51, 55, 50, 49, 53, 50, 53, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 49, 56, 52, 50, 55, 54, 48, 53, 51, 49, 52, 56, 51, 50, 55, 54, 55, 51, 55, 52, 57, 53, 57, 56, 57, 52, 57, 53, 49, 49, 52, 49, 50, 56, 53, 56, 48, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 49, 55, 55, 48, 48, 54, 57, 52, 57, 56, 51, 55, 52, 52, 55, 48, 57, 48, 53, 48, 53, 55, 55, 51, 54, 54, 52, 53, 54, 48, 54, 48, 56, 56, 55, 48, 54, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 50, 49, 48, 57, 48, 57, 49, 53, 53, 55, 50, 56, 51, 48, 56, 57, 52, 49, 55, 56, 57, 57, 51, 49, 50, 51, 57, 53, 50, 57, 53, 51, 48, 49, 56, 55, 54, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 50, 49, 48, 52, 48, 54, 48, 48, 57, 50, 48, 51, 54, 57, 55, 51, 52, 51, 54, 55, 55, 52, 51, 48, 55, 57, 57, 55, 49, 49, 51, 57, 57, 57, 48, 53, 51, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 49, 55, 48, 55, 55, 54, 48, 51, 52, 53, 51, 49, 55, 56, 51, 55, 48, 50, 54, 52, 57, 52, 50, 49, 55, 48, 56, 54, 54, 54, 54, 48, 51, 57, 48, 51, 54, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 50, 48, 54, 50, 48, 55, 54, 53, 51, 53, 57, 57, 52, 48, 56, 52, 54, 55, 57, 55, 51, 56, 53, 57, 49, 49, 53, 50, 50, 51, 53, 53, 55, 52, 53, 52, 52]";
		String vote8 = "[101, 98, 51, 102, 99, 57, 53, 99, 53, 97, 48, 52, 51, 53, 100, 54, 102, 49, 98, 55, 101, 56, 54, 50, 48, 55, 102, 48, 102, 99, 97, 54, 49, 51, 57, 101, 50, 54, 56, 99, 63, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 50, 49, 56, 48, 50, 54, 48, 57, 52, 53, 56, 52, 55, 52, 51, 51, 54, 55, 53, 55, 53, 52, 57, 57, 52, 55, 53, 55, 50, 55, 52, 55, 50, 50, 48, 51, 54, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 50, 49, 49, 52, 52, 48, 53, 55, 48, 52, 50, 56, 49, 48, 56, 48, 49, 48, 50, 53, 54, 57, 48, 52, 49, 53, 49, 49, 51, 57, 56, 56, 55, 53, 54, 53, 52, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 54, 49, 50, 50, 53, 52, 56, 52, 50, 57, 50, 53, 49, 56, 53, 51, 53, 52, 55, 49, 53, 52, 50, 54, 50, 54, 52, 57, 51, 49, 49, 56, 50, 50, 56, 50, 51, 54, 54, 50, 48, 50, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 50, 49, 53, 48, 55, 53, 49, 53, 52, 51, 51, 53, 49, 52, 53, 50, 52, 50, 54, 56, 52, 51, 50, 56, 48, 48, 56, 49, 49, 56, 50, 50, 56, 53, 53, 53, 54, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 50, 49, 49, 52, 52, 48, 53, 55, 48, 52, 50, 56, 48, 57, 56, 55, 49, 49, 54, 55, 56, 54, 51, 55, 48, 50, 57, 53, 53, 49, 51, 55, 50, 49, 53, 50, 53, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 49, 56, 52, 50, 55, 54, 48, 53, 51, 49, 52, 56, 51, 50, 55, 54, 55, 51, 55, 52, 57, 53, 57, 56, 57, 52, 57, 53, 49, 49, 52, 49, 50, 56, 53, 56, 48, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 49, 55, 55, 48, 48, 54, 57, 52, 57, 56, 51, 55, 52, 52, 55, 48, 57, 48, 53, 48, 53, 55, 55, 51, 54, 54, 52, 53, 54, 48, 54, 48, 56, 56, 55, 48, 54, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 50, 49, 48, 57, 48, 57, 49, 53, 53, 55, 50, 56, 51, 48, 56, 57, 52, 49, 55, 56, 57, 57, 51, 49, 50, 51, 57, 53, 50, 57, 53, 51, 48, 49, 56, 55, 54, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 50, 49, 48, 52, 48, 54, 48, 48, 57, 50, 48, 51, 54, 57, 55, 51, 52, 51, 54, 55, 55, 52, 51, 48, 55, 57, 57, 55, 49, 49, 51, 57, 57, 57, 48, 53, 51, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 49, 55, 48, 55, 55, 54, 48, 51, 52, 53, 51, 49, 55, 56, 51, 55, 48, 50, 54, 52, 57, 52, 50, 49, 55, 48, 56, 54, 54, 54, 54, 48, 51, 57, 48, 51, 54, 124, 53, 54, 56, 55, 55, 52, 56, 48, 50, 53, 52, 48, 50, 55, 53, 49, 56, 48, 53, 50, 53, 48, 51, 52, 56, 55, 56, 52, 57, 48, 57, 48, 49, 51, 53, 55, 50, 55, 56, 50, 48, 54, 50, 48, 55, 54, 53, 51, 53, 57, 57, 52, 48, 56, 52, 54, 55, 57, 55, 51, 56, 53, 57, 49, 49, 53, 50, 50, 51, 53, 53, 55, 52, 53, 52, 52]";

		List<String> votes2 = new ArrayList<String>();

		votes2.add(vote5);
		votes2.add(vote6);
		votes2.add(vote7);
		votes2.add(vote8);

		List<byte[]> votesByte2 = new ArrayList<byte[]>();

		votesByte2 = transformByteArrayStringToByteArray(votes2);

		System.out.println("---------- Prueba transformacion votacion 2 ----------");

		for (byte[] vote : votesByte2) {
			System.out.println("--------- New element ---------");
			System.out.println(Arrays.toString(vote));
			String strVote = new String(vote);
			System.out.println(strVote);
			System.out.println(Arrays.toString(strVote.getBytes()));
			try {
				System.out.println(auth.decrypt("2", vote, token2));
			} catch (Exception e) {
				System.out.println("An error has ocurred");
			}
			System.out.println("--------- Next element --------");
		}

	}

	public static List<byte[]> forCodification(final String codification, final List<String> votes) {
		List<byte[]> result;
		if ((codification == null) || (codification == "normal")) {
			result = Transformations.transformStringToByteArray(votes);
		} else {
			result = Transformations.transformByteArrayStringToByteArray(votes);
		}
		return result;
	}
}
