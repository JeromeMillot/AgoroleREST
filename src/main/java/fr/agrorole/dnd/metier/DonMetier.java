package fr.agrorole.dnd.metier;

import java.util.List;

import org.apache.commons.lang.NullArgumentException;

import fr.agrorole.dnd.dao.DonDAOImpl;
import fr.agrorole.dnd.dto.Don;
import fr.agrorole.dnd.exceptions.DonFieldsException;

public class DonMetier {
	
	private DonDAOImpl donDAOImpl = new DonDAOImpl();
	
	public List<Don> getAlldons() {
		return donDAOImpl.getAllDons();
	}
	
	public Don getDonbyKeyWord(String kw) throws DonFieldsException {
		try {
			return donDAOImpl.getDonByKw(kw);
		}
		catch(NullArgumentException e) {
			throw new DonFieldsException("Le label de la race est manquant", e);
		}
	}
	
	public Don updateDon(Don don) throws DonFieldsException {
		try {
			if(null==don.getLabel()) {
				throw new DonFieldsException("Le label est manquant");
			}
			donDAOImpl.updateDon(don);
			return donDAOImpl.getDonByKw(don.getLabel());
		} catch (NullArgumentException e) {
			throw new DonFieldsException("L\'objet Don est manquant", e);
		}		
	}
	
	public Don addDon(Don don) throws DonFieldsException {
		try {
			donDAOImpl.addDon(don);
			return donDAOImpl.getDonByKw(don.getLabel());
		} catch (NullArgumentException e) {
			throw new DonFieldsException("L\'objet Race est manquant", e);
		}
	}
	
	public void delDon(String label) throws DonFieldsException {
		try {
			donDAOImpl.deleteDon(label);
		} catch (NullArgumentException e) {
			throw new DonFieldsException("Le label de la race est manquant", e);
		}
	}

}
