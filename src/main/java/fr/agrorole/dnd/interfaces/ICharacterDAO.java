package fr.agrorole.dnd.interfaces;

import java.util.List;

import fr.agrorole.dnd.dto.PJ;

public interface ICharacterDAO {

	public void addCharacter(PJ pj);
	public PJ getCharacter(String name, String userId);
	public List<PJ> getAllCharacterList();
	public List<PJ> getCharacterListByKW(String keyWord);
	public List<PJ> getCharacterListByUserName(String userName);
	public void updateCharacter(PJ pj);
	public void deleteCharacter(String charName, String userId);
}
