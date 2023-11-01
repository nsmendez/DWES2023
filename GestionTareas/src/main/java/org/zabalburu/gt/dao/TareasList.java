package org.zabalburu.gt.dao;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.zabalburu.gt.modelo.Empleado;
import org.zabalburu.gt.modelo.Tarea;

public final class TareasList implements TareasDAO {
	private static TareasList tareasList = null;
	private List<Empleado> empleados = new ArrayList<>();
	private List<Tarea> tareas = new ArrayList<>();
	
	public static TareasList getInstance() {
		if (tareasList == null) {
			tareasList = new TareasList();
		}
		return tareasList;
	}
	
	private TareasList() {
		empleados.add(new Empleado(1,"López Marín","Juan","juan@empresa.com","12345",new ArrayList<>()));
		empleados.add(new Empleado(2,"Cáceres Royo","Ana","ana@empresa.com","12345",new ArrayList<>()));
		empleados.add(new Empleado(3,"Arias Brito","Miren","miren@empresa.com","12345",new ArrayList<>()));
		empleados.add(new Empleado(4,"Lorente Font","Carlos","carlos@empresa.com","12345",new ArrayList<>()));
		empleados.add(new Empleado(5,"Gallardo Romero","Eva","eva@empresa.com","12345",new ArrayList<>()));
		nuevaTarea(new Tarea(empleados.get(0),"Análisis del Proyecto de ENVASA", new GregorianCalendar(2023,9,5).getTime(),new GregorianCalendar(2023,10,5).getTime()));
		nuevaTarea(new Tarea(empleados.get(2),"Requerimientos de IBEX", new GregorianCalendar(2023,9,15).getTime(),new GregorianCalendar(2023,9,21).getTime()));
		nuevaTarea(new Tarea(empleados.get(0),"Desarrollo Aplicación ENVASA", new GregorianCalendar(2023,10,5).getTime(),new GregorianCalendar(2023,11,25).getTime()));
		nuevaTarea(new Tarea(empleados.get(1),"Desarrollo Aplicación ENVASA", new GregorianCalendar(2023,10,5).getTime(),new GregorianCalendar(2023,11,25).getTime()));
		nuevaTarea(new Tarea(empleados.get(2),"Análisis Aplicación IBEX", new GregorianCalendar(2023,9,21).getTime(),new GregorianCalendar(2023,10,11).getTime()));
		nuevaTarea(new Tarea(empleados.get(0),"Testeo Aplicación ENVASA", new GregorianCalendar(2023,10,23).getTime(),new GregorianCalendar(2023,10,28).getTime()));
	}
	
	@Override
	public List<Empleado> getEmpleados() {
		return empleados;
	}

	@Override
	public Empleado getEmpleado(Integer id) {
		return empleados.stream()
						.filter(e -> e.getId().equals(id))
						.findAny()
						.orElse(null);
	}

	@Override
	public void nuevaTarea(Tarea t) {
		tareas.add(t);
		t.getEmpleado().getTareas().add(t);

	}

	@Override
	public Tarea getTarea(Integer id) {
		return tareas.stream()
					  .filter(t -> t.getId().equals(id))
					  .findAny()
					  .orElse(null);
	}

	@Override
	public Empleado login(String email, String password) {
		return getEmpleados().stream()
							 .filter(e -> e.getEmail().equalsIgnoreCase(email) && e.getPassword().equals(password))
							 .findAny()
							 .orElse(null);
	}

}
