package com.swg.client.view;

import com.google.gwt.user.client.ui.IsWidget;
import com.sencha.gxt.widget.core.client.Window;

/**
 * Base class untuk view yang ditampilkan atau didisplay menggunakan {@link Window}.
 * 
 * @author zakyalvan
 */
public interface WindowableView extends IsWidget {
	/**
	 * Method ini digunakan oleh view atau widget object yang mengimplementasi
	 * interface ini untuk menconfigure presentasi dari {@link Window} yang akan digunakan
	 * dalam menampilkan view tersebut.
	 * 
	 * @param window
	 */
	void configureWindow(Window window);
}