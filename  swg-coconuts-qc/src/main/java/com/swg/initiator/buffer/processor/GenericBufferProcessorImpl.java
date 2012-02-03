package com.swg.initiator.buffer.processor;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.swg.initiator.convert.EntityConversionUtil;
import com.swg.initiator.entity.SimpleTps;
import com.swg.initiator.entity.VoteObserver;
import com.swg.initiator.repo.SimpleTpsRepository;

/**
 * This is not generic class!
 * this is the implementation to processing the generic workbook that used as test
 * @author satriaprayoga
 *
 */
@Component
public class GenericBufferProcessorImpl implements BufferProcessor {
	
	private final int OBS_FULLNAME=0;
	
	private final int OBS_PHONE=1;
	
	private final int KEL_KODE=2;
	
	private final int KEL=3;
	
	private final int KEC_KODE=4;
	
	private final int KEC=5;
	
	private final int KAB_KODE=6;
	
	private final int KAB=7;
	
	@Autowired
	private SimpleTpsRepository repository;
	

	@SuppressWarnings("unchecked")
	@Override
	public void doProcess(Object objects) {
		Map<Object,List<String>> map=(Map<Object,List<String>>)objects;
		Iterator<List<String>> values=map.values().iterator();
		while(values.hasNext()){
			List<String> current=values.next();
			VoteObserver obs=createObserver(current);
			SimpleTps  tps=createPemantauWilayah(current);
			tps.setVoteObserver(obs);
			repository.save(tps);
		}
	}
	
	
	private VoteObserver createObserver(List<String> data){
		VoteObserver observer=new VoteObserver();
		String fullName=data.get(OBS_FULLNAME);
		if(fullName.matches(EntityConversionUtil.NAME_PATTERN))
			observer.setFullName(fullName);
		String phone=data.get(OBS_PHONE);
		if(phone.matches(EntityConversionUtil.PHONE_PATTERN))
			observer.setCellularNumber(phone);
		/*
		 * // TODO: use the pattern matcher method or..regular expression..or whatever you like
		for(String s:data){
			if(s.matches(EntityConversionUtil.NAME_PATTERN))
				observer.setFullName(s);
			else if(s.matches(EntityConversionUtil.PHONE_PATTERN))
				observer.setCellularNumber(s);
			else
				createPemantauWilayah(data);
		}
		*/
		observer.setRegisteredDate(Calendar.getInstance().getTime());
		observer.setDeleted(false);
		System.out.println("create vote observer: "+observer.toString());
		return observer;
	}
	
	private SimpleTps createPemantauWilayah(List<String> data){
		SimpleTps pw=new SimpleTps();
		String kelurahan=data.get(KEL);
		pw.setKelurahan(kelurahan);
		String kodeKelurhan=data.get(KEL_KODE);
		pw.setKodeKelurahan(kodeKelurhan);
		String kecamatan=data.get(KEC);
		pw.setKecamatan(kecamatan);
		String kodeKecamatan=data.get(KEC_KODE);
		pw.setKodeKecamatan(kodeKecamatan);
		String kabupaten=data.get(KAB);
		pw.setKabupaten(kabupaten);
		String kodeKabupaten=data.get(KAB_KODE);
		pw.setKodeKabupaten(kodeKabupaten);
		/*
		 * // TODO: use the pattern matcher method or..regular expression..or whatever you like
		for(String s:data){
			if(s.matches(EntityConversionUtil.KODE_KELURAHAN_PATTERN))
				pw.setKodeKelurahan(s);
			else if(s.matches(EntityConversionUtil.KODE_WILAYAH_PATTERN))
				pw.setKodeKabupaten(s);
			else
				break;
		}
		*/
		System.out.println("create wialayh: "+pw.toString());
		return pw;
	}

}
