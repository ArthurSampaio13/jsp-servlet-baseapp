<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="br.mendonca.testemaven.services.dto.UserDTO"%>

<%
	if (session.getAttribute("user") != null) {
%>
<!doctype html>
<html lang="pt-br" data-bs-theme="dark">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Gerência de Configuração</title>
	<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
			rel="stylesheet"
			integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
			crossorigin="anonymous">
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


	<h1 class="h3 mb-3 fw-normal">About</h1>
	<table class="table">
		<thead>
		<tr>
			<th scope="col">Nome</th>
			<th scope="col">Entidade</th>
			<th scope="col">Regra de negócio</th>
		</tr>
		</thead>
		<tr>
			<td scope="col">Ewerton Mendonça</th>
			<td scope="col">Users</th>
			<td scope="col">Autenticação</th>
		</tr>
		<tr>
			<td scope="col">Jeová Bezerra Leite</th>
			<td scope="col">Estrelas</th>
			<td scope="col">O usuário pode visulizar uma lista de ativiades pelo tempo (timeline) seus e dos outros usuários que cadastraram alguma entidade e curtir a atividade, a atividade na timeline mostra a quantidade de curtidas</th>
		</tr>
		<tr>
			<td scope="col">Arthur Lopes Filgueira Sampaio</th>
			<td scope="col">Planetas</th>
			<td scope="col">o usuário pode, na listagem de usuário, procurar por um usuário por parte do nome e utilizar filtros: um para a propriedade de número idade e outro para a propriedade booleana status. Os dados do banco devem vir filtrados por um ou mais endpoints</th>
		</tr>
		<tr>
			<td scope="col">Marcos Pierre de Melo Monteiro</th>
			<td scope="col">Galaxia</th>
			<td scope="col">O usuario pode, na listagem de usuarios, selecionar um usuário para seguir e através do endpoint 'seguindo' visualizar quem ele segue e deixar de seguir</th>
		</tr>
		<tbody>
		</tbody>
	</table>


</main>



<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous">

</script>
</body>
</html>
<%
	}
%>