package org.zabalburu.gt.modelo;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tareas")
public class Tarea {
	private static int numTareas = 0;
	@EqualsAndHashCode.Include 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "idempleado")
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
