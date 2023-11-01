package org.zabalburu.gt.dao;

import java.util.List;

import org.zabalburu.gt.modelo.Empleado;
import org.zabalburu.gt.modelo.Tarea;

public interface TareasDAO {
	public List<Empleado> getEmpleados();
	public Empleado getEmpleado(Integer id);
	public void nuevaTarea(Tarea t);
	public Tarea getTarea(Integer id);
	Empleado login(String email, String password);
}
