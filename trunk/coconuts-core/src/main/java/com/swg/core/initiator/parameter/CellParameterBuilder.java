/**
 * 
 */
package com.swg.core.initiator.parameter;

import java.io.Serializable;

/**
 * @author satriaprayoga
 *
 */
public class CellParameterBuilder {
	
	public static <E extends Serializable> CellParameter<?> createCellParameter(Class<?> clazz,Object value){
		if(clazz.getName()==String.class.getName() && value instanceof String){
			TextParameter parameter=new TextParameter();
			parameter.setValue((String) value);
			return parameter;
		}else if(clazz.getName()==Double.class.getName() && value instanceof Double){
			DoubleParameter parameter=new DoubleParameter();
			parameter.setValue((Double)value);
			return parameter;
		}else{
			throw new IllegalArgumentException("invalid type");
		}
		
	}
}
