package fr.agrorole.dnd.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.agrorole.dnd.dto.User;
import fr.agrorole.dnd.interfaces.IUserDAO;

public class UserDAOImpl implements IUserDAO {
	
	private EntityManager entityManager;
	private static Logger logger = LogManager.getLogger(UserDAOImpl.class);
	
	public UserDAOImpl() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("DB_DND");		
		entityManager = entityManagerFactory.createEntityManager();
	}

	@Override
	public void addUser(User user) {
		// Debut de transaction
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		try {
			entityManager.persist(user);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			 if (logger.isErrorEnabled()) {
	            logger.debug(e);
	        }
		}		
	}

	@Override
	public List<User> listUsers() {
		// Debut de transaction
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		try {
		Query query = entityManager.createQuery("select u from User u");
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
	public User getUser(String userName) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		try {
			User u = entityManager.find(User.class, userName);
			return u;
		} catch (Exception e) {
			transaction.rollback();
			 if (logger.isErrorEnabled()) {
	            logger.debug(e);
	        }
		}
		return null;
	}

	@Override
	public void updateUser(User user) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		try {
			entityManager.merge(user);
		} catch (Exception e) {
			transaction.rollback();
			 if (logger.isErrorEnabled()) {
	            logger.debug(e);
	        }
		}
	}

	@Override
	public void deleteUser(String userName) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		try {
			User u = entityManager.find(User.class, userName);
			entityManager.merge(u);		
		} catch (Exception e) {
			transaction.rollback();
			 if (logger.isErrorEnabled()) {
	            logger.debug(e);
	        }
		}
		
	}

	@Override
	public List<User> listUsersMC(String mc) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		try {
			Query query = entityManager.createQuery("select u from User u where p.userName like :x");
			query.setParameter("x", "%"+mc+"%");
			return query.getResultList();	
		} catch (Exception e) {
			transaction.rollback();
			 if (logger.isErrorEnabled()) {
	            logger.debug(e);
	        }
		}
		return null;
	}
		
	
}
