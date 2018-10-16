package fr.agrorole.dnd.metier;

import java.util.List;

import fr.agrorole.dnd.dao.CharacterDAOImpl;
import fr.agrorole.dnd.dto.PJ;
import fr.agrorole.dnd.exceptions.CharFieldsException;

public class CharacterMetier {
	
	private UserMetier userMetier = new UserMetier();
	private CharacterDAOImpl characterDAOImpl = new CharacterDAOImpl();
	
	public PJ createNewCharacter(PJ pj) throws CharFieldsException {
		if (null!=pj) {
			if(null==pj.getNom()) {
				throw new CharFieldsException("Le nom du personnage est obligatoire.");
			} else if (pj.getNom().trim().matches("[\\w\\s]{3,30}")) {
				throw new CharFieldsException("Le nom du personnage doit faire entre 3 et 30 caractères.");
			}
			
			if(null==pj.getUser()) {
				throw new CharFieldsException("Le nom de l'utilisateur est obligatoire.");
			} else if(null==userMetier.getUserFromId(pj.getUser())) {
				throw new CharFieldsException("L'utilisateur n\'existe pas en base.");
			}
			characterDAOImpl.addCharacter(pj);
			return characterDAOImpl.getCharacter(pj.getNom(), pj.getUser());
			
		} else {
			throw new CharFieldsException("Pas de personnage joueur intégré à la requète.");
		}
	}
	
	public PJ getCharacter(String name, String userId) throws CharFieldsException {
		if(null==name) {
			throw new CharFieldsException("Le nom du personnage est obligatoire pour la recherche.");
		}
		if(null==userId) {
			throw new CharFieldsException("Le nom de l\'utilisateur est obligatoire pour la recherche.");
		}
		return characterDAOImpl.getCharacter(name, userId);
	}
	
	public List<PJ> getAllCharacters() {
		return characterDAOImpl.getAllCharacterList();
	}
	
	public List<PJ> getAllCharactersByUserId(String userId) throws CharFieldsException {
		if(null==userId) {
			throw new CharFieldsException("Le nom de l\'utilisateur est obligatoire pour la recherche.");
		}
		return characterDAOImpl.getCharacterListByUserName(userId);
	}
	
	public List<PJ> getCharacterListFromKW(String kw) throws CharFieldsException {
		if(null==kw) {
			throw new CharFieldsException("Le mot clé est obligatoire pour la recherche.");
		}
		return characterDAOImpl.getCharacterListByKW(kw);
	}
	
	public void delCharacter(String name, String userId) throws CharFieldsException {
		if(null==name) {
			throw new CharFieldsException("Le nom du personnage est obligatoire pour la suppression.");
		}
		if(null==userId) {
			throw new CharFieldsException("Le nom de l\'utilisateur est obligatoire pour la suppression.");
		}
		characterDAOImpl.deleteCharacter(name, userId);
	}
	
	public PJ updateChar(PJ pj) throws CharFieldsException {
		if (null==pj) {
			throw new CharFieldsException("Personnage joueur absent de la requète de mise à jour.");
		}
		if(null==pj.getNom()) {
			throw new CharFieldsException("Le nom du personnage est obligatoire.");
		} else if (pj.getNom().trim().matches("[\\w\\s]{3,30}")) {
			throw new CharFieldsException("Le nom du personnage doit faire entre 3 et 30 caractères.");
		}
		
		if(null==pj.getUser()) {
			throw new CharFieldsException("Le nom de l'utilisateur est obligatoire.");
		} else if(null==userMetier.getUserFromId(pj.getUser())) {
			throw new CharFieldsException("L'utilisateur n\'existe pas en base.");
		}
		characterDAOImpl.updateCharacter(pj);
		return characterDAOImpl.getCharacter(pj.getNom(), pj.getUser());
	}
}
