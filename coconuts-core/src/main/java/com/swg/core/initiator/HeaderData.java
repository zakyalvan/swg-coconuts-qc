/**
 * 
 */
package com.swg.core.initiator;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Interface untuk menampung data header / judul pada kolom file xls
 * @author satriaprayoga
 *
 */
public interface HeaderData extends Serializable{
	
	/**
	 * Jumlah kolom
	 * @param num
	 */
	public void setNumOfHeader(int num);
	public int getNumOfHeader();
	
	/**
	 * Data tiap judul kolom di tampund di map
	 * @return
	 */
	public Map<Object,String> getHeaders();
	
	/**
	 * cek apakah data judul yang ditampung memiliki isi berupa string yang diinginkan
	 * @param characters parameter string yang akan di cek
	 * @return
	 */
	public boolean contain(String characters);
	public int getPosition(Object keyword);
	
	/**
	 * method untuk manipulasi data baris dibawah judul
	 * @see {@link com.swg.core.initiator.ContentData}
	 * @param data
	 */
	public void addChild(ContentData data);
	public List<ContentData> getContents();
	
}
