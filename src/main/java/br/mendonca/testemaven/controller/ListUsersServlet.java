package br.mendonca.testemaven.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import br.mendonca.testemaven.services.UserService;
import br.mendonca.testemaven.services.dto.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/dashboard/users")
public class ListUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserService service = new UserService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		String action = request.getParameter("action");

		String followerUuid = (String) request.getSession().getAttribute("userUuid"); // Obtém o UUID do usuário da sessão
		System.out.println("fora do try catch");
		System.out.println(action);
		try {
			if ("listFollowing".equals(action)) {
				List<UserDTO> following = service.listFollowing(followerUuid);
				request.setAttribute("following",following);
				request.getRequestDispatcher("seguindo.jsp").forward(request, response);
			} else {
				// Listar todos os usuários
				List<UserDTO> lista = service.listAllUsers();
				request.setAttribute("lista", lista); // Define o atributo para a JSP
				request.getRequestDispatcher("list-users.jsp").forward(request, response);
			}
		} catch (Exception e) {
			handleException(response, e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String followerUuid = (String) request.getSession().getAttribute("userUuid"); // Obtém o UUID do usuário da sessão
		String followedUuid = request.getParameter("followedUuid");

		try {
			if ("follow".equals(action) && followerUuid != null) {
				service.followUser(followerUuid, followedUuid);
				response.sendRedirect("/dashboard/seguindo.jsp");
			} else if ("unfollow".equals(action) && followerUuid != null) {
				service.unfollowUser(followerUuid, followedUuid);
			}
			response.sendRedirect("/dashboard/users"); // Atualiza a página
		} catch (Exception e) {
			handleException(response, e);
		}

	}

	private void handleException(HttpServletResponse response, Exception e) throws IOException {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		response.setContentType("text/html");
		PrintWriter page = response.getWriter();
		page.println("<html lang='pt-br'><head><title>Error</title></head><body>");
		page.println("<h1>Error</h1>");
		page.println("<code>");
		page.println(sw.toString());
		page.println("</code>");
		page.println("</body></html>");
		page.close();
	}
}
