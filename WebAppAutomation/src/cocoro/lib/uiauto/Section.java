package cocoro.lib.uiauto;

import java.util.List;

import cocoro.lib.loader.Loader;
import cocoro.pages.Page;

public class Section implements UIControlContainer{

	private ElementMap elemMap = null;
	private UIControl root = null;
	
	public Section(String name, Page parent) throws Exception{	
		Loader loader = parent.getLoader();
		this.elemMap = loader.getSectionElemMap(name);
		System.out.println(loader.getSectionRootName(name));
		this.root = parent.uiControl(loader.getSectionRootName(name));
	}
	
	@Override
	public UIControl uiControl(String name) throws Exception{		
		return SeleniumUIControl.find(this.root.getRawWebElement(), elemMap.getInfo(name));
	}
	@Override
	public List<UIControl> uiControls(String name) throws Exception{
		return SeleniumUIControl.findAll(this.root.getRawWebElement(), elemMap.getInfo(name));
	}

}
