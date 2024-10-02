package cocoro.lib.uiauto;

import java.util.List;

public interface UIControlContainer {
	 UIControl uiControl(String name) throws Exception;
	 List<UIControl> uiControls(String name) throws Exception;
}
