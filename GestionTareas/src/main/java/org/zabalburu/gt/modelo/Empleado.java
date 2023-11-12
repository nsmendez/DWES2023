package org.zabalburu.gt.modelo;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "EmpleadosT")
public class Empleado {
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String apellidos;
	private String nombre;
	private String email;
	private String password;
	@OneToMany(mappedBy = "empleado")
	private List<Tarea> tareas;
	
	@Override
	public String toString() {
		return this.getNombre() + " " + this.getApellidos();
	}
}
