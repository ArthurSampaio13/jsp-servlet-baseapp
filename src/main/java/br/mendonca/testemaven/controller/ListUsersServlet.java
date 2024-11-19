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
			String name = request.getParameter("name");
			String idadeMinStr = request.getParameter("idadeMin");
			String idadeMaxStr = request.getParameter("idadeMax");
			String statusStr = request.getParameter("status");

			Integer idadeMin = (idadeMinStr != null && !idadeMinStr.isEmpty()) ? Integer.parseInt(idadeMinStr) : null;
			Integer idadeMax = (idadeMaxStr != null && !idadeMaxStr.isEmpty()) ? Integer.parseInt(idadeMaxStr) : null;
			Boolean status = (statusStr != null && !statusStr.isEmpty()) ? Boolean.parseBoolean(statusStr) : null;

			UserService service = new UserService();
			List<UserDTO> lista = service.filterUsers(name, idadeMin, idadeMax, status);

			request.setAttribute("lista", lista);
			request.setAttribute("searchQuery", name);
			request.setAttribute("idadeMinQuery", idadeMinStr);
			request.setAttribute("idadeMaxQuery", idadeMaxStr);
			request.setAttribute("statusQuery", statusStr);

			request.getRequestDispatcher("/dashboard/list-users.jsp").forward(request, response);
			if ("listFollowing".equals(action)) {
				List<UserDTO> following = service.listFollowing(followerUuid);
				request.setAttribute("following",following);
				request.getRequestDispatcher("seguindo.jsp").forward(request, response);
			} else {
				// Listar todos os usuários
				request.setAttribute("lista", lista); // Define o atributo para a JSP
				request.getRequestDispatcher("list-users.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao carregar usuÃ¡rios.");
		}
	}
	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String followerUuid = (String) request.getSession().getAttribute("userUuid"); // Obtém o UUID do usuário da sessão
		String followedUuid = request.getParameter("followedUuid");
		PrintWriter page = response.getWriter();

		try {
			// A programaï¿½ï¿½o do servlet deve ser colocada neste bloco try.
			// Apague o conteï¿½do deste bloco try e escreva seu cï¿½digo.
			String parametro = request.getParameter("nomeparametro");
			
			page.println("Parametro: " + parametro);
			page.close();
			
			
			if ("follow".equals(action) && followerUuid != null) {
				service.followUser(followerUuid, followedUuid);
				response.sendRedirect("/dashboard/seguindo.jsp");
			} else if ("unfollow".equals(action) && followerUuid != null) {
				service.unfollowUser(followerUuid, followedUuid);
			}
			response.sendRedirect("/dashboard/users"); // Atualiza a página
		} catch (Exception e) {
			// Escreve as mensagens de Exception em uma pï¿½gina de resposta.
			// Nï¿½o apagar este bloco.
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

	@WebServlet("/dashboard/users/search")
	public class SearchUsersServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			try {
				String name = request.getParameter("name");
				UserService service = new UserService();
				List<UserDTO> users = service.searchByName(name);

				StringBuilder json = new StringBuilder("[");
				for (int i = 0; i < users.size(); i++) {
					UserDTO user = users.get(i);
					json.append("{")
							.append("\"name\":\"").append(user.getName()).append("\",")
							.append("\"email\":\"").append(user.getEmail()).append("\"")
							.append("}");
					if (i < users.size() - 1) {
						json.append(",");
					}
				}
				json.append("]");

				response.getWriter().write(json.toString());
			} catch (Exception e) {
				e.printStackTrace();
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.getWriter().write("{\"error\":\"Internal server error\"}");
			}
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
