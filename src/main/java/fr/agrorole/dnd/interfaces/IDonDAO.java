package fr.agrorole.dnd.interfaces;

import java.util.List;

import fr.agrorole.dnd.dto.Don;

public interface IDonDAO {
	public List<Don> getAllDons();
	public Don getDonByKw(String kw);
	public void addDon(Don don);
	public void updateDon(Don don);
	public void deleteDon(String label);
}
