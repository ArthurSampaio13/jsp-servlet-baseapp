package br.mendonca.testemaven.controller;

import br.mendonca.testemaven.services.EstrelaService;
import br.mendonca.testemaven.services.dto.EstrelaDTO;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/estrela")
public class EstrelaServletController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final EstrelaService estrelaService = new EstrelaService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter page = response.getWriter();

        try {
            // Lista todas as estrelas usando o serviço
            List<EstrelaDTO> estrelas = estrelaService.listAllEstrelas();

            page.println("<html lang='pt-br'><head><title>Lista de Estrelas</title></head><body>");
            page.println("<h1>Estrelas Cadastradas</h1>");

            page.println("<ul>");
            for (EstrelaDTO estrela : estrelas) {
                page.println("<li>Nome: " + estrela.getNome() + ", Temperatura: " + estrela.getTemperatura() +
                        ", Está na Via Láctea: " + (estrela.getEstaNaViaLactea() ? "Sim" : "Não") + "</li>");
            }
            page.println("</ul>");

            // Formulário para inserir uma nova estrela
            page.println("<h2>Inserir Nova Estrela</h2>");
            page.println("<form method='post' action='/estrela'>");
            page.println("  Nome: <input type='text' name='nome'><br>");
            page.println("  Temperatura: <input type='number' name='temperatura'><br>");
            page.println("  Está na Via Láctea: <input type='checkbox' name='estaNaViaLactea'><br>");
            page.println("  <input type='submit' value='Adicionar Estrela'>");
            page.println("</form>");

            page.println("</body></html>");
            page.close();

        } catch (Exception e) {
            // Exibe erros em uma página HTML
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);

            page.println("<html lang='pt-br'><head><title>Error</title></head><body>");
            page.println("<h1>Error</h1>");
            page.println("<code>" + sw.toString() + "</code>");
            page.println("</body></html>");
            page.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Recebe os parâmetros do formulário
            String nome = request.getParameter("nome");
            Integer temperatura = Integer.parseInt(request.getParameter("temperatura"));
            Boolean estaNaViaLactea = request.getParameter("estaNaViaLactea") != null;

            // Chama o serviço para inserir uma nova estrela
            estrelaService.insertEstrela(nome, temperatura, estaNaViaLactea);

            // Redireciona de volta para a página de estrelas após a inserção
            response.sendRedirect(request.getContextPath() + "/dashboard/estrelas.jsp");

        } catch (Exception e) {
            // Exibe erros em uma página HTML
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);

            response.setContentType("text/html");
            PrintWriter page = response.getWriter();
            page.println("<html lang='pt-br'><head><title>Error</title></head><body>");
            page.println("<h1>Error</h1>");
            page.println("<code>" + sw.toString() + "</code>");
            page.println("</body></html>");
            page.close();
        }
    }

}
