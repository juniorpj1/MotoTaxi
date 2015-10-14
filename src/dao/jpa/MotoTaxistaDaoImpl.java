package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.MotoTaxistaDao;
import dominio.MotoTaxista;

public class MotoTaxistaDaoImpl implements MotoTaxistaDao {
 
	private EntityManager em;
	
	public MotoTaxistaDaoImpl(){
		this.em = EM.getLocalEm();
	}
	
	@Override
	public void inserirAtualizar(MotoTaxista x) {
		if(x.getCodMotoTaxista() != null){
			x = em.merge(x);
		}
		em.persist(x);

	}

	@Override
	public void excluir(MotoTaxista x) {
		x = em.merge(x);
		em.remove(x);

	}

	@Override
	public MotoTaxista buscar(int cod) {
		return em.find(MotoTaxista.class, cod);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MotoTaxista> buscarTodos() {
		String jpql = "SELECT x FROM MotoTaxista x";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

}
