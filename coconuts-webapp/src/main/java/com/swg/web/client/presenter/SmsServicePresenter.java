package com.swg.web.client.presenter;

import java.util.List;

import com.google.gwt.editor.client.Editor;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.swg.web.client.event.AddGatewayRequestEvent;
import com.swg.web.client.event.RemoveGatewayRequestEvent;
import com.swg.web.client.presenter.SmsServicePresenter.View;
import com.swg.web.client.view.TabableView;
import com.swg.web.client.view.WindowableView;
import com.swg.web.shared.proxy.GatewayInfoProxy;

/**
 * Kontrak untuk presenter sms service management.
 * 
 * @author zakyalvan
 */
public interface SmsServicePresenter extends Presenter<View> {
	public interface View extends TabableView {
		public static final String START_SRVC_BTN_ID = "start-service";
		public static final String STOP_SRVC_BTN_ID = "stop-service";
		
		public static final String ADD_SERIAL_GTW_BTN_ID = "add-gateway";
		
		public static final String REMOVE_GTW_BTN_ID = "remove-gateway";
		public static final String UPDATE_GTW_BTN_ID = "update-gateway";
		public static final String ENABLE_GTW_BTN_ID = "enable-gateway";
		public static final String DISABLE_GTW_BTN_ID = "disable-gateway";
		
		/**
		 * Dipanggil oleh object konkrit presenter untuk menotifikasi bahwa service distart.
		 * Bisa jadi notifikasi ini merupakan notifikasi global.
		 */
		void notifyServiceStarted();
		
		/**
		 * Dipanggil oleh presenter untuk menotifikasi bahwa service gagal di-start.
		 * 
		 * @param failureCause
		 */
		void notifyServiceStartingFailed(Throwable failureCause);
		void notifyServiceStoped();
		void notifyServiceStopingFailed(Throwable failureCause);
		
		/**
		 * Perintahkan view object untuk memulai meminta user untuk memasukan info gateway.
		 * Method ini dipanggil setelah view ini merequest untuk membuat menambah gateway,
		 * atau secara teknis setelah event {@link AddGatewayRequestEvent} difire dan dihandle
		 * di dalam presenter. Setelah method ini dipanggil seharusnya form create gateway ditampilkan.
		 * (Ingat, passive view, view tidak memiliki referensi kepada presenter object).
		 * 
		 * @param {@link GatewayInfoProxy} gatewayInfoProxy
		 * @param {@link RequestContext} requestContext (Diperlukan oleh request factory editor driver).
		 */
		void startCreateGateway(GatewayInfoProxy gatewayInfoProxy, RequestContext requestContext);
		
		/**
		 * Sama dengan use case method {@code #startCreateGateway(GatewayInfoProxy)}
		 * 
		 * @param {@link GatewayInfoProxy} gatewayInfoProxy
		 */
		void startUpdateGateway(GatewayInfoProxy gatewayInfoProxy);
		
		/**
		 * Sama dengan use case method {@code #startCreateGateway(GatewayInfoProxy)}
		 * 
		 * @param gatewayInfos
		 */
		void setGatewayInfos(List<GatewayInfoProxy> gatewayInfos);
		
		/**
		 * Notify gateway sudah diremove.
		 * Sama dengan use case method {@code #startCreateGateway(GatewayInfoProxy)}
		 * yaitu setelah event {@link RemoveGatewayRequestEvent} di-fire.
		 * 
		 * @param gatewayInfos
		 */
		void notifyRemovedGateways(List<GatewayInfoProxy> gatewayInfos);
	}
	
	/**
	 * Kontrak untuk editor gateway info.
	 * 
	 * @author zakyalvan
	 *
	 * @param <T>
	 */
	public interface GatewayInfoEditor<T extends GatewayInfoProxy> extends WindowableView, Editor<T>, HasSelectHandlers {
		public static final String SAVE_SELECT_KEY = "save-select-key";
		public static final String CANCEL_SELECT_KEY = "cancel-select-key";
		
		Class<T> getEditedType();
		void startEditor(T gatewayProxy, RequestContext requestContext);
	}
		
	void startService();
	void stopService();
}
