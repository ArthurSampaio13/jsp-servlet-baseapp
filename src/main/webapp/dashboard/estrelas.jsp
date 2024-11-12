<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="br.mendonca.testemaven.services.dto.EstrelaDTO"%>
<%@ page import="br.mendonca.testemaven.services.EstrelaService"%>

<%
  if (session.getAttribute("user") != null) {
    EstrelaService estrelaService = new EstrelaService();
    int pageSize = 3; // Número de estrelas por página
    int pageNumber = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;

    // Chama o serviço para contar o total de estrelas
    int totalEstrelas = estrelaService.countEstrelas();
    int totalPages = (int) Math.ceil((double) totalEstrelas / pageSize); // Total de páginas

    // Lista de estrelas para a página atual
    List<EstrelaDTO> estrelas = estrelaService.listAllEstrelasPaginada(pageNumber, pageSize);
%>

<!doctype html>
<html lang="pt-br" data-bs-theme="dark">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Criar Estrela</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="style.css" rel="stylesheet">
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
          <li class="nav-item"><a class="nav-link" href="/dashboard/users">Users</a></li>
          <li class="nav-item"><a class="nav-link" href="/dashboard/estrelas.jsp">Estrelas</a></li>
          <li class="nav-item"><a class="nav-link" href="/dashboard/planetas">Planetas</a></li>
          <li class="nav-item"><a class="nav-link" href="/dashboard/list-galaxias.jsp">Galaxias</a></li>
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

    <!-- Lista de Estrelas Cadastradas -->
    <div class="mt-5">
      <h2>Estrelas Cadastradas</h2>
      <ul class="list-group">
        <% for (EstrelaDTO estrela : estrelas) { %>
        <li class="list-group-item d-flex justify-content-between align-items-center">
          <div>
            <strong>Nome:</strong> <%= estrela.getNome() %> |
          </div>
          <div class="d-flex gap-2">
            <button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#detalhesModal<%= estrela.getUuid() %>">
              Ver Detalhes
            </button>
            <button type="button" class="btn btn-danger" onclick="marcarComoInvisivel('<%= estrela.getUuid() %>')">
              Deletar
            </button>
          </div>
        </li>



        <!-- Modal para detalhes da estrela -->
        <div class="modal fade" id="detalhesModal<%= estrela.getUuid() %>" tabindex="-1" aria-labelledby="detalhesModalLabel" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="detalhesModalLabel">Detalhes da Estrela</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                <p><strong>Nome:</strong> <%= estrela.getNome() %></p>
                <p><strong>Temperatura:</strong> <%= estrela.getTemperatura() %> K</p>
                <p><strong>Está na Via Láctea:</strong> <%= estrela.getEstaNaViaLactea() ? "Sim" : "Não" %></p>
                <!-- Adicione outras informações aqui se houver -->
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
              </div>
            </div>
          </div>
        </div>
        <% } %>
      </ul>

      <!-- Navegação de Páginas -->
      <div class="mt-4">
        <nav>
          <ul class="pagination">
            <li class="page-item <%= (pageNumber == 1) ? "disabled" : "" %>">
              <a class="page-link" href="?page=<%= pageNumber - 1 %>">Anterior</a>
            </li>

            <!-- Impede ir além da última página -->
            <li class="page-item <%= (pageNumber == totalPages) ? "disabled" : "" %>">
              <a class="page-link" href="?page=<%= pageNumber + 1 %>">Próxima</a>
            </li>
          </ul>
        </nav>
      </div>
    </div>
  </div>

</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

<script>
  function marcarComoInvisivel(uuid) {
    if (confirm("Deseja realmente excluir esta estrela da listagem?")) {
      fetch('/estrela/marcarComoInvisivel?uuid=' + uuid, { method: 'POST' })
              .then(response => {
                if (response.ok) {
                  alert("Estrela marcada para não visualização.");
                  window.location.reload(true);
                } else {
                  alert("Erro ao excluir a estrela.");
                }
              });
    }
  }
</script>
</body>
</html>

<%
  } else {
    response.sendRedirect("/login.jsp");
  }
%>
