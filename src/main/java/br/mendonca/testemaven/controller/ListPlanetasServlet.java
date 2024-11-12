package br.mendonca.testemaven.controller;

import br.mendonca.testemaven.services.PlanetaService;
import br.mendonca.testemaven.services.UserService;
import br.mendonca.testemaven.services.dto.PlanetaDTO;
import br.mendonca.testemaven.services.dto.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@WebServlet("/dashboard/planetas")
public class ListPlanetasServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter page = response.getWriter();

        try {
            int pageNumber = 1;
            String pageParam = request.getParameter("page");
            if (pageParam != null) {
                pageNumber = Integer.parseInt(pageParam);
            }

            int pageSize = 3;
            PlanetaService service = new PlanetaService();

            // Obtém a lista de planetas com paginação
            List<PlanetaDTO> lista = service.listPlanetasWithPagination(pageNumber, pageSize);

            // Conta o total de planetas para calcular o número total de páginas
            int totalPlanetas = service.countPlanetas();
            int totalPages = (int) Math.ceil((double) totalPlanetas / pageSize);

            // Envia a lista e as informações de paginação para a JSP
            request.setAttribute("lista", lista);
            request.setAttribute("currentPage", pageNumber);
            request.setAttribute("totalPages", totalPages);
            request.getRequestDispatcher("list-planetas.jsp").forward(request, response);

        } catch (Exception e) {
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


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter page = response.getWriter();

        try {
            String nome = request.getParameter("nome");
            int densidade = Integer.parseInt(request.getParameter("densidade"));
            boolean possuiAgua = request.getParameter("possuiAgua") != null;

            PlanetaService service = new PlanetaService();
            service.adicionar(nome, densidade, possuiAgua);

            response.sendRedirect("/dashboard/planetas");


        } catch (Exception e) {
            // Escreve as mensagens de Exception em uma p�gina de resposta.
            // N�o apagar este bloco.
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);

            page.println("<html lang='pt-br'><head><title>Error</title></head><body>");
            page.println("<h1>Error</h1>");
            page.println("<code>");
            page.println(sw.toString());
            page.println("</code>");
            page.println("</body></html>");
            page.close();
        } finally {

        }
    }
}
