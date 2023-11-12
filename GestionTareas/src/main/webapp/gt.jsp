<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="org.zabalburu.gt.servicio.TareasServicio, java.text.DateFormat, org.zabalburu.gt.modelo.*, java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="cabeceraBootstrap.jsp">
<jsp:param value="Gestor de tareas" name="titulo"/>
</jsp:include>
<jsp:useBean id="empleado" class="org.zabalburu.gt.modelo.Empleado"></jsp:useBean>
<jsp:setProperty property="*" name="empleado"/>
<jsp:setProperty property="email" param="usuario" name="empleado"/>
</head>
<%!
	private TareasServicio servicio = TareasServicio.getInstance();
	private DateFormat df = DateFormat.getDateInstance();
%>
<body>
    <div class='container m-5'>
	<%
	  String error = null;
	  //Empleado empleado = null;
	  if (request.getMethod().equals("POST")){
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		if (usuario.isEmpty() || password.isEmpty()) {
			error = "Faltan datos obligatorios";
		} else {
			empleado = servicio.login(usuario, password);
			if (empleado == null) {
				error = "Usuario / password erróneos";
			}
		}
	   }
	
		if (request.getMethod().equals("GET") || error != null){
	%>
	
		<form method='post' action='gt.jsp'>
			<div class="form-group">
				<label for="exampleInputEmail1">Usuario</label> <input type="email"
					class="form-control" id="exampleInputEmail1" name='usuario'
					required>
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">Password</label> <input
					type="password" class="form-control" id="exampleInputPassword1"
					name='password' required>
			</div>
			<button type="submit" class="btn btn-primary">Enviar</button>
		</form>
	<%
		if (error!=null){
	%>
	<div class='alert alert-danger mt-3'><%= error %></div>
	<%
		}
	  }
	  if (error == null && request.getMethod().equals("POST")){
	%>
	<div class='row'>
		<h2>Bienvenido <%= empleado.toString()%> </h2>
		</div>
		  <div class='row'>
		  <% 
		  	List<Tarea> tareas = empleado.getTareas();
		  	            if (tareas.isEmpty()) {                                                                               
		  %>
		 
			<div class='alert alert-info'>No hay tareas asignadas</div>
		  
		  <% 
			} else {
		  %>
			<table class="table">
				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">Descripción</th>
						<th scope="col">Fecha Inicio</th>
						<th scope=\"col\">Fecha Fin</th>
					</tr>
				</thead>
				<tbody>
			<% 
				for(Tarea t : tareas) {
			%>
					<tr>
						<th scope="row"><%= t.getId() %></th>
						<td><%= t.getDescripcion() %></td>
						<td><%= df.format(t.getFechaInicio()) %></td>
						<td><%= df.format(t.getFechaFin()) %></td>
					</tr>
			<% 
				}
			%>
				</tbody>
			</table>
		<% 
			}
		%>
			<a href='GT.jsp' class='btn btn-primary'>Salir</a>
		</div>
    <%
	  }
	%>
	</div>
</body>
</html>