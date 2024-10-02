package cocoro.lib.loader;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import cocoro.lib.tdloader.LStringMap;
import cocoro.lib.uiauto.ElementMap;


public class LocalizeStringsLoader{

	private ElementMap localizeStrings = new ElementMap();
	private LStringMap stringMap = new LStringMap();
	private static Set<String> emptySet = new HashSet<String>();
	//private static Set<String> sectionMetaSet = new HashSet<String>(Arrays.asList(new String[]{"root"}));
	//private Map<String, ElementMap> sectionMap = new HashMap<String, ElementMap>();
	//private Map<String, String> sectionRootMap = new HashMap<String, String>();
	private String primaryLang ;

	private void populateStringForElement( String elemName, JsonObject idJson){
		HashMap<String,String> idMap = new HashMap<String,String>();
		for(Map.Entry<String,JsonElement> entry : idJson.entrySet()){
			if (this.primaryLang.contentEquals(entry.getKey().toLowerCase())){
				stringMap.addLString(elemName, entry.getValue().getAsString());
				/*
				 * System.out.println("Key : " +entry.getKey().toLowerCase());
				 * System.out.println("Value : " +entry.getValue().getAsString());
				 */
			}
			//idMap.put(entry.getKey(), entry.getValue().getAsString());
		}
		
		//map.add(elemName, idMap);
		//localizeStrings.add(elemName, idMap);
	}
	
	private void loadAllElements(JsonObject json){
		//ElementMap map = new ElementMap();		 
		for(Map.Entry<String,JsonElement> entry : json.entrySet()){
			String key = entry.getKey();
			/* System.out.println("Key value : "+key); */
					populateStringForElement(key,entry.getValue().getAsJsonObject());
		}
	}
	

	public LStringMap load(String filePath, String language) throws Exception {
		JsonReader reader = new JsonReader(new FileReader(filePath+ File.separator + "localization.json"));
		this.primaryLang = language.toLowerCase();
		JsonObject jsonObject = (new JsonParser()).parse(reader).getAsJsonObject();
		JsonObject elements = jsonObject.getAsJsonObject("stringlocalize");
		
		if (elements != null){
			loadAllElements(elements);
		}
		
		return stringMap;
	}



}
