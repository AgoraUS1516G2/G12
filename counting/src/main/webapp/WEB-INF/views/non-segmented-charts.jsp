<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Charts</title>
<script type="text/javascript"
	src="https://www.google.com/jsapi?autoload={'modules':[{'name':'visualization','version':'1.1','packages':['corechart']}]}"></script>
<script>
	google.setOnLoadCallback(drawChart);
	function drawChart() {

		<c:forEach var="question" items="${data}" varStatus="loopCounter">
				var arrays = [[ 'Answer', 'Count' ]];
				arrays.push([ 'S�', parseInt("${question.yes}") ]);
				arrays.push([ 'No', parseInt("${question.no}") ]);

				var data = google.visualization.arrayToDataTable(arrays);

				var options = {
					title : "${question.question}",
					is3D: true 
				};

				var chart = new google.visualization.PieChart(document
				.getElementById('piechart${loopCounter.count}'));

				chart.draw(data, options);
		</c:forEach>

	}
</script>
</head>
<body>

	<c:forEach var="question" items="${data}" varStatus="loopCounter">
			<div id="piechart${loopCounter.count}" style="width: 900px; height: 500px;"></div>
	</c:forEach>

</body>
</html>
