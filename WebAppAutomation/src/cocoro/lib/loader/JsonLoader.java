package cocoro.lib.loader;

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

import cocoro.lib.uiauto.ElementMap;

public class JsonLoader implements Loader {

	private ElementMap mainPageElems = new ElementMap();
	private static Set<String> emptySet = new HashSet<String>();
	private static Set<String> sectionMetaSet = new HashSet<String>(Arrays.asList(new String[] { "root" }));
	private Map<String, ElementMap> sectionMap = new HashMap<String, ElementMap>();
	private Map<String, String> sectionRootMap = new HashMap<String, String>();

	private void populateIdentifiersForElement(ElementMap map, String elemName, JsonObject idJson) {
		HashMap<String, String> idMap = new HashMap<String, String>();
		for (Map.Entry<String, JsonElement> entry : idJson.entrySet()) {
			idMap.put(entry.getKey().toLowerCase(), entry.getValue().getAsString());
		}

		map.add(elemName, idMap);
		mainPageElems.add(elemName, idMap);
	}
	
	private void populateIdentifiers(ElementMap map, String elemName, JsonObject idJson) {
		HashMap<String, String> idMap = new HashMap<String, String>();
		for (Map.Entry<String, JsonElement> entry : idJson.entrySet()) {
			idMap.put(entry.getKey().toLowerCase(), entry.getValue().getAsString());
		}

		map.add(elemName, idMap);
		mainPageElems.add(elemName, idMap);
	}

	private ElementMap loadAllElements(JsonObject json, Set<String> filtered, String browsername) {
		ElementMap map = new ElementMap();
		for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
			String key = entry.getKey();
			//System.out.println(" key  :  "+key);
			if (!filtered.contains(key.toLowerCase())) {
				JsonObject locators =  entry.getValue().getAsJsonObject();
				for (Map.Entry<String, JsonElement> individual : locators.entrySet()) {
				//if (entry.getValue().getAsJsonObject().entrySet().)
					if(individual.getKey().equalsIgnoreCase(browsername)) {
						
				//	System.out.println( individual.getValue());
					
					// JsonObject idJson = individual.getValue()[1];
					// System.out.println( idJson.get());
					//System.out.println( idJson.getValue());
						/*
						 * for (Map.Entry<String, JsonElement> entry1 :
						 * individual.getValue().getAsJsonObject().entrySet()) {
						 * System.out.println(entry1.getKey().toLowerCase()); System.out.println(
						 * entry1.getValue().getAsString()); }
						 */
					populateIdentifiersForElement(map, key, individual.getValue().getAsJsonObject());
					}
				}
			}
		}
		return map;
	}

	private void populateSection(String section, JsonObject json, String browsername) {
		ElementMap map = loadAllElements(json, sectionMetaSet, browsername);
		sectionRootMap.put(section.toLowerCase(), json.get("Root").getAsString());
		sectionMap.put(section, map);
	}

	private void loadSections(JsonObject json, String browsername) {
		for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
			String key = entry.getKey();
			populateSection(key.toLowerCase(), entry.getValue().getAsJsonObject(), browsername);
		}
	}

	@Override
	public void load(String filePath, String browsername) throws Exception {
		JsonReader reader = new JsonReader(new FileReader(filePath));
		JsonObject jsonObject = (new JsonParser()).parse(reader).getAsJsonObject();
		JsonObject elements = jsonObject.getAsJsonObject("elements");
		JsonObject sections = jsonObject.getAsJsonObject("sections");
		if (elements != null) {
			loadAllElements(elements, emptySet, browsername);
		}

		if (sections != null) {
			loadSections(sections, browsername);
		}
	}

	public ElementMap getElemMap() {
		return this.mainPageElems;
	}

	public ElementMap getSectionElemMap(String Name) {
		return this.sectionMap.get(Name.toLowerCase());
	}

	public String getSectionRootName(String section) {
		return this.sectionRootMap.get(section.toLowerCase());
	}

	@Override
	public void load(String filePath) throws Exception {
		JsonReader reader = new JsonReader(new FileReader(filePath));
		JsonObject jsonObject = (new JsonParser()).parse(reader).getAsJsonObject();
		JsonObject elements = jsonObject.getAsJsonObject("elements");
		JsonObject sections = jsonObject.getAsJsonObject("sections");
		if (elements != null) {
			//loadAllElements(elements, emptySet);
		}

		if (sections != null) {
		//	loadSections(sections);
		}

	}

}
