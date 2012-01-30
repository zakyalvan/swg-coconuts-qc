package com.swg.web.client.presenter;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * Kontrak dasar untuk Presenter.
 * Ini adalah bagian dari MPV pattern.
 * Implementasi MPV menggunakan paradigma passive view,
 * dimana view tidak mengetahui sama sekali tentang model
 * atau dengan kata lain, yang beriteraksi dengan model (server)
 * hanya presenter saja.
 * 
 * @author zakyalvan
 */
public interface Presenter<V extends IsWidget> {
	V getView();
}