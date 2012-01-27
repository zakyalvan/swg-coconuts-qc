package com.swg.client.view.widget;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.BeforeHideEvent;
import com.sencha.gxt.widget.core.client.event.BeforeHideEvent.BeforeHideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.swg.client.event.AddGatewayEvent;
import com.swg.client.view.WindowableView;
import com.swg.client.view.widget.SerialGatewayEditorWidget.EditorDriver;
import com.swg.shared.proxy.SerialGatewayInfoProxy;

/**
 * Editor (form) widget yang digunakan untuk meng-create atau meng-update
 * object {@link SerialGatewayInfoProxy}.
 * 
 * @author zakyalvan
 */
public class SerialGatewayEditorWidget implements WindowableView, Editor<SerialGatewayInfoProxy>, SelectHandler {
	interface EditorDriver extends RequestFactoryEditorDriver<SerialGatewayInfoProxy, Editor<SerialGatewayInfoProxy>> {}
	
	public static final String SAVE_BUTTON_ID = "save-button";
	public static final String CANCEL_BUTTON_ID = "cancel-button";
	
	private Logger logger = Logger.getLogger("SerialGatewayEditorWidget");
	
	private EventBus eventBus;
	private EditorDriver editorDriver;
	
	private SerialGatewayInfoProxy edited;
	private RequestContext editedRequest;
	
	private SimpleContainer container;
	
	TextField idEditor;
	TextField serialPortEditor;
	NumberField<Integer> baudRateEditor;
	TextField manufacturerEditor;
	TextField modelNumberEditor;
	
	private TextButton saveButton;
	private TextButton cancelButton;
	
	@Inject(optional=false)
	public SerialGatewayEditorWidget(EventBus eventBus) {
		logger.setLevel(Level.FINEST);
		this.eventBus = eventBus;
		
		editorDriver = GWT.create(EditorDriver.class);
		
		container = new SimpleContainer();
		VerticalLayoutContainer layoutContainer = new VerticalLayoutContainer();
		
		idEditor = new TextField();
		idEditor.setAllowBlank(false);
		layoutContainer.add(new FieldLabel(idEditor, "Gateway Id"));
		
		serialPortEditor = new TextField();
		serialPortEditor.setAllowBlank(false);
		layoutContainer.add(new FieldLabel(serialPortEditor, "Serial Port"));
		
		baudRateEditor = new NumberField<Integer>(new NumberPropertyEditor.IntegerPropertyEditor());
		baudRateEditor.setAllowBlank(false);
		layoutContainer.add(new FieldLabel(baudRateEditor, "Baud Rate"));
		
		manufacturerEditor = new TextField();
		manufacturerEditor.setAllowBlank(false);
		layoutContainer.add(new FieldLabel(manufacturerEditor, "Produsen"));
		
		modelNumberEditor = new TextField();
		modelNumberEditor.setAllowBlank(false);
		layoutContainer.add(new FieldLabel(modelNumberEditor, "Model"));
		
		saveButton = new TextButton("Simpan");
		saveButton.setId(SAVE_BUTTON_ID);
		saveButton.addSelectHandler(this);
		
		cancelButton = new TextButton("Batal");
		cancelButton.setId(CANCEL_BUTTON_ID);
		cancelButton.addSelectHandler(this);
		
		container.add(layoutContainer);
		
		editorDriver.initialize(this);
	}
	
	@Override
	public Widget asWidget() {
//		if(edited == null) {
//			container.mask("Edited object == null");
//		}
		return container;
	}

	@Override
	public void configureWindow(Window window) {
		window.setHeadingText("Tambah Serial Gateway");
		window.setShadow(true);
		window.setClosable(true);
		window.setModal(true);
		
		window.setButtonAlign(BoxLayoutPack.END);
		window.setMinButtonWidth(75);
		window.getButtonBar().add(saveButton);
		window.getButtonBar().add(cancelButton);
		
		window.addBeforeHideHandler(new BeforeHideHandler() {
			@Override
			public void onBeforeHide(BeforeHideEvent event) {
				if(editorDriver.hasErrors()) {
					event.setCancelled(true);
				}
			}
		});
	}

	public void startEditor(SerialGatewayInfoProxy gatewayInfoProxy, RequestContext requestContext) {
		logger.info("Start editor with gateway info proxy object : " + gatewayInfoProxy);
		edited = gatewayInfoProxy;
		editedRequest = requestContext;
	}
	
	@Override
	public void onSelect(SelectEvent event) {
		if(event.getSource() != null && event.getSource() instanceof TextButton) {
			TextButton sourceButton = (TextButton) event.getSource();
			if(sourceButton.getId().equals(SAVE_BUTTON_ID)) {
				logger.info("Save gateway info proxy.");				
				
				SerialGatewayInfoProxy proxy = editedRequest.create(SerialGatewayInfoProxy.class);
				editorDriver.edit(proxy, editedRequest);
				logger.info("Edited Id sebelum diflush : " + edited.getManufacturer());
				
				editorDriver.flush();
				
				logger.info("Edited Id setelah diflush : " + edited.getManufacturer());
				
				if(editorDriver.hasErrors()) {
					
				}
				else {
					eventBus.fireEvent(new AddGatewayEvent(edited));
				}
			}
			else if(sourceButton.getId().equals(CANCEL_BUTTON_ID)) {
				logger.info("Cancel button clicked!");
			}
		}
	}
}
