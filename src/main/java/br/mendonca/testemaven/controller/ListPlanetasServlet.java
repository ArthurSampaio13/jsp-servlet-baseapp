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
import java.util.UUID;

@WebServlet("/dashboard/planetas")
public class ListPlanetasServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter page = response.getWriter();

        try {
            PlanetaService service = new PlanetaService();
            String deleteId = request.getParameter("deleteId");
            if (deleteId != null) {
                service.deletarLogico(UUID.fromString(deleteId));
                response.sendRedirect("/dashboard/planetas");
                return;
            }

            String viewDeleted = request.getParameter("viewDeleted");
            List<PlanetaDTO> lista;

            if (viewDeleted != null) {
                int pageNumber = 1;
                String pageParam = request.getParameter("page");
                if (pageParam != null) {
                    pageNumber = Integer.parseInt(pageParam);
                }

                int pageSize = 3;
                lista = service.listPlanetasInativosWithPagination(pageNumber, pageSize);
                int totalPlanetasInativos = service.countPlanetasInativos();
                int totalPages = (int) Math.ceil((double) totalPlanetasInativos / pageSize);
                request.setAttribute("currentPage", pageNumber);
                request.setAttribute("totalPages", totalPages);
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("planetas_deletados.jsp").forward(request, response);

            } else {
                int pageNumber = 1;
                String pageParam = request.getParameter("page");
                if (pageParam != null) {
                    pageNumber = Integer.parseInt(pageParam);
                }

                int pageSize = 3;
                lista = service.listPlanetasWithPagination(pageNumber, pageSize);

                int totalPlanetasAtivos = service.countPlanetas();
                int totalPages = (int) Math.ceil((double) totalPlanetasAtivos / pageSize);

                request.setAttribute("currentPage", pageNumber);
                request.setAttribute("totalPages", totalPages);
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("list-planetas.jsp").forward(request, response);
            }

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
}
