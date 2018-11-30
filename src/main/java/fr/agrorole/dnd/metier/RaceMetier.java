package fr.agrorole.dnd.metier;

import java.util.List;

import org.apache.commons.lang.NullArgumentException;

import fr.agrorole.dnd.dao.RaceDAOImpl;
import fr.agrorole.dnd.dto.Race;
import fr.agrorole.dnd.exceptions.RaceFieldsException;

public class RaceMetier {
	
	private RaceDAOImpl raceDAO = new RaceDAOImpl();
	
	public List<Race> getRaceList() {
		return this.raceDAO.getListRace();
	}
	
	public Race getRace(String kw) throws RaceFieldsException {
		try {
			return raceDAO.getRaceByKw(kw);
		}
		catch(NullArgumentException e) {
			throw new RaceFieldsException("Le label de la race est manquant", e);
		}
	}
	
	public Race updateRace(Race race) throws RaceFieldsException {
		try {
			if(null==race.getLabel()) {
				throw new RaceFieldsException("Le label est manquant");
			}
			raceDAO.updateRace(race);
			return raceDAO.getRaceByKw(race.getLabel());
		} catch (NullArgumentException e) {
			throw new RaceFieldsException("L\'objet Race est manquant", e);
		}		
	}
	
	public Race addRace(Race race) throws RaceFieldsException {
		try {
			raceDAO.addRace(race);
			return raceDAO.getRaceByKw(race.getLabel());
		} catch (NullArgumentException e) {
			throw new RaceFieldsException("L\'objet Race est manquant", e);
		}
	}
	
	public void delRace(String label) throws RaceFieldsException {
		try {
			raceDAO.delRace(label);
		} catch (NullArgumentException e) {
			throw new RaceFieldsException("Le label de la race est manquant", e);
		}
	}

}
