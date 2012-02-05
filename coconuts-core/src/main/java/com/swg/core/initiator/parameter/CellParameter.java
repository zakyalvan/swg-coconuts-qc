/**
 * 
 */
package com.swg.core.initiator.parameter;

import java.io.Serializable;

/**
 * Interface sederhana sebagai wrapper value setiap sell file xls
 * @author satriaprayoga
 *
 */
public interface CellParameter<E extends Serializable> extends Serializable{
	
	void setValue(E value);
	E getValue();
	
	void setProceed(boolean proceed);
	boolean isProceed();
	
	/**
	 * Interface sederhana sebagai wrapper value setiap sell file xls (untuk data type Double)
	 * 
	 *
	 */
	public static interface NumberParameter extends CellParameter<Double>{
		
	}
	
	/**
	 * Interface sederhana sebagai wrapper value setiap sell file xls (untuk data type String)
	 * 
	 *
	 */
	public static interface StringParameter extends CellParameter<String>{

	}
	
	
}
