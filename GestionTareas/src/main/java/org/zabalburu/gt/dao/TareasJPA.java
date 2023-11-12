package org.zabalburu.gt.dao;

import java.util.List;

import org.zabalburu.gt.modelo.Empleado;
import org.zabalburu.gt.modelo.Tarea;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class TareasJPA implements TareasDAO {
	private static TareasJPA jpa;
	
	private EntityManager em = Persistence.createEntityManagerFactory("GT").createEntityManager();
	
	public static TareasJPA getInstance() {
		if (jpa == null) {
			jpa = new TareasJPA();
		}
		return jpa;
	}
	
	private TareasJPA() {
		
	}
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("GT");
	
	@Override
	public List<Empleado> getEmpleados() {
		Query q = em.createQuery("Select e From Empleado e");
		return q.getResultList();
	}

	@Override
	public Empleado getEmpleado(Integer id) {
		TypedQuery<Empleado> q = em.createQuery("Select e From Empleado e Where e.id = :id", Empleado.class);
		q.setParameter("id", id);
		Empleado e = null;
		try {
			e = q.getSingleResult();
		} catch (NoResultException ex) {
			
		}
		return e;
	}

	@Override
	public void nuevaTarea(Tarea t) {
		em.persist(t);

	}

	@Override
	public Tarea getTarea(Integer id) {
		TypedQuery<Tarea> q = em.createQuery("Select t From Tarea t Where t.id = :id", Tarea.class);
		q.setParameter("id", id);
		Tarea t = null;
		try {
			t = q.getSingleResult();
		} catch (NoResultException ex) {
			
		}
		return t;
	}

	@Override
	public Empleado login(String email, String password) {
		TypedQuery<Empleado> q = em.createQuery("Select e From Empleado e Where upper(e.email) = upper(:email) and e.password=:password",
								Empleado.class);
		q.setParameter("email", email);
		q.setParameter("password", password);
		Empleado e = null;
		try {
			e = q.getSingleResult();
		} catch (NoResultException ex) {
			
		}
		return e;
	}

}
