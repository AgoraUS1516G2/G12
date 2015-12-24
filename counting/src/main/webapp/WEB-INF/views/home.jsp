<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Subsistema "Recuento y Modificación de Resultados"</h1>

	<P>
		Subsistema encargado de la realización del recuento y
		la modificación de los resultados de una votación determinada. Para la
		realización del recuento se accederá a la base de datos proporcionada
		por almacenamiento y utilizará los métodos proporcionados por
		verificación para descifrar los votos obtenidos.
	</P>

	API REST del subsistema:

	<p>*/count/predefined --> Test de funcionamiento de una votación.</p>

	<p>*/count/{idVotacion} --> Recuento de un referéndum.</p>

	<p>
		Se puede añadir el parámetro "cod", dependiendo de la codificación de
		los votos.<br> */count/1?cod=special --> si se esperan recibir
		los votos como array de bytes. <br> */count/2 o
		*/count/2?cod=normal --> si se esperan recibir los votos como un hash
		cadena cifrado.
	</p>
	<p>
		Es posible segmentar el recuento mediante el parámetro "segment" por
		edad, género y comunidad autónoma. <br> */count/2?segment=age -->
		para segmentar el recuento por edad.<br> */count/2?segment=gender
		--> para segmentar el recuento por género.<br>
		*/count/2?segment=aut_com --> para segmentar el recuento por com.
		autónoma.
	</p>

	<p>
		Para visualizar los resultados de una votación por segmento, invocar
		las siguientes rutas: <br> */count/2/charts?segment=age -->
		para segmentar el recuento por edad.<br> */count/2/charts?segment=gender
		--> para segmentar el recuento por género.<br>
		*/count/2/charts?segment=aut_com --> para segmentar el recuento por com.
		autónoma.
	</p>
</body>
</html>
