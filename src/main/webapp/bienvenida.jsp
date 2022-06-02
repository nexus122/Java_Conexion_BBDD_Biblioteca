<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="servlet.Libro"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://unpkg.com/@picocss/pico@latest/css/pico.min.css">
</head>
<body>
<main class="container">
	<h1>Conexión a base de datos</h1>
	
	<article>
	<h2>Buscar por titulo</h2>
		<form action="./Gestor" method="GET">
			<input placeholder="Titulo del libro" type="text" name="title">
			<input type="hidden" name="type" value="search">
			<input type="submit" value="Buscar">
		</form>
	</article>
	
	<table border=1>
		<tr>
			<th>Id</th>
			<th>Titulo</th>
			<th>Autor</th>
			<th>Editorial</th>
			<th>Fecha</th>
			<th>Categoria</th>
			<th>Novedad</th>
		</tr>
		<%
		ArrayList<Libro> lista = (ArrayList<Libro>) request.getAttribute("lista");
		for (int i = 0; i < lista.size(); i++)
			out.print("<tr>" + 
			"<td>" + lista.get(i).getId() + "</td>" +
			"<td>" + lista.get(i).getTitulo() + "</td>" +
			"<td>"+ lista.get(i).getAutor() + "</td>" +
			"<td>" + lista.get(i).getEditorial() + "</td>" +
			"<td>"+ lista.get(i).getFecha() + "</td>" +
			"<td>" + lista.get(i).getCategoria() + "</td>" +
			"<td>"+ lista.get(i).getNovedad() + "</td>" +			
			"</tr>");
		%>
	</table>

</main>
</body>
</html>