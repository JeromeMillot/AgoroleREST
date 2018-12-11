package fr.agrorole.dnd.outils.serializers;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.agrorole.dnd.dto.Carac;
import fr.agrorole.dnd.dto.Classe;
import fr.agrorole.dnd.dto.Competence;
import fr.agrorole.dnd.dto.Don;
import fr.agrorole.dnd.dto.PJ;
import fr.agrorole.dnd.dto.Race;
import fr.agrorole.dnd.dto.Sauvegarde;
import fr.agrorole.dnd.dto.User;

public class GsonSerializationManager {
	
	private static final String CARAC_NAME = "CARACS";
	private static final String CLASSE_NAME = "CLASSES";
	private static final String COMPETENCE_NAME = "COMPETENCES";
	private static final String DON_NAME = "DONS";
	private static final String RACE_NAME = "RACES";
	private static final String SAUVEGARDE_NAME = "SAUVEGARDES";
			
	private Gson gson = new GsonBuilder().serializeNulls().create();
	
	public GsonSerializationManager() {
		super();
	}

	public String userSerializer(User user) {
		return gson.toJson(user);
	}
	
	public String userListSerializer(List<User> listUsers) {
		return gson.toJson(listUsers);
	}
	
	public User userDeserializer(String json) {		
		return gson.fromJson(json, User.class);
	}
	
	public String pjSerializer(PJ pj) {
		return gson.toJson(pj);
	}
	
	public String pjListSerializer(List<PJ> ListPj) {
		return gson.toJson(ListPj);
	}
	
	public PJ pjDeserializer(String json) {
		return gson.fromJson(json, PJ.class);
	}
	
	public Race raceDeserializer(String json) {
		return gson.fromJson(json, Race.class);
	}
	
	public String raceSerializer(Race race) {
		return gson.toJson(race);
	}
	
	public Carac caracDeserializer(String json) {
		return gson.fromJson(json, Carac.class);
	}
	
	public String caracSerializer(Carac carac) {
		return gson.toJson(carac);
	}
	
	public String serializeRules(List<Carac> carac, List<Classe> classe, List<Competence> competence, List<Don> don, List<Race> race, List<Sauvegarde> sauvegarde) {
		Map<String, List<?>> mapRules = new HashMap<String, List<?>>();
		mapRules.put(CARAC_NAME, carac);
		mapRules.put(CLASSE_NAME, classe);
		mapRules.put(COMPETENCE_NAME, competence);
		mapRules.put(DON_NAME, don);
		mapRules.put(RACE_NAME, race);
		mapRules.put(SAUVEGARDE_NAME, sauvegarde);
		String json = gson.toJson(mapRules);
		System.out.println("Map Rules to json : "+ json);
		return json;		
	}	
	
	public static void main(String[] args) throws IllegalArgumentException, UnsupportedEncodingException {
		GsonSerializationManager gUser = new GsonSerializationManager();
		String json = gUser.userSerializer(new User("Moi", "aaaa", "ici@la.fr", "A", Timestamp.from(Instant.now()), "Jerome", "Millot"));
		System.out.println(json);
		User user = gUser.userDeserializer(json);
		System.out.println(user.toString());
	}

}
