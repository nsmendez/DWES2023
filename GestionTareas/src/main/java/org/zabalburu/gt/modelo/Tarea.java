package org.zabalburu.gt.modelo;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Tarea {
	private static int numTareas = 0;
	@EqualsAndHashCode.Include private Integer id;
	private Empleado empleado;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaFin;
	
	public Tarea(Empleado empleado, String descripcion, Date fechaInicio, Date fechaFin) {
		super();
		this.id = ++numTareas;
		this.empleado = empleado;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
}
