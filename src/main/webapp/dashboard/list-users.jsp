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
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<a class="navbar-brand" href="/dashboard/dashboard.jsp">Gerência de Configuração</a>
			<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarText"
					aria-controls="navbarText" aria-expanded="false"
					aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarText">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link" href="/dashboard/dashboard.jsp">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="/dashboard/timeLine.jsp">TimeLine</a></li>
					<li class="nav-item"><a class="nav-link" href="/dashboard/users">Users</a></li>
					<li class="nav-item"><a class="nav-link" href="/dashboard/estrelas.jsp">Estrelas</a></li>
					<li class="nav-item"><a class="nav-link" href="/dashboard/estrelasDeletadas.jsp">Estrelas deletadas</a></li>
					<li class="nav-item"><a class="nav-link" href="/dashboard/planetas">Planetas</a></li>
					<li class="nav-item"><a class="nav-link" href="/dashboard/planetas?viewDeleted=true">Planetas deletados</a></li>
					<li class="nav-item"><a class="nav-link" href="/dashboard/list-galaxias.jsp">Galaxias</a></li>
					<li class="nav-item"><a class="nav-link" href="/dashboard/galaxiasDeletadas.jsp">Galaxias deletadas</a></li>
					<li class="nav-item"><a class="nav-link" href="/dashboard/seguindo?action=listFollowing">Seguindo</a></li>
					<li class="nav-item"><a class="nav-link" href="/dashboard/about.jsp">About</a></li>
				</ul>
				<span class="navbar-text">
                    <a class="btn btn-success" href="/auth/logoff">Logoff</a>
                </span>
			</div>
		</div>
	</nav>

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
			<th scope="col">Nome</th>
			<th scope="col">E-mail</th>
			<th scope="col">Idade</th>
			<th scope="col">Status</th>
			<th scope="col">Seguir</th>
			<th scope="col">Apagar</th>
		</tr>
		</thead>
		<tbody>
		<%
			// 'lista' has already been defined at the top, so no need to declare it here again.
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
			<td><%= user.getName() %></td>
			<td><%= user.getEmail() %></td>
			<td><%= user.getIdade() %></td>
			<td><%= user.isStatus() ? "Ativo" : "Inativo" %></td>
			<td>
				<form method="post" action="/dashboard/seguindo?action=follow">
					<input type="hidden" name="action" value="follow">
					<input type="hidden" name="followerUuid" value="<%= session.getAttribute("userUuid") %>">
					<input type="hidden" name="followedUuid" value="<%= user.getUserId() %>">
					<button type="submit" class="btn btn-primary btn-sm">Seguir</button>
				</form>
			</td>
			<td>Apagar</td>
		</tr>
		<%
				}
			}
		%>
		</tbody>
	</table>

</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

</body>
</html>
