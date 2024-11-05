<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="br.mendonca.testemaven.services.dto.EstrelaDTO"%>

<%
  if (session.getAttribute("user") != null) {
%>

<!doctype html>
<html lang="pt-br" data-bs-theme="dark">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Criar Estrela</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
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
          <li class="nav-item"><a class="nav-link" href="/dashboard/users">Users</a></li>
          <li class="nav-item"><a class="nav-link" href="/dashboard/estrelas">Estrelas</a></li>
          <li class="nav-item"><a class="nav-link" href="/dashboard/about.jsp">About</a></li>
        </ul>
        <span class="navbar-text">
                    <a class="btn btn-success" href="/auth/logoff">Logoff</a>
                </span>
      </div>
    </div>
  </nav>

  <div class="container mt-4">
    <h1>Criar Estrela</h1>
    <form method="post" action="/estrela">
      <div class="mb-3">
        <label for="nome" class="form-label">Nome da Estrela</label>
        <input type="text" class="form-control" id="nome" name="nome" required>
      </div>
      <div class="mb-3">
        <label for="temperatura" class="form-label">Temperatura (em Kelvin)</label>
        <input type="number" class="form-control" id="temperatura" name="temperatura" required>
      </div>
      <div class="mb-3 form-check">
        <input type="checkbox" class="form-check-input" id="estaNaViaLactea" name="estaNaViaLactea">
        <label class="form-check-label" for="estaNaViaLactea">Está na Via Láctea?</label>
      </div>
      <button type="submit" class="btn btn-primary">Criar Estrela</button>
    </form>
  </div>

</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>

<%
  } else {
    response.sendRedirect("/login.jsp"); // Redireciona para a página de login se o usuário não estiver autenticado
  }
%>
