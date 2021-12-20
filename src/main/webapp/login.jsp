<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<link rel="shortcut icon" href="/AgenciaTurismo/assets/favicon.ico">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Lato&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="<%= request.getContextPath() %>/login.css">
<title>Turismo en Tierra Media</title>
</head>

<body>


	<main>

		<div>

			
				<%
				String flash = (String) request.getAttribute("flash");
				%>

				<%
				if (flash != null) {
				%>
				<div class="alert alert-danger" role="alert">
				<strong><%=flash%> </strong>
				</div>
				<%
				}
				%>
				
				
			
			<p>Bienvenido a la agencia de turismo oficial de Tierra Media</p>
			<form action="<%= request.getContextPath() %>/login" method="post">
				<label for="formGroupExampleInput" class="form-label justify-content-center"><i>Ingrese
						su nombre de usuario</i> </label>
						 <input type="text" class="form-control"
					id="formGroupExampleInput" placeholder="Ingrese su nombre..."
					name="username"> 
					<input type="submit" class="btn btn-dark"
					value="Ingresar">

			</form>
		</div>

	</main>

	<script src="index.js"></script>
</body>

</html>

