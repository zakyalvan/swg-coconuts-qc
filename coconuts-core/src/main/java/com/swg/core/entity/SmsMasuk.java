package com.swg.core.entity;

import java.io.Serializable;
import java.util.Date;

public class SmsMasuk implements Serializable {
	private static final long serialVersionUID = -5943710058993175357L;

	public static enum StatusPemrosesan {
		SUCCESS, FAILED
	}
	
	private String nomorPengirim;
	private String isiPesan;
	private Date waktuMasuk;
	private StatusPemrosesan status;
	
}
