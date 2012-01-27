package com.swg.server.sms.service;

import java.util.Set;

import com.swg.server.sms.entity.GatewayInfo;

/**
 * Kontrak untuk gateway messaging management.
 * 
 * @author zakyalvan
 */
public interface GatewayManager<T extends GatewayInfo> {
	/**
	 * Accessor untuk jenis gateway (gateway info yang disupport oleh gateway manager ini).
	 * 
	 * @return Set<Class<? extends GatewayInfo>>
	 */
	Set<Class<? extends GatewayInfo>> getSupportedGatewayInfoTypes();
	
	/**
	 * Retrieve jenis gateway info yang didisupport oleh system.
	 * 
	 * @param supportedGatewayInfoTypes
	 */
	void setSupportedGatewayInfoTypes(Set<Class<? extends GatewayInfo>> supportedGatewayInfoTypes);
	
	
	/**
	 * Register gateway baru ke dalam system.
	 * Sebelum diregister ke dalam internal service, 
	 * harus divalidasi terlebih dahulu (termasuk validasi 
	 * isi current internal service, mis : smslib service).
	 * Jika service sudah di-start maka service harus distop terlebih dahulu lalu di-restart.
	 * 
	 * @param {@link GatewayInfo} gateway
	 * @throws MessageServiceException
	 */
	void registerGateway(T gateway) throws MessageServiceException;
	
	/**
	 * Retrieve regitered gateway (info) berdasarkan id-nya.
	 * 
	 * @param String id
	 * @return {@link GatewayInfo}
	 */
	T getGateway(String id);
	
	/**
	 * Retieve seluruh gateway yang teregister.
	 * 
	 * @return {@link Set<GatewayInfo>}
	 */
	Set<T> getGateways();
	
	/**
	 * Retrieve seluruh gateway yang teregister berdasarkan capability-nya.
	 * Lihat enum {@link Capability}
	 * 
	 * @param Integer gateway capability (all == 1 | inbound == 2 | outbound == 3)
	 * @return {@link Set<GatewayInfo>}
	 */
	Set<T> getGateways(Integer capability);
	
	/**
	 * Enable gateway berdasarkan id-nya.
	 * Jika gateway dengan id yang diberikan tidak ditemukan, maka exception di-throw.
	 * Service harus distop terlebih dahulu jika sudah distart dan
	 * direstart setelah didisble.
	 * 
	 * @return
	 * @throws MessageServiceException
	 */
	T enableGateway(String id) throws MessageServiceException;
	
	/**
	 * Disable gateway berdasarkan id-nya, dengan demikian tidak dapat digunakan lagi,
	 * sampai dienable lagi. 
	 * Jika gateway dengan id yang diberikan tidak ditemukan, maka exception di-throw.
	 * Service harus distop terlebih dahulu jika sudah distart dan
	 * direstart setelah didisble.
	 * 
	 * @param id
	 * @return {@link GatewayInfo} gateway info yang didisable.
	 * @throws MessageServiceException
	 */
	T disableGateway(String id) throws MessageServiceException;
	
	/**
	 * Remove gateway berdasarkan id, dengan demikian tidak dapat digunakan lagi.
	 * Service harus distop terlebih dahulu jika sudah distart dan
	 * direstart setelah gateway bersangkutan di-remove.
	 * 
	 * @param String id
	 * @return {@link GatewayInfo} info gateway yang diremove, mungkin mau digunakan lagi.
	 */
	T removeGateway(String id) throws MessageServiceException;
}
