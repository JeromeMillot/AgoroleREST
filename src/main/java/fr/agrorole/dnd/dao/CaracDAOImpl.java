package fr.agrorole.dnd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.agrorole.dnd.dto.Carac;
import fr.agrorole.dnd.dto.Race;
import fr.agrorole.dnd.interfaces.ICaracDAO;

public class CaracDAOImpl implements ICaracDAO {
	
	private EntityManager entityManager;
	private static Logger logger = LogManager.getLogger(CaracDAOImpl.class);

	@Override
	public List<Carac> getListCarac() {
		// Debut de transaction
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		try {
			Query query = entityManager.createQuery("select c from CARACS c");
			return query.getResultList();
		} catch (Exception e) {
			transaction.rollback();
			if (logger.isErrorEnabled()) {
				logger.debug(e);
			}
		}
		return null;
	}

	@Override
	public Carac getCaracByKw(String kw) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		try {
			Carac carac = entityManager.find(Carac.class, kw);
			return carac;
		} catch (Exception e) {
			transaction.rollback();
			 if (logger.isErrorEnabled()) {
	            logger.debug(e);
	        }
		}
		return null;
	}

}
