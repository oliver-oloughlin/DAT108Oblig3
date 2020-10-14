package handleliste;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Vare;
import database.VareDAO;

@WebServlet("/HandlelistePost")
public class HandlelistePost extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private VareDAO vareDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Henter slett parameter og vareNavn parameter.
		boolean slett = Boolean.parseBoolean(request.getParameter("slett"));
		String vareNavn = request.getParameter("vareNavn");
		
		// Sjekker om vare skal slettes eller legegs til
		if(slett) {
			// Sjekker om vareNavn != null for sikkerhetskyld, og sletter deretter oppgitt vare fra database.
			if(vareNavn != null) {
				vareDAO.slettVare(vareDAO.getVare(vareNavn));
			}
		}
		else {
			// Sjekker at vare er blitt oppgitt og ikke er tom, og legger s� til varen i databasen.
			if(vareNavn != null && !vareNavn.isBlank()) {
				Vare nyVare = new Vare(vareNavn);
				vareDAO.leggTilVare(nyVare);
			}
		}
		response.sendRedirect("Handleliste");
	}

}