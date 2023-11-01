package org.zabalburu.gt.modelo;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Empleado {
	@EqualsAndHashCode.Include private Integer id;
	private String apellidos;
	private String nombre;
	private String email;
	private String password;
	private List<Tarea> tareas;
	
	@Override
	public String toString() {
		return this.getNombre() + " " + this.getApellidos();
	}
}
