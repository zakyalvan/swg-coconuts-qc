package com.swg.sms.service;

/**
 * Iseng aja ini, ga serius kok.
 * 
 * @author zakyalvan
 *
 * @param <T>
 */
public abstract class SettingParameter<T> {
	public static final BooleanParameter SERIAL_NOFLUSH = new BooleanParameter("smslib.serial.noflush");
	public static final BooleanParameter SERIAL_NOEVENTS = new BooleanParameter("smslib.serial.noevents");
	public static final BooleanParameter SERIAL_POLLING = new BooleanParameter("smslib.serial.polling");
	public static final NumberParameter SERIAL_POLLING_INTERVAL = new NumberParameter("smslib.serial.pollinginterval");
	public static final NumberParameter SERIAL_TIMEOUT = new NumberParameter("smslib.serial.timeout");
	public static final NumberParameter SERIAL_KEEPALIVE_INTERVAL = new NumberParameter("smslib.serial.keepalive");
	public static final NumberParameter SERIAL_BUFFER_SIZE = new NumberParameter("smslib.serial.buffer");
	public static final NumberParameter SERIAL_CLEAR_WAIT = new NumberParameter("smslib.serial.clearwait");
	// Ga ada
	public static final BooleanParameter SERIAL_RTSCTS_OUT = new BooleanParameter("smslib.serial.rctsout");
	public static final NumberParameter QUEUE_RETRIES = new NumberParameter("smslib.queue.retries");
	public static final NumberParameter AT_WAIT = new NumberParameter("smslib.at.wait");
	public static final NumberParameter AT_WAIT_AFTER_RESET = new NumberParameter("smslib.at.resetwait");
	public static final NumberParameter AT_WAIT_CMD = new NumberParameter("smslib.at.cmdwait");
	public static final NumberParameter AT_WAIT_CGMS = new NumberParameter("smslib.at.cmgswait");
	public static final NumberParameter AT_WAIT_NETWORK = new NumberParameter("smslib.at.networkwait");
	public static final NumberParameter AT_WAIT_SIMPIN = new NumberParameter("smslib.at.simpinwait");
	public static final NumberParameter AT_WAIT_CNMI = new NumberParameter("smslib.at.cnmiwait");
	public static final NumberParameter CNMI_EMULATOR_INTERVAL = new NumberParameter("smslib.serial.clearwait");
	public static final NumberParameter OUTBOUND_RETRIES = new NumberParameter("smslib.outbound.retries");
	public static final NumberParameter OUTBOUND_RETRY_WAIT = new NumberParameter("smslib.outbound.retrywait");
	public static final NumberParameter WATCHDOG_INTERVAL = new NumberParameter("smslib.watchdog");
	// Ga ada.
	public static final BooleanParameter MASK_IMSI = new BooleanParameter("smslib.maskimsi");
	public static final BooleanParameter CONCURRENT_GATEWAY_START = new BooleanParameter("smslib.disable.concurrent.gateway.startup");
	public static final BooleanParameter DISABLE_CMTI = new BooleanParameter("smslib.nocmti");
	// Ga ada.
	public static final NumberParameter HOURS_TO_ORPHAN = new NumberParameter("smslib.hourstoorphan");
	public static final BooleanParameter DISABLE_CMMSS = new BooleanParameter("smslib.nocmms");
	public static final BooleanParameter DISABLE_COPS = new BooleanParameter("smslib.nocops");
	public static final StringParameter CACHE_DIRECTORY = new StringParameter("smslib.cachedir");
	public static final StringParameter QUEUE_DIRECTORY = new StringParameter("smslib.queuedir");
	
	
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	protected String name;
	protected T value;
	
	public SettingParameter(String name) {
		this(name, null);
	}
	public SettingParameter(String name, T value) {
		this.name = name;
		setValue(value);
	}
	
	public String getName() {
		return name;
	}
	
	T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
		System.setProperty(name, (value != null) ? value.toString() : null);
	}
	
	public static class StringParameter extends SettingParameter<String> {
		public StringParameter(String name) {
			super(name);
		}
	}
	public static class NumberParameter extends SettingParameter<Number> {
		public NumberParameter(String name) {
			super(name);
		}
	}
	public static class BooleanParameter extends SettingParameter<Boolean> {
		public BooleanParameter(String name) {
			super(name);
		}
	}
}
