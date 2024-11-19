package br.mendonca.testemaven.controller;

import br.mendonca.testemaven.dao.CurtidaDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/curtirPost")
public class CurtirPostServlet extends HttpServlet {
    private CurtidaDAO curtidaService = new CurtidaDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String postId = request.getParameter("uuid");
        try {
            curtidaService.curtirPost(postId);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao curtir post.");
        }
    }
}
