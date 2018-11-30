package fr.agrorole.dnd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.agrorole.dnd.dto.PJ;
import fr.agrorole.dnd.dto.Race;
import fr.agrorole.dnd.interfaces.IRaceDAO;

public class RaceDAOImpl implements IRaceDAO {
	
	private EntityManager entityManager;
	private static Logger logger = LogManager.getLogger(RaceDAOImpl.class);

	@Override
	public List<Race> getListRace() {
		// Debut de transaction
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		try {
		Query query = entityManager.createQuery("select r from RACE r");
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
	public Race getRaceByKw(String kw) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		try {
			Race race = entityManager.find(Race.class, kw);
			return race;
		} catch (Exception e) {
			transaction.rollback();
			 if (logger.isErrorEnabled()) {
	            logger.debug(e);
	        }
		}
		return null;
	}

	@Override
	public void addRace(Race race) {
		// Debut de transaction
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		try {
			entityManager.persist(race);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			 if (logger.isErrorEnabled()) {
	            logger.debug(e);
	        }
		}

	}

	@Override
	public void delRace(String raceName) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		try {
			Query query = entityManager.createQuery("DELETE FROM RACE r WHERE r.label = :l");
			query.setParameter("l", raceName);			
			query.executeUpdate();			
			
		} catch (Exception e) {
			transaction.rollback();
			 if (logger.isErrorEnabled()) {
	            logger.debug(e);
	        }
		}

	}

	@Override
	public void updateRace(Race race) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		try {
			entityManager.merge(race);
		} catch (Exception e) {
			transaction.rollback();
			 if (logger.isErrorEnabled()) {
	            logger.debug(e);
	        }
		}

	}

}
