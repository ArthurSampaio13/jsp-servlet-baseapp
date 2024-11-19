<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="br.mendonca.testemaven.services.dto.UserDTO"%>
<%@ page import="java.util.ArrayList" %>

<%
	List<UserDTO> lista = (List<UserDTO>) request.getAttribute("lista");
	if (lista == null) {
		lista = new ArrayList<>();
	}

	String searchQuery = (String) request.getAttribute("searchQuery");
	if (searchQuery == null) {
		searchQuery = "";
	}

	String idadeMinQuery = (String) request.getAttribute("idadeMinQuery");
	if (idadeMinQuery == null) {
		idadeMinQuery = "";
	}

	String idadeMaxQuery = (String) request.getAttribute("idadeMaxQuery");
	if (idadeMaxQuery == null) {
		idadeMaxQuery = "";
	}

	String statusQuery = (String) request.getAttribute("statusQuery");
%>

<!doctype html>
<html lang="pt-br" data-bs-theme="dark">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Gerência de Configuração</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	<link href="style.css" rel="stylesheet">
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">
<main class="w-100 m-auto form-container">

	<h1 class="h3 mb-3 fw-normal">Usuários</h1>

	<!-- Formulário de Pesquisa -->
	<form method="get" action="/dashboard/users" class="mb-3">
		<div class="row g-3">
			<div class="col">
				<input type="text" name="name" class="form-control" placeholder="Nome" value="<%= searchQuery %>">
			</div>
			<div class="col">
				<input type="number" name="idadeMin" class="form-control" placeholder="Idade Mínima" value="<%= idadeMinQuery %>">
			</div>
			<div class="col">
				<input type="number" name="idadeMax" class="form-control" placeholder="Idade Máxima" value="<%= idadeMaxQuery %>">
			</div>
			<div class="col">
				<select name="status" class="form-select">
					<option value="">Status</option>
					<option value="true" <%= "true".equals(statusQuery) ? "selected" : "" %>>Ativo</option>
					<option value="false" <%= "false".equals(statusQuery) ? "selected" : "" %>>Inativo</option>
				</select>
			</div>
			<div class="col">
				<button type="submit" class="btn btn-primary">Filtrar</button>
			</div>
		</div>
	</form>

	<!-- Tabela de Resultados -->
	<table class="table">
		<thead>
		<tr>
			<th scope="col"></th>
			<th scope="col">Nome</th>
			<th scope="col">E-mail</th>
			<th scope="col">Idade</th>
			<th scope="col">Status</th>
			<th scope="col"></th>
		</tr>
		</thead>
		<tbody>
		<%
			if (lista.isEmpty()) {
		%>
		<tr>
			<td colspan="6">Nenhum usuário encontrado.</td>
		</tr>
		<%
		} else {
			for (UserDTO user : lista) {
		%>
		<tr>
			<td>Editar</td>
			<td><%= user.getName() %></td>
			<td><%= user.getEmail() %></td>
			<td><%= user.getIdade() %></td>
			<td><%= user.isStatus() ? "Ativo" : "Inativo" %></td>
			<td>Apagar</td>
		</tr>
		<%
				}
			}
		%>
		</tbody>
	</table>
</main>
</body>
</html>
