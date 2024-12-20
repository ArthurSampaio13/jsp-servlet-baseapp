package br.mendonca.testemaven.controller.install;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import br.mendonca.testemaven.services.InstallService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/install")
public class InstallDatabaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter page = response.getWriter();
		
		try {
			InstallService service = new InstallService();
			String msg = "<h1>INSTALL DATABASE</h1>";
			
			service.testConnection();
			msg += "<h2>Connection DB sucessful!</h2>\n";
			
			service.deleteUserTable();
			msg += "<h2>Delete table user sucessful!</h2>\n";

			service.deleteEstrelaTable();
			msg += "<h2>Delete table estrela sucessful!</h2>\n";
			
			service.createUserTable();
			msg += "<h2>Create table user sucessful!</h2>\n";

			service.deleteSeguidoresTable();
			msg += "<h2>deleted table seguidores sucessful!</h2>\n";

			service.createSeguidoresTable();
			msg += "<h2>Create table seguidores sucessful!</h2>\n";

			service.createEstrelaTable();
			msg += "<h2>Create table estrela sucessful!</h2>\n";

			service.populate7EstrelaTable();
			msg += "<h2>Populate table estrela sucessful!</h2>\n";

			service.deletePlanetasTable();
			msg += "<h2>Delete table planetas sucessful!</h2>\n";

			service.createPlanetasTable();
			msg += "<h2>Create table planetas sucessful!</h2>\n";

			service.populate7PlanetaTable();
			msg += "<h2>7 Planetas de exemplo adicionados com sucesso!</h2>\n";

			service.deleteTableCurtidas();
			msg += "<h2>Delete table curtidas successful!</h2>\n";

			service.createTableCurtidas();
			msg += "<h2>Create table curtidas successful!</h2>\n";

			page.println("<html lang='pt-br'><head><title>Teste</title></head><body>");
			msg += "<h2>Create table user successful!</h2>\n";

			service.deleteGalaxiaTable();
			msg += "<h2>Delete table galaxias successful!</h2>\n";

			service.createGalaxiaTable();
			msg += "<h2>Create table galaxias successful!</h2>\n";

			service.populate7GalaxiaTable();
			msg += "<h2>7 Galaxias de exemplo adicionados com sucesso!</h2>\n";

				page.println("<html lang='pt-br'><head><title>Install Database</title></head><body>");
				page.println(msg);
				/*/
				page.println("<code>");
				for (Map.Entry<String,String> pair : env.entrySet()) {
					page.println(pair.getKey());
					page.println(pair.getValue());
				}
				//*/
				page.println("</code>");
				page.println("</body></html>");
				page.close();

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
