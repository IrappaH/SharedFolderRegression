package cocoro.pages;

import cocoro.lib.loader.Loader;
import cocoro.lib.uiauto.UIControlContainer;

public interface Page extends UIControlContainer {

	void quit();
	Loader getLoader();	
}
