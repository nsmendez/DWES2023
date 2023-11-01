package org.zabalburu.gt.servicio;

import java.util.List;

import org.zabalburu.gt.dao.TareasDAO;
import org.zabalburu.gt.dao.TareasList;
import org.zabalburu.gt.dao.TareasSQLServer;
import org.zabalburu.gt.modelo.Empleado;
import org.zabalburu.gt.modelo.Tarea;

public class TareasServicio implements TareasDAO{
	private TareasDAO dao = TareasSQLServer.getInstance();
	
	private static TareasServicio tareasServicio;
	
	public static TareasServicio getInstance() {
		if (tareasServicio == null) {
			tareasServicio = new TareasServicio();
		}
		return tareasServicio;
	}
	
	private TareasServicio() {}
	
	@Override
	public List<Empleado> getEmpleados() {
		return dao.getEmpleados();
	}
	
	@Override
	public Empleado getEmpleado(Integer id) {
		return dao.getEmpleado(id);
	}
	
	@Override
	public void nuevaTarea(Tarea t) {
		dao.nuevaTarea(t);
	}

	@Override
	public Tarea getTarea(Integer id) {
		return dao.getTarea(id);
	}
	
	@Override
	public Empleado login(String email, String password) {
		return dao.login(email, password);
	}
}
