package cocoro.lib.uiauto;

import java.util.HashMap;
import java.util.Map;

public class ElementMap {

	private Map<String, HashMap<String, String>> elements =  new HashMap<String, HashMap<String, String>>();
	
	public void add(String name, HashMap<String, String> idMap){	
		this.elements.put(name.toLowerCase(), idMap);	
	}
	
	public Map<String,String> getInfo(String name){		
		return this.elements.get(name.toLowerCase());
	}
	
}
