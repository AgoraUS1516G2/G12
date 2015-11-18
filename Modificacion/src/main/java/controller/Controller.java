package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;

import main.AuthorityImpl;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sun.misc.BASE64Decoder;
import domain.Answer;
import domain.Respuesta;
import domain.Voto;
import domain.VotosCifrados;

@RestController
public class Controller {

	// private final String jsonTest =
	// "{\"votes\": [{\"id\": \"None\",\"community\": \"Extremadura\",\"answers\": \"¿DeseaaprobarEGC?: SI\",\"age\": \"20\",\"id_poll\": \"2\",\"genre\": \"HOMBRE\"},{\"id\": \"None\",\"community\": \"Extremadura\",\"answers\": \"¿DeseaaprobarEGC?: SI\",\"age\": \"20\",\"id_poll\": \"2\",\"genre\": \"HOMBRE\"},{\"id\": \"None\",\"community\": \"Andalucia\",\"answers\": \"¿DeseaaprobarEGC?: SI\",\"age\": \"20\",\"id_poll\": \"2\",\"genre\": \"HOMBRE\"},{\"id\": \"None\",\"community\": \"Andalucia\",\"answers\": \"¿DeseaaprobarEGC?: NO\",\"age\": \"20\",\"id_poll\": \"2\",\"genre\": \"HOMBRE\"}],\"msg\": 1}";

	@RequestMapping("/modificacion/results")
	public List<Respuesta> resultados(
			@RequestParam(required = false, defaultValue = "99") String votacionId) {

		return respuestas(votacionId);
	}

	public List<Respuesta> respuestas(String id) {
		boolean first = true;
		VotosCifrados votos = null;
		List<Respuesta> result = null;
		String jsonObject = getStringJSONFromUrl("http://php-egc.rhcloud.com/get_votes.php?votation_id=" + id);
		ObjectMapper mapper = new ObjectMapper();
		AuthorityImpl authorityImpl = new AuthorityImpl();

		try {
			votos = mapper.readValue(jsonObject,new TypeReference<VotosCifrados>() {});

			for (String s : votos.getVotes()) {
				BASE64Decoder decoder = new BASE64Decoder();

				byte[] bytesDecode = decoder.decodeBuffer(s);
				
				if (authorityImpl.checkVote(bytesDecode, id)) {
					String descifrado;
					descifrado = authorityImpl.decrypt(id, bytesDecode);

					Voto voto = mapper.readValue(descifrado,new TypeReference<Voto>() {
							});
					if (first) {
						result = new ArrayList<Respuesta>();
						for (Answer a : voto.getAnswers()) {
							Respuesta r = new Respuesta(a.getQuestion());
							result.add(r);
						}
						first = false;
					}

					int mod = ponderacion(voto.getAutonomous_community());

					for (int i = 0; i < result.size(); i++) {
						if (voto.getAnswers().get(i).getAnswer_question()
								.equals("SI")) {
							result.get(i).setNumerosSi(
									result.get(i).getNumerosSi() + mod);
						} else if (voto.getAnswers().get(i)
								.getAnswer_question().equals("NO")) {
							result.get(i).setNumerosNo(
									result.get(i).getNumerosNo() + mod);
						}

					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public static Integer ponderacion(String comunidad) {
		Integer result = null;

		switch (comunidad) {
		case "Andalucia":
			result = 1;
			break;
		case "Murcia":
			result = 2;
			break;
		case "Extremadura":
			result = 1;
			break;
		case "Castilla la Mancha":
			result = 1;
			break;
		case "Comunidad Valenciana":
			result = 2;
			break;
		case "Madrid":
			result = 2;
			break;
		case "Castilla y Leon":
			result = 1;
			break;
		case "Aragon":
			result = 1;
			break;
		case "Cataluña":
			result = 2;
			break;
		case "La Rioja":
			result = 3;
			break;
		case "Galicia":
			result = 2;
			break;
		case "Asturias":
			result = 2;
			break;
		case "Cantabria":
			result = 2;
			break;
		case "Pais Vasco":
			result = 3;
			break;
		case "Navarra":
			result = 1;
			break;
		}
		return result;
	}

	public static String getStringJSONFromUrl(String urlString) {

		HttpURLConnection urlConnection = null;
		BufferedReader reader = null;

		String jsonString = null;

		try {
			// Construct the URL for the OpenWeatherMap query
			// Possible parameters are available at OWM's forecast API page, at
			// http://openweathermap.org/API#forecast
			URL url = new URL(urlString);

			// Create the request to OpenWeatherMap, and open the connection
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.connect();

			// Read the input stream into a String
			InputStream inputStream = urlConnection.getInputStream();
			StringBuffer buffer = new StringBuffer();

			if (inputStream == null) {
				jsonString = null;
			}

			reader = new BufferedReader(new InputStreamReader(inputStream));

			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line + "\n");
			}

			if (buffer.length() == 0) {
				// Stream was empty. No point in parsing.
				jsonString = null;
			}
			jsonString = buffer.toString();
		} catch (IOException e) {

			// If the code didn't successfully get the weather data, there's no
			// point in attempting
			// to parse it.
			jsonString = null;
		} finally {
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (final IOException e) {
				}
			}
		}
		// return JSON String
		return jsonString;
	}
}
