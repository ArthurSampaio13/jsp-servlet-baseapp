<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="br.mendonca.testemaven.services.dto.PlanetaDTO"%>

<!doctype html>
<html lang="pt-br" data-bs-theme="dark">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Planetas Deletados</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="style.css" rel="stylesheet">
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">

<main class="w-100 m-auto form-container">
    <h1 class="h3 mb-3 fw-normal">Planetas Deletados</h1>

    <!-- Botão para voltar à listagem de planetas ativos -->
    <a href="/dashboard/planetas" class="btn btn-primary mb-4">Voltar para Planetas Ativos</a>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">Nome</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<PlanetaDTO> listaDeletados = (List<PlanetaDTO>) request.getAttribute("lista");
            for (PlanetaDTO planeta : listaDeletados) {
        %>
        <tr>
            <!-- Link para abrir o modal com informações detalhadas do planeta -->
            <td>
                <a href="#" class="planet-link"
                   data-nome="<%= planeta.getNome() %>"
                   data-densidade="<%= planeta.getDensidade() %>"
                   data-agua="<%= planeta.isPossuiAgua() %>">
                    <%= planeta.getNome() %>
                </a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>

    <!-- Paginação -->
    <%
        Integer currentPage = (Integer) request.getAttribute("currentPage");
        Integer totalPages = (Integer) request.getAttribute("totalPages");

        int previousPage = (currentPage != null && currentPage > 1) ? currentPage - 1 : 1;
        int nextPage = (currentPage != null && currentPage < totalPages) ? currentPage + 1 : 1;
    %>

    <% if (currentPage != null && totalPages != null) { %>
    <div class="d-flex justify-content-between align-items-center">
        <!-- Botão Anterior -->
        <a href="?viewDeleted=true&page=<%= previousPage %>"
           class="btn btn-primary <%= currentPage == 1 ? "disabled" : "" %>">
            Anterior
        </a>

        <!-- Número da Página -->
        <span>Página <%= currentPage %> de <%= totalPages %></span>

        <!-- Botão Próxima -->
        <a href="?viewDeleted=true&page=<%= nextPage %>"
           class="btn btn-primary <%= currentPage == totalPages ? "disabled" : "" %>">
            Próxima
        </a>
    </div>
    <% } %>

    <!-- Modal para exibir informações detalhadas do planeta -->
    <div class="modal fade" id="planetModal" tabindex="-1" aria-labelledby="planetModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="planetModalLabel">Informações do Planeta</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p><strong>Nome:</strong> <span id="modalNome"></span></p>
                    <p><strong>Densidade:</strong> <span id="modalDensidade"></span></p>
                    <p><strong>Possui Água:</strong> <span id="modalAgua"></span></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
                </div>
            </div>
        </div>
    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        const planetLinks = document.querySelectorAll(".planet-link");

        planetLinks.forEach(link => {
            link.addEventListener("click", function(event) {
                event.preventDefault();

                // Pega os dados do planeta a partir dos atributos data-*
                document.getElementById("modalNome").textContent = this.dataset.nome;
                document.getElementById("modalDensidade").textContent = this.dataset.densidade;
                document.getElementById("modalAgua").textContent = this.dataset.agua === "true" ? "Sim" : "Não";

                // Exibe o modal
                new bootstrap.Modal(document.getElementById("planetModal")).show();
            });
        });
    });
</script>

</body>
</html>
