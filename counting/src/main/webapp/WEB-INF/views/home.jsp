<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Subsistema "Recuento y Modificaci�n de Resultados"</h1>

	<P>
		Subsistema encargado de la realizaci�n del recuento y
		la modificaci�n de los resultados de una votaci�n determinada. Para la
		realizaci�n del recuento se acceder� a la base de datos proporcionada
		por almacenamiento y utilizar� los m�todos proporcionados por
		verificaci�n para descifrar los votos obtenidos.
	</P>

	API REST del subsistema:

	<p>*/count/predefined --> Test de funcionamiento de una votaci�n.</p>

	<p>*/count/{idVotacion} --> Recuento de un refer�ndum.</p>

	<p>
		Se puede a�adir el par�metro "cod", dependiendo de la codificaci�n de
		los votos.<br> */count/1?cod=special --> si se esperan recibir
		los votos como array de bytes. <br> */count/2 o
		*/count/2?cod=normal --> si se esperan recibir los votos como un hash
		cadena cifrado.
	</p>
	<p>
		Es posible segmentar el recuento mediante el par�metro "segment" por
		edad, g�nero y comunidad aut�noma. <br> */count/2?segment=age -->
		para segmentar el recuento por edad.<br> */count/2?segment=gender
		--> para segmentar el recuento por g�nero.<br>
		*/count/2?segment=aut_com --> para segmentar el recuento por com.
		aut�noma.
	</p>

	<p>
		Para visualizar los resultados de una votaci�n por segmento, invocar
		las siguientes rutas: <br> */count/2/charts?segment=age -->
		para segmentar el recuento por edad.<br> */count/2/charts?segment=gender
		--> para segmentar el recuento por g�nero.<br>
		*/count/2/charts?segment=aut_com --> para segmentar el recuento por com.
		aut�noma.
	</p>
</body>
</html>
