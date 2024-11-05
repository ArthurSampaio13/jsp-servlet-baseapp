<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="br.mendonca.testemaven.services.dto.PlanetaDTO"%>

<% if (session.getAttribute("user") != null && request.getAttribute("lista") != null) { %>

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
          <li class="nav-item"><a class="nav-link" href="/dashboard/users">Users</a></li>
          <li class="nav-item"><a class="nav-link" href="/dashboard/about.jsp">About</a></li>
          <li class="nav-item"><a class="nav-link" href="/dashboard/planetas">Planetas</a></li>
        </ul>
        <span class="navbar-text">
						<a class="btn btn-success" href="/auth/logoff">Logoff</a>
					</span>
      </div>
    </div>
  </nav>



  <h1 class="h3 mb-3 fw-normal">Planetas</h1>

  <form action="/dashboard/planetas" method="POST" class="mb-4">
    <div class="mb-3">
      <label for="nome" class="form-label">Nome</label>
      <input type="text" class="form-control" id="nome" name="nome" required>
    </div>
    <div class="mb-3">
      <label for="densidade" class="form-label">Densidade</label>
      <input type="number" class="form-control" id="densidade" name="densidade" required>
    </div>
    <div class="form-check">
      <input class="form-check-input" type="checkbox" id="possuiAgua" name="possuiAgua">
      <label class="form-check-label" for="possuiAgua">Possui Água</label>
    </div>
    <button type="submit" class="btn btn-primary mt-3">Adicionar Planeta</button>
    <p></p>
  </form>

  <table class="table">
    <thead>
    <tr>
      <th scope="col"></th>
      <th scope="col">Nome</th>
      <th scope="col">Densidade</th>
      <th scope="col">Possui Agua</th>
      <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <%
      List<PlanetaDTO> lista = (List<PlanetaDTO>) request.getAttribute("lista");
      for (PlanetaDTO planeta : lista) {
    %>
    <tr>
      <td>Editar</td>
      <td><%= planeta.getNome() %></td>
      <td><%= planeta.getDensidade() %></td>
      <td><%= planeta.isPossuiAgua() %></td>
      <td>Apagar</td>
    </tr>
    <% } %>
    </tbody>
  </table>


</main>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>

<% } %>