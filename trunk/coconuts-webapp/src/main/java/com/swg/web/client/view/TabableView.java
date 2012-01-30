package com.swg.web.client.view;

import com.google.gwt.user.client.ui.IsWidget;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;

/**
 * Interface yang diimplementasi agar view object atau widget ini
 * dapat ditampilkan dalam {@link TabPanel}
 * 
 * @author zakyalvan
 */
public interface TabableView extends IsWidget {
	/**
	 * Atur konfigurasi tab, sehingga presentasi tab pada {@link TabPanel}
	 * bisa disesuaikan dengan keinginan.
	 * 
	 * @param config
	 */
	void configureTab(TabItemConfig config);
}
