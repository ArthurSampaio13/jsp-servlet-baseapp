package br.mendonca.testemaven.controller;

import br.mendonca.testemaven.services.GalaxiaService;
import br.mendonca.testemaven.services.UserService;
import br.mendonca.testemaven.services.dto.GalaxiaDTO;
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

@WebServlet("/dashboard/galaxias")
public class ListGalaxiasServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter page = response.getWriter();

        try {
            GalaxiaService service = new GalaxiaService();
            List<GalaxiaDTO> lista = service.listAllGalaxias();

            // Anexa � requisi��o um objeto ArrayList e despacha a requisi��o para uma JSP.
            request.setAttribute("lista", lista);
            request.getRequestDispatcher("list-galaxias.jsp").forward(request, response);
        } catch (Exception e) {
            // Escreve as mensagens de Exception em uma p�gina de resposta.
            // N�o apagar este bloco.
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);

            page.println("<html lang='pt-br'><head><title>Error</title></head><body>");
            page.println("<h1>Error</h1>");
            page.println("<code>" + sw.toString() + "</code>");
            page.println("</body></html>");
            page.close();
        } finally {

        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter page = response.getWriter();

        try {
            GalaxiaService service = new GalaxiaService();
            String galaxiaId = request.getParameter("galaxiaId");
            String action = request.getParameter("action");

            if (galaxiaId != null && !galaxiaId.isEmpty()) {
                GalaxiaDTO galaxia = service.findGalaxiaById(UUID.fromString(galaxiaId));

                if (galaxia != null) {
                    if ("delete".equalsIgnoreCase(action)) {
                        galaxia.setVisible(false);
                    } else if ("restore".equalsIgnoreCase(action)) {
                        galaxia.setVisible(true);
                    }
                    service.updateGalaxia(galaxia);
                }

                response.sendRedirect("/dashboard/galaxias");
                return;
            }

            // Se `galaxiaId` não estiver presente, cria uma nova galáxia.
            String nome = request.getParameter("nome");
            int quantidadeDeEstrelas = Integer.parseInt(request.getParameter("estrela"));
            boolean viaLactea = request.getParameter("viaLactea") != null;

            service.register(nome, quantidadeDeEstrelas, viaLactea);
            response.sendRedirect("/dashboard/galaxias");

        } catch (Exception e) {
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
        }
    }
}
