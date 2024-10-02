package cocoro.lib.tdloader;

import java.util.HashMap;
import java.util.Map;

public class LStringMap {
	private Map<String, String> map = new HashMap<String, String>();
	
	public String addLString(String stringID, String value) {
		return this.map.put(stringID.toLowerCase(), value);
	}
	
	public String getLString(String stringID) {
		return this.map.get(stringID.toLowerCase());
	}

}
