package fr.agrorole.dnd.metier;

import java.util.List;

import org.apache.commons.lang.NullArgumentException;

import fr.agrorole.dnd.dao.CaracDAOImpl;
import fr.agrorole.dnd.dto.Carac;
import fr.agrorole.dnd.exceptions.CaracFieldsException;

public class CaracMetier {
	
	private CaracDAOImpl caracDAO = new CaracDAOImpl();
	
	public List<Carac> getListCarac() {
		return this.caracDAO.getListCarac();
	}
	
	public Carac getCaracByKw(String kw) throws CaracFieldsException {
		try {
			return caracDAO.getCaracByKw(kw);
		}
		catch(NullArgumentException e) {
			throw new CaracFieldsException("Le label de la caractéristique est manquant", e);
		}
	}

}
