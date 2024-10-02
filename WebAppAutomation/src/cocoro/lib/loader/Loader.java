package cocoro.lib.loader;

import cocoro.lib.uiauto.ElementMap;

public interface Loader {

	void load(String filePath) throws Exception;
	ElementMap getElemMap();	
	ElementMap getSectionElemMap(String pageName);
	String getSectionRootName(String section);
	void load(String string, String browserName) throws Exception;
}
