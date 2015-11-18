package algoritmos;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.DatatypeConverter;

import main.Authority;
import main.AuthorityImpl;

import com.google.gson.Gson;

import sun.misc.BASE64Decoder;

import java.security.spec.PKCS8EncodedKeySpec;

import domain.Answer;
import domain.Resultado;
import domain.Voto;
import domain.VotoAntiguo;
import domain.VotoAux;
import domain.VotoNuevo;

@SuppressWarnings("restriction")
public class Algoritmo {
	// Suponemos que la base de datos almacenara la informacion de la siguiente
	// manera: "PP", "PSOE", "PP", "PODEMOS", ...
	// Recorreremos toda esa coleccion, de forma que obtengamos los nombres de
	// cada votacion, y los almacenaremos en un hashset (para que no se repitan
	// valores)

	// La supuesta lista que nos pasan

	public static Map<String, Integer> Algoritmo1(List<String> votos) {

		// Suponemos que la coleccion "votos" es lo que hemos recuperado de la
		// base de datos

		Set<String> claves = new HashSet<String>();

		for (String s : votos) {
			claves.add(s);
		}
		// Ahora, una vez tenemos las claves,crearemos un mapa al que
		// asignaremos a cada clave su valor.
		Map<String, Integer> resultados = new HashMap<String, Integer>();

		// Iniciamos el mapa con los valores a cero
		for (String s1 : claves) {
			resultados.put(s1, 0);
		}
		// Recorremos la lista de votos y adjudicamos el numero de veces
		// repetida a su
		// nombre
		for (String s2 : votos) {
			if (resultados.containsKey(s2)) {
				resultados.put(s2, resultados.get(s2) + 1);
			}
		}

		return resultados;
	}

	// En el caso de que no se nos pase una lista de strings, sino una lista de
	// votos
	// se usara este algoritmo, que hace lo mismo que el algoritmo de arriba,
	// pero sacando de la
	// entidad "VotoAntiguo" su nombre.
	public static Map<String, Integer> algoritmo2(List<VotoAntiguo> votos) {
		Set<String> claves = new HashSet<String>();
		List<String> votaciones = new ArrayList<String>();
		for (VotoAntiguo v : votos) {
			votaciones.add(v.getNombre());
		}
		for (String s : votaciones) {
			claves.add(s);
		}
		Map<String, Integer> resultados = new HashMap<String, Integer>();
		for (String s1 : claves) {
			resultados.put(s1, 0);
		}
		for (String s2 : votaciones) {
			if (resultados.containsKey(s2)) {
				resultados.put(s2, resultados.get(s2) + 1);
			}
		}
		return resultados;
	}

	// public static List<Resultado> algoritmo3(String idVotacion,List<String>
	// votos) throws BadPaddingException, NoSuchAlgorithmException,
	// InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException,
	// IllegalBlockSizeException
	// {
	// Authority auth = new AuthorityImpl();
	// List<VotoNuevo> votes = new ArrayList<VotoNuevo>();
	// String key = auth.getPrivateKey(idVotacion);
	// for (String s: votos)
	// {
	// BASE64Decoder decoder = new BASE64Decoder();
	// String g = s.substring(0,s.length()-1);
	//
	// try
	// {
	// byte[] bytesDecode = decoder.decodeBuffer(g);
	//
	// if (auth.checkVote(s.getBytes(), idVotacion))
	// {
	// Cipher rsa;
	//
	// KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	// KeySpec keySpec = new
	// PKCS8EncodedKeySpec(DatatypeConverter.parseBase64Binary(key));
	// PrivateKey privKeyFromBytes = keyFactory.generatePrivate(keySpec);
	// rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	// rsa.init(Cipher.DECRYPT_MODE, privKeyFromBytes);
	// byte[] bytesDesencriptados = rsa.doFinal(bytesDecode);
	// String json = new String(bytesDesencriptados);
	// Gson gson = new Gson();
	// VotoNuevo vot = gson.fromJson(json,VotoNuevo.class);
	// votes.add(vot);
	// }
	// }
	// catch (IOException e)
	// {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// Set<String> claves = new HashSet<String>();
	// for (VotoNuevo v : votes)
	// {
	// for (Answer a: v.getAnswers())
	// {
	// claves.add(a.getQuestion());
	// }
	// }
	// List<Resultado> resultados = new ArrayList<Resultado>();
	// for (String c : claves)
	// {
	// resultados.add(new Resultado(c, 0, 0));
	// }
	// for (VotoNuevo v : votes)
	// {
	// for (Resultado r : resultados)
	// {
	// for (Answer a:v.getAnswers())
	// {
	//
	// if (a!=null)
	// {
	// if(a.equals(r.getPregunta()))
	// {
	// System.out.println(a.getAnswer_question());
	// if
	// (a.getAnswer_question().equalsIgnoreCase("si")||a.getAnswer_question().equalsIgnoreCase("si"))
	// {
	// r.setNumeroSi(r.getNumeroSi() + 1);
	// }
	// else if (a.getAnswer_question().equalsIgnoreCase("no"))
	// {
	// r.setNumeroNo(r.getNumeroNo() + 1);
	// }
	// }
	// }
	// }
	// }
	// }
	// return resultados;
	// }

	public static List<Resultado> algoritmo3(String idVotacion,
			List<String> votos) throws BadPaddingException,
			NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException {
		AuthorityImpl auth = new AuthorityImpl();
		List<VotoNuevo> votes = new ArrayList<VotoNuevo>();
		String key = auth.getPrivateKey(idVotacion);
		for (String s : votos) {
			BASE64Decoder decoder = new BASE64Decoder();

			try {
				byte[] bytesDecode = decoder.decodeBuffer(s);

				if (auth.checkVote(bytesDecode, idVotacion)) {
					String res = null;
					res = auth.decrypt(idVotacion, bytesDecode);

					// Voto voto = mapper.readValue(res,new
					// TypeReference<Voto>() {});

					Gson gson = new Gson();
					VotoNuevo vot = gson.fromJson(res, VotoNuevo.class);
					votes.add(vot);

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Set<String> claves = new HashSet<String>();
		for (VotoNuevo v : votes) {
			for (Answer a : v.getAnswers()) {
				claves.add(a.getQuestion());
			}
		}
		List<Resultado> resultados = new ArrayList<Resultado>();
		for (String c : claves) {
			resultados.add(new Resultado(c, 0, 0));

		}
		for (VotoNuevo v : votes) {
			for (Resultado r : resultados) {
				for (Answer a : v.getAnswers()) {

					if (a.getQuestion().equals(r.getPregunta())) {

						if (a.getAnswer_question().equals("SI")) {
							r.setNumeroSi(r.getNumeroSi() + 1);
						} else if (a.getAnswer_question().equals("NO")) {
							r.setNumeroNo(r.getNumeroNo() + 1);
						}
					}
				}
			}
		}

		return resultados;
	}

	public static String decrypt(byte[] cipherText, Key privateKey)
			throws BadPaddingException, InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException {

		String res;
		Cipher rsa;

		rsa = Cipher.getInstance("RSA");
		rsa.init(Cipher.DECRYPT_MODE, privateKey);

		byte[] bytesDesencriptados = rsa.doFinal(cipherText);
		res = new String(bytesDesencriptados);
		return res;

	}
}