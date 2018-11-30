package fr.agrorole.dnd.interfaces;

import java.util.List;

import fr.agrorole.dnd.dto.Race;

public interface IRaceDAO {
	
	public List<Race> getListRace();
	public Race getRaceByKw(String kw);
	public void addRace(Race race);
	public void delRace(String raceName);
	public void updateRace(Race race);
}
