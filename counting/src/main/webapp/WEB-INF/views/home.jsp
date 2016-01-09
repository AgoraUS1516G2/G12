<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<head>
<title>Home</title>

<!-- Compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/css/materialize.min.css">

<!-- Compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/js/materialize.min.js"></script>
</head>
<body>

	<div class="container">
		<h1>Subsistema "Recuento y Modificación de Resultados"</h1>

		<p class="flow-text">Subsistema encargado de la realización del
			recuento y la modificación de los resultados de una votación
			determinada. Para la realización del recuento se accederá a la base
			de datos proporcionada por almacenamiento y utilizará los métodos
			proporcionados por verificación para descifrar los votos obtenidos.</p>

		<div class="card-panel light-green accent-1">
			<p class="flow-text">

				Nota:<br> Los tiempos de respuestas pueden ser bastante
				amplios.<br> No es responsabilidad del subsistema de recuento
				ni de ningún miembro relacionado con el subsistema.<br> Se
				trata de un problema sobre el lugar donde está desplegado el
				subsistema de Verificación, el cual debe ser consultado para el
				descifrado. <br> Aún así, disculpen las molestias causadas.
			</p>
		</div>
		<br>


		<h4>Listado de votaciones existentes:</h4>
		<br>
		<table class="table-responsive striped centered bordered">
			<thead>
				<tr>
					<th>Votación</th>
					<th>Sin visualización</th>
					<th>Visual</th>
					<th>Visual segmentado por edad</th>
					<th>Visual segmentado por género</th>
					<th>Visual segmentado por comunidad autónoma</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Votación 1</td>
					<td><a href="./count/1?cod=special">Ver</a></td>
					<td><a href="./count/1/charts?cod=special">Ver</a></td>
					<td><a href="./count/1/charts?cod=special&segment=age">Ver</a></td>
					<td><a href="./count/1/charts?cod=special&segment=gender">Ver</a></td>
					<td><a href="./count/1/charts?cod=special&segment=aut_com">Ver</a></td>
				</tr>

				<tr>
					<td>Votación 2</td>
					<td><a href="./count/2">Ver</a></td>
					<td><a href="./count/2/charts">Ver</a></td>
					<td><a href="./count/2/charts?segment=age">Ver</a></td>
					<td><a href="./count/2/charts?segment=gender">Ver</a></td>
					<td><a href="./count/2/charts?segment=aut_com">Ver</a></td>
				</tr>

				<tr>
					<td>Votación 3</td>
					<td><a href="./count/3">Ver</a></td>
					<td><a href="./count/3/charts">Ver</a></td>
					<td><a href="./count/3/charts?segment=age">Ver</a></td>
					<td><a href="./count/3/charts?segment=gender">Ver</a></td>
					<td><a href="./count/3/charts?segment=aut_com">Ver</a></td>
				</tr>

				<tr>
					<td>Votación 4</td>
					<td><a href="./count/4">Ver</a></td>
					<td><a href="./count/4/charts">Ver</a></td>
					<td><a href="./count/4/charts?segment=age">Ver</a></td>
					<td><a href="./count/4/charts?segment=gender">Ver</a></td>
					<td><a href="./count/4/charts?segment=aut_com">Ver</a></td>
				</tr>

				<tr>
					<td>Votación 5</td>
					<td><a href="./count/5">Ver</a></td>
					<td><a href="./count/5/charts">Ver</a></td>
					<td><a href="./count/5/charts?segment=age">Ver</a></td>
					<td><a href="./count/5/charts?segment=gender">Ver</a></td>
					<td><a href="./count/5/charts?segment=aut_com">Ver</a></td>
				</tr>
			</tbody>
		</table>
		<br> <br>

		<ul class="collection with-header">
			<li class="collection-header"><h4>API REST del subsistema:</h4></li>
			<li class="collection-item">
				<font face="consolas">*/count/predefined</font>
				--> Test de funcionamiento de una votación.</li>
			<li class="collection-item">
				<font face="consolas">*/count/{idVotacion}</font>
				--> Recuento de un referéndum.</li>
			<li class="collection-item"><b>Se puede añadir el parámetro
					"cod", dependiendo de la codificación de los votos.</b><br> <br>
				<font face="consolas">*/count/1?cod=special</font>
				 --> si se esperan recibir los votos como array de bytes. <br> 
				<font face="consolas">*/count/2 o */count/2?cod=normal</font>
				--> si se esperan recibir los votos como un hash cadena cifrado.</li>
			<li class="collection-item"><b>Es posible segmentar el
					recuento mediante el parámetro "segment" por edad, género y
					comunidad autónoma.</b> <br> <br> 
					<font face="consolas">*/count/2?segment=age</font>
				--> para segmentar el recuento por edad.<br> 
				<font face="consolas">*/count/2?segment=gender</font> 
				--> para segmentarel recuent o por género.<br> 
				<font face="consolas">*/count/2?segment=aut_com</font>
				--> para segmentar el recuento por com. autónoma.</li>
			<li class="collection-item"><b>Para visualizar los
					resultados de una votación por segmento, invocar las siguientes
					rutas:</b><br> <br> <font face="consolas">*/count/2/charts?segment=age</font>
				--> para segmentar el recuento por edad.<br> <font
				face="consolas">*/count/2/charts?segment=gender</font> --> para
				segmentar el recuento por género.<br> <font face="consolas">*/count/2/charts?segment=aut_com</font>
				--> para segmentar el recuento por com. autónoma.</li>
		</ul>

	</div>
</body>
</html>
