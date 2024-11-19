<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="br.mendonca.testemaven.services.AtividadeService" %>
<%@ page import="br.mendonca.testemaven.services.dto.AtividadeDTO" %>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>


<%
  if (session.getAttribute("user") != null) {
    AtividadeService atividadeService = new AtividadeService();
%>

<!doctype html>
<html lang="pt-br" data-bs-theme="dark">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Criar Estrela</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="style.css" rel="stylesheet">
  <style>
    .timeline .card {
      border-left: 5px solid #0d6efd;
    }
  </style>

</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">

<main class="w-100 m-auto form-container">

  <nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
      <a class="navbar-brand" href="/dashboard/dashboard.jsp">Gerência de Configuração</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
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

  <div class="container mt-4">
    <h1>Timeline de Atividades</h1>
    <div class="timeline">
      <%
        List<AtividadeDTO> atividades = atividadeService.listarAtividades();
        for (AtividadeDTO atividade : atividades) {
      %>
      <div class="card mb-3">
        <div class="card-header">
          <strong><%= atividade.getTipo() %></strong>
          <span class="text-muted float-end"><%= atividade.getDateCreated() %></span>
        </div>
        <div class="card-body">
          <h5 class="card-title"><%= atividade.getNome() %></h5>
          <p class="card-text"> Curtidas: <%= atividade.getQuantidadeCurtidas()%></p>
          <p class="card-text"><%= atividade.getDescricao() %></p>
          <ul>
            <% if (atividade.getTipo().equals("Estrela")) { %>
              <li>Temperatura: <%= atividade.getTemperatura() %> K</li>
              <li>Está na Via Láctea: <%= atividade.getEstaNaViaLactea() ? "Sim" : "Não" %></li>
            <% } else if (atividade.getTipo().equals("Galaxia")) { %>
              <li>Quantidade de Estrelas: <%= atividade.getQuantidadeDeEstrelas() %></li>
              <li>Está na Via Láctea: <%= atividade.getEstaNaViaLactea() ? "Sim" : "Não" %></li>
            <% } else if (atividade.getTipo().equals("Planeta")) { %>
              <li>Possui Água: <%= atividade.getPossuiAgua() ? "Sim" : "Não" %></li>
              <li>Densidade: <%= atividade.getDensidade() %> g/cm³</li>
            <% } %>
          </ul>
          <button type="button" class="btn btn-danger" onclick="curtirAtividade('<%= atividade.getId() %>')">
            curtir
          </button>
        </div>
      </div>
      <% } %>
    </div>
  </div>

</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

</body>


<script>
  function curtirAtividade(uuid) {
    fetch('/curtirPost?uuid=' + uuid, { method: 'POST' })
            .then(response => {
              if (response.ok) {
                window.location.reload(true);
              } else {
                alert('Erro ao curtir atividade');
              }
            });
  }
</script>


</html>


<%
  } else {
    response.sendRedirect("/login.jsp");
  }
%>
