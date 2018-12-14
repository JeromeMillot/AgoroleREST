package fr.agrorole.dnd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.agrorole.dnd.dto.PJ;
import fr.agrorole.dnd.interfaces.ICharacterDAO;

public class CharacterDAOImpl implements ICharacterDAO {
	
	private EntityManager entityManager;
	private static Logger logger = LogManager.getLogger(CharacterDAOImpl.class);

	@Override
	public void addCharacter(PJ pj) {
		// Debut de transaction
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		try {
			entityManager.persist(pj);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			 if (logger.isErrorEnabled()) {
	            logger.debug(e);
	        }
		}

	}

	@Override
	public PJ getCharacter(String name, String userId) {
		// Debut de transaction
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		try {
			PJ pj = entityManager.find(PJ.class, name+userId);
			return pj;
		} catch (Exception e) {
			transaction.rollback();
			 if (logger.isErrorEnabled()) {
	            logger.debug(e);
	        }
		}
		return null;
	}

	@Override
	public List<PJ> getAllCharacterList() {
		// Debut de transaction
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		try {
		Query query = entityManager.createQuery("select p from PJ p");
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
	public List<PJ> getCharacterListByKW(String keyWord) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		try {
			Query query = entityManager.createQuery("select p from PJ p where p.name like :x");
			query.setParameter("x", "%"+keyWord+"%");
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
	public List<PJ> getCharacterListByUserName(String userName) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		try {
			Query query = entityManager.createQuery("select p from PJ p where p.joueur = :j");
			query.setParameter("j", userName);
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
	public void updateCharacter(PJ pj) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		try {
			entityManager.merge(pj);
		} catch (Exception e) {
			transaction.rollback();
			 if (logger.isErrorEnabled()) {
	            logger.debug(e);
	        }
		}
	}

	@Override
	public void deleteCharacter(String charName, String userId) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		try {
			Query query = entityManager.createQuery("DELETE FROM PJ p WHERE p.name = :n AND p.user = :u");
			query.setParameter("n", charName);
			query.setParameter("u", userId);
			query.executeUpdate();			
			
		} catch (Exception e) {
			transaction.rollback();
			 if (logger.isErrorEnabled()) {
	            logger.debug(e);
	        }
		}

	}

}
