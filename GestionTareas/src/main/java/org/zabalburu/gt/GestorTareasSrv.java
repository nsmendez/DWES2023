package org.zabalburu.gt;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.List;

import org.zabalburu.gt.modelo.Empleado;
import org.zabalburu.gt.modelo.Tarea;
import org.zabalburu.gt.servicio.TareasServicio;

/**
 * Servlet implementation class GestorTareasSrv
 */
@WebServlet(name = "Gestor Tareas", value = "/GT")
public class GestorTareasSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DateFormat df = DateFormat.getDateInstance();
    private TareasServicio servicio;
    /**
     * @see HttpServlet#HttpServlet()
     */
    
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		servicio = TareasServicio.getInstance();
	}
	
    public GestorTareasSrv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		mostrarFormulario(response, null);
	}
	
	private void mostrarFormulario(HttpServletResponse response, String error) throws IOException {
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		out.println("<!doctype html>");
		out.println("<html lang=\"en\">");
		out.println("<head>");
		out.println("  <meta charset='utf-8'>");
		out.println("  <title>Gestor Tareas</title>");
		out.println(
				"  <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\" integrity=\"sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk\" crossorigin=\"anonymous\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class='container m-5'>");
		out.println("  <form method='post' action='GT'>\n" + "  <div class='form-group'>\r\n"
				+ "    <label for='email'>Usuario</label>\r\n"
				+ "    <input type='email' class='form-control' id='email' name='usuario' required>\r\n"
				+ "  </div>\r\n" + "  <div class='form-group'>\r\n" + "    <label for='password'>Password</label>\r\n"
				+ "    <input type='password' class='form-control' id='password' name='password' required>\r\n"
				+ "  </div>\r\n" + "  <button type='submit' class='btn btn-primary'>Enviar</button>\r\n" + "</form>  ");
		if (error != null) {
			out.println("<div class='alert alert-danger mt-3'>" + error + "</div>");
		}
		out.println("</div>");
		out.println(
				"<script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\" integrity=\"sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj\" crossorigin=\"anonymous\"></script>\r\n"
						+ "<script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\" integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\" crossorigin=\"anonymous\"></script>\r\n"
						+ "<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js\" integrity=\"sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI\" crossorigin=\"anonymous\"></script>");
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		String error = null;
		Empleado empleado = null;
		if (usuario.isEmpty() || password.isEmpty()) {
			error = "Faltan datos obligatorios";
		} else {
			empleado = servicio.login(usuario, password);
			if (empleado == null) {
				error = "Usuario / password erróneos";
			}
		}
		if (error != null) {
			mostrarFormulario(response, error);
		} else {
			PrintWriter out = response.getWriter();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			out.println("<!doctype html>");
			out.println("<html lang=\"en\">"); 
			out.println("<head>"); 
			out.println("  <meta charset='utf-8'>"); 
			out.println("  <title>Gestor Tareas</title>");
			out.println("  <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\" integrity=\"sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk\" crossorigin=\"anonymous\">");
			out.println("</head>");
			out.println("<body>"); 
			out.println("<div class='container m-5'>");
			out.println("  <div class='row'>");	
			out.println("    <h2>Bienvenido " + empleado.toString() + "</h2>");
			out.println("  </div>");
			out.println("  <div class='row'>");
			List<Tarea> tareas = empleado.getTareas();
			if (tareas.isEmpty()) {
				out.println("  <div class='alert alert-info'>No hay tareas asignadas</div>");
			} else {
								out.println("<table class=\"table\">\r\n" +                                                         
						"  <thead>\r\n" + 
						"    <tr>\r\n" + 
						"      <th scope=\"col\">#</th>\r\n" + 
						"      <th scope=\"col\">Descripción</th>\r\n" + 
						"      <th scope=\"col\">Fecha Inicio</th>\r\n" + 
						"      <th scope=\"col\">Fecha Fin</th>\r\n" + 
						"    </tr>\r\n" + 
						"  </thead>\r\n" + 
						"  <tbody>\r\n");
				for(Tarea t : tareas) {
					out.println("    <tr>\r\n" + 
						"      <th scope=\"row\">" + t.getId() + "</th>\r\n" + 
						"      <td>" + t.getDescripcion() + "</td>\r\n" + 
						"      <td>" + df.format(t.getFechaInicio()) + "</td>\r\n" + 
						"      <td>" + df.format(t.getFechaFin()) + "</td>\r\n" + 
						"    </tr>");
				}
				out.println("</tbody>");
				out.println("</table>");
			}
			out.println("<a href='GT' class='btn btn-primary'>Salir</a>");
			out.println("  </div>");
		}
	}

}
