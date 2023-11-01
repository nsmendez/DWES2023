package org.zabalburu.gt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.zabalburu.gt.modelo.Empleado;
import org.zabalburu.gt.modelo.Tarea;
import org.zabalburu.gt.util.ConexionSQLServer;

public final class TareasSQLServer implements TareasDAO {
	private static TareasSQLServer dao = null;
	private Connection cnn = ConexionSQLServer.getConexion();

	public static TareasSQLServer getInstance() {
		if (dao == null) {
			dao = new TareasSQLServer();
		}
		return dao;
	}
	
	@Override
	public List<Empleado> getEmpleados() {
		// Cuando recuperamos todos los empleados no les añadimos sus tareas
		List<Empleado> empleados = new ArrayList<>();
		try {
			ResultSet rst = cnn.createStatement().executeQuery("Select * From EmpleadosT");
			while (rst.next()) {
				empleados.add(crearEmpleado(rst));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return empleados;
	}

	@Override
	public Empleado getEmpleado(Integer id) {
		// En este método añadimos las tareas al empleado
		Empleado e = null;
		try {
			PreparedStatement pst = cnn.prepareStatement("Select * From EmpleadosT Where id=?");
			pst.setInt(1, id);
			ResultSet rst = pst.executeQuery();
			if (rst.next()) {
				e = crearEmpleado(rst);
				pst = cnn.prepareStatement("Select * From Tareas Where idEmpleado=?");
				pst.setInt(1, id);
				rst = pst.executeQuery();
				while (rst.next()) {
					e.getTareas().add(crearTarea(rst, e));
				}
				rst.close();
				pst.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return e;
	}

	@Override
	public void nuevaTarea(Tarea t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Tarea getTarea(Integer id) {
		Tarea t = null;
		try {
			PreparedStatement pst = cnn.prepareStatement("Select * From Tareas Where id=?");
			pst.setInt(1, id);
			ResultSet rst = pst.executeQuery();
			if (rst.next()) {
				t = crearTarea(rst, null);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return t;
	}
	
	public Empleado crearEmpleado(ResultSet rst) {
		Empleado e = new Empleado();
		try {
			e.setId(rst.getInt("id"));
			e.setNombre(rst.getString("nombre"));
			e.setApellidos(rst.getString("apellidos"));
			e.setEmail(rst.getString("email"));
			e.setPassword(rst.getString("password"));
			e.setTareas(new ArrayList<>());
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return e;
	}

	public Tarea crearTarea(ResultSet rst, Empleado e) {
		Tarea t = new Tarea();
		try {
			t.setId(rst.getInt("id"));
			t.setDescripcion(rst.getString("descripcion"));
			t.setFechaInicio(rst.getDate("fechaInicio"));
			t.setFechaFin(rst.getDate("fechaFin"));
			if (e == null) {
				PreparedStatement pst = cnn.prepareStatement("Select * From EmpleadosT Where id=?");
				pst.setInt(1, rst.getInt("idEmpleado"));
				ResultSet rstw = pst.executeQuery();
				if (rst.next()) {
					e = crearEmpleado(rst);
				}
				rst.close();
				pst.close();
			}
			t.setEmpleado(e);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return t;
	}
	
	public static void main(String[] args) {
		TareasSQLServer s = new TareasSQLServer();
		System.out.println(s.getEmpleado(1).getTareas());
	}

	@Override
	public Empleado login(String email, String password) {
		// En este método añadimos las tareas al empleado
		Empleado e = null;
		try {
			PreparedStatement pst = cnn
					.prepareStatement("Select * From EmpleadosT Where upper(email)=upper(?) and password=?");
			pst.setString(1, email);
			pst.setString(2, password);
			ResultSet rst = pst.executeQuery();
			if (rst.next()) {
				e = crearEmpleado(rst);
				pst = cnn.prepareStatement("Select * From Tareas Where idEmpleado=?");
				pst.setInt(1, e.getId());
				rst = pst.executeQuery();
				while (rst.next()) {
					e.getTareas().add(crearTarea(rst, e));
				}
				rst.close();
				pst.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return e;
	}

}
