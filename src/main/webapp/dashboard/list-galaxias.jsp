<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="br.mendonca.testemaven.services.dto.GalaxiaDTO"%>
<%@ page import="br.mendonca.testemaven.services.GalaxiaService" %>

<%
  if (session.getAttribute("user") != null) {
    GalaxiaService galaxiaService = new GalaxiaService();

    // Definir quantos registros por página
    int registrosPorPagina = 3;

    // Obter o número da página atual
    String paginaParam = request.getParameter("pagina");
    int paginaAtual = (paginaParam != null && !paginaParam.isEmpty()) ? Integer.parseInt(paginaParam) : 1;

    // Obter a lista completa de galáxias
    List<GalaxiaDTO> galaxias = galaxiaService.listAllGalaxias();

    // Calcular o total de páginas
    int totalRegistros = galaxias.size();
    int totalPaginas = (int) Math.ceil((double) totalRegistros / registrosPorPagina);

    // Calcular os índices de início e fim para a página atual
    int inicio = (paginaAtual - 1) * registrosPorPagina;
    int fim = Math.min(inicio + registrosPorPagina, totalRegistros);

    // Sublistar as galáxias para a página atual
    List<GalaxiaDTO> galaxiasPagina = galaxias.subList(inicio, fim);
%>

<!doctype html>
<html lang="pt-br" data-bs-theme="dark">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Gerenciamento de Galáxias</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <link href="style.css" rel="stylesheet">
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">

<main class="w-100 m-auto form-container">
  <nav class="navbar navbar-expand-lg bg-body-terciary">
    <div class="container-fluid">
      <a class="navbar-brand" href="/dashboard/dashboard.jsp">Gerência de Configuração</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item"><a class="nav-link" href="/dashboard/dashboard.jsp">Home</a></li>
          <li class="nav-item"><a class="nav-link" href="/dashboard/users">Users</a></li>
          <li class="nav-item"><a class="nav-link" href="/dashboard/estrelas.jsp">Estrelas</a></li>
          <li class="nav-item"><a class="nav-link" href="/dashboard/planetas">Planetas</a></li>
          <li class="nav-item"><a class="nav-link" href="/dashboard/list-galaxias.jsp">Galáxias</a></li>
          <li class="nav-item"><a class="nav-link" href="/dashboard/about.jsp">About</a></li>
        </ul>
        <span class="navbar-text">
          <a class="btn btn-success" href="/auth/logoff">Logoff</a>
        </span>
      </div>
    </div>
  </nav>

  <!-- Formulário de Criação de Galáxia -->
  <div class="container mt-4">
    <h1>Criar Galáxia</h1>
    <form method="post" action="/dashboard/galaxias">
      <div class="mb-3">
        <label for="nome" class="form-label">Nome da Galáxia</label>
        <input type="text" class="form-control" id="nome" name="nome" required>
      </div>
      <div class="mb-3">
        <label for="estrela" class="form-label">Quantidade de estrelas</label>
        <input type="number" class="form-control" id="estrela" name="estrela" required min="0">
      </div>
      <div class="mb-3 form-check">
        <input type="checkbox" class="form-check-input" id="viaLactea" name="viaLactea">
        <label class="form-check-label" for="viaLactea">É a Via Láctea?</label>
      </div>
      <button type="submit" class="btn btn-primary">Criar Galáxia</button>
    </form>
  </div>

  <!-- Lista de Galáxias Cadastradas -->
  <div class="container mt-4">
    <h2>Galáxias Cadastradas</h2>
    <ul class="list-group">
      <% for (GalaxiaDTO galaxia : galaxiasPagina) { %>
      <li class="list-group-item d-flex justify-content-between align-items-center">
        <div>
          <strong>Nome:</strong> <%= galaxia.getNome() %> |
          <strong>Quantidade de estrelas:</strong> <%= galaxia.getQuantidadeDeEstrelas() %> |
          <strong>É a Via Láctea?:</strong> <%= galaxia.isViaLactea() ? "Sim" : "Não" %>
        </div>
        <button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#detalhesModal<%= galaxia.getId() %>">
          Ver Detalhes
        </button>
      </li>

      <!-- Modal para detalhes da galáxia -->
      <div class="modal fade" id="detalhesModal<%= galaxia.getId() %>" tabindex="-1" aria-labelledby="detalhesModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="detalhesModalLabel">Detalhes da Galáxia</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <p><strong>UUID:</strong> <%= galaxia.getId() %></p>
              <p><strong>Nome:</strong> <%= galaxia.getNome() %></p>
              <p><strong>Quantidade de estrelas:</strong> <%= galaxia.getQuantidadeDeEstrelas() %> K</p>
              <p><strong>É a Via Láctea?:</strong> <%= galaxia.isViaLactea() ? "Sim" : "Não" %></p>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
            </div>
          </div>
        </div>
      </div>
      <% } %>
    </ul>

    <!-- Controles de navegação -->
    <div class="mt-4 d-flex justify-content-between">
      <% if (paginaAtual > 1) { %>
      <a href="?pagina=<%= paginaAtual - 1 %>" class="btn btn-secondary">Anterior</a>
      <% } %>

      <span>Página <%= paginaAtual %> de <%= totalPaginas %></span>

      <% if (paginaAtual < totalPaginas) { %>
      <a href="?pagina=<%= paginaAtual + 1 %>" class="btn btn-secondary">Próxima</a>
      <% } %>
    </div>

  </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>

<%
  } else {
    response.sendRedirect("/login.jsp");
  }
%>
