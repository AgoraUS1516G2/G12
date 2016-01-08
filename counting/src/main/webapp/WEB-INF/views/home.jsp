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
	<h1>Subsistema "Recuento y Modificación de Resultados"</h1>

	<p class="flow-text">Subsistema encargado de la realización del
		recuento y la modificación de los resultados de una votación
		determinada. Para la realización del recuento se accederá a la base de
		datos proporcionada por almacenamiento y utilizará los métodos
		proporcionados por verificación para descifrar los votos obtenidos.</p>

	<div class="card-panel light-green accent-1">
		<p class="flow-text">

			Nota:<br> Los tiempos de respuestas pueden ser bastante amplios.<br>
			No es responsabilidad del subsistema de recuento ni de ningún miembro
			relacionado con el subsistema.<br> Se trata de un problema sobre
			el lugar donde está desplegado el subsistema de Verificación, el cual
			debe ser consultado para el descifrado. <br> Aún así, disculpen
			las molestias causadas.
		</p>
	</div>
	<br>


	<h4>Listado de votaciones existentes:</h4>
	<br>

	<h5>Mostrado sin visualización</h5>

	<table class="table-responsive">
		<thead>
			<tr>
				<th>Votación</th>
				<th>Enlace</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>Votación 1</td>
				<td><a
					href="https://recuento.herokuapp.com/count/1?cod=special">Ver</a></td>
			</tr>

			<tr>
				<td>Votación 2</td>
				<td><a href="https://recuento.herokuapp.com/count/2">Ver</a></td>
			</tr>
		</tbody>
	</table>

	<h5>Mostrado con visualización</h5>

	<table class="table-responsive">
		<thead>
			<tr>
				<th>Votación</th>
				<th>Enlace</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>Votación 1</td>
				<td><a
					href="https://recuento.herokuapp.com/count/1/charts?cod=special">Ver</a></td>
			</tr>

			<tr>
				<td>Votación 2</td>
				<td><a href="https://recuento.herokuapp.com/count/2/charts">Ver</a></td>
			</tr>
		</tbody>
	</table>

	<h5>Mostrado segmentado</h5>
	<br>
	<h6>
		<b>Por edad</b>
	</h6>
	<br>

	<table class="table-responsive">
		<thead>
			<tr>
				<th>Votación</th>
				<th>Enlace</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>Votación 1</td>
				<td><a
					href="https://recuento.herokuapp.com/count/1/charts?cod=special&segment=age">Ver</a></td>
			</tr>

			<tr>
				<td>Votación 2</td>
				<td><a
					href="https://recuento.herokuapp.com/count/2/charts?segment=age">Ver</a></td>
			</tr>
		</tbody>
	</table>
	<br>

	<h6>
		<b>Por género</b>
	</h6>
	<br>

	<table class="table-responsive">
		<thead>
			<tr>
				<th>Votación</th>
				<th>Enlace</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>Votación 1</td>
				<td><a
					href="https://recuento.herokuapp.com/count/1/charts?cod=special&segment=gender">Ver</a></td>
			</tr>

			<tr>
				<td>Votación 2</td>
				<td><a
					href="https://recuento.herokuapp.com/count/2/charts?segment=gender">Ver</a></td>
			</tr>
		</tbody>
	</table>
	<br>


	<h6>
		<b>Por comunidad autonoma</b>
	</h6>
	<br>


	<table class="table-responsive">
		<thead>
			<tr>
				<th>Votación</th>
				<th>Enlace</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>Votación 1</td>
				<td><a
					href="https://recuento.herokuapp.com/count/1/charts?cod=special&segment=aut_com">Ver</a></td>
			</tr>

			<tr>
				<td>Votación 2</td>
				<td><a
					href="https://recuento.herokuapp.com/count/2/charts?segment=aut_com">Ver</a></td>
			</tr>
		</tbody>
	</table>
	<br>
	<br>

	<ul class="collection with-header">
		<li class="collection-header"><h4>API REST del subsistema:</h4></li>
		<li class="collection-item">*/count/predefined --> Test de
			funcionamiento de una votación.</li>
		<li class="collection-item">*/count/{idVotacion} --> Recuento de
			un referéndum.</li>
		<li class="collection-item"><b>Se puede añadir el parámetro
				"cod", dependiendo de la codificación de los votos.</b><br> <br>
			*/count/1?cod=special --> si se esperan recibir los votos como array
			de bytes. <br> */count/2 o */count/2?cod=normal --> si se
			esperan recibir los votos como un hash cadena cifrado.</li>
		<li class="collection-item"><b>Es posible segmentar el
				recuento mediante el parámetro "segment" por edad, género y
				comunidad autónoma.</b> <br> <br> */count/2?segment=age -->
			para segmentar el recuento por edad.<br>
			*/count/2?segment=gender --> para segmentar el recuento por género.<br>
			*/count/2?segment=aut_com --> para segmentar el recuento por com.
			autónoma.</li>
		<li class="collection-item"><b>Para visualizar los resultados
				de una votación por segmento, invocar las siguientes rutas:</b><br>
			<br> */count/2/charts?segment=age --> para segmentar el recuento
			por edad.<br> */count/2/charts?segment=gender --> para segmentar
			el recuento por género.<br> */count/2/charts?segment=aut_com -->
			para segmentar el recuento por com. autónoma.</li>
	</ul>

</body>
</html>
