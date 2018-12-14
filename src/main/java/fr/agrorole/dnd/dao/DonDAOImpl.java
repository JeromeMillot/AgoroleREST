package fr.agrorole.dnd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.agrorole.dnd.dto.Don;
import fr.agrorole.dnd.interfaces.IDonDAO;

public class DonDAOImpl implements IDonDAO {
	
	private EntityManager entityManager;
	private static Logger logger = LogManager.getLogger(DonDAOImpl.class);

	@Override
	public List<Don> getAllDons() {
		// Debut de transaction
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		try {
			Query query = entityManager.createQuery("select d from DON d");
			return query.getResultList();
		} catch (Exception e) {
			transaction.rollback();
			if (logger.isErrorEnabled()) {
				logger.error(e);
			}
		}
		return null;
	}

	@Override
	public Don getDonByKw(String kw) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		try {
			Don don = entityManager.find(Don.class, kw);
			return don;
		} catch (Exception e) {
			transaction.rollback();
			 if (logger.isErrorEnabled()) {
	            logger.error(e);
	        }
		}
		return null;
	}

	@Override
	public void addDon(Don don) {
		// Debut de transaction
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		try {
			entityManager.persist(don);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			if (logger.isErrorEnabled()) {
				logger.error(e);
			}
		}

	}

	@Override
	public void updateDon(Don don) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		try {
			entityManager.merge(don);
		} catch (Exception e) {
			transaction.rollback();
			 if (logger.isErrorEnabled()) {
	            logger.error(e);
	        }
		}
	}

	@Override
	public void deleteDon(String label) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		try {
			Query query = entityManager.createQuery("DELETE FROM DON d WHERE d.label = :l");
			query.setParameter("l", label);			
			query.executeUpdate();
			
		} catch (Exception e) {
			transaction.rollback();
			 if (logger.isErrorEnabled()) {
	            logger.error(e);
	        }
		}

	}

}
