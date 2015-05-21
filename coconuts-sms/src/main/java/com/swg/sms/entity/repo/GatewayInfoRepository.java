package com.swg.sms.entity.repo;

import com.swg.sms.entity.GatewayInfo;
import com.swg.sms.entity.SerialGatewayInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Kontrak untuk repository gateway info dan turunannya.
 *
 * @author zakyalvan
 */
@Repository
public interface GatewayInfoRepository extends JpaRepository<GatewayInfo, String>, JpaSpecificationExecutor<GatewayInfo> {
    /**
     * Query satu gateway info berdasarkan jenis spesifik dan id-nya. Misalanya query
     * untuk info jenis gateway {@link SerialGatewayInfo}.
     *
     * @param gatewayType
     * @param id
     * @return
     */
    @Query(
            value = "SELECT gi FROM GatewayInfo AS gi WHERE TYPE(gi) = :gatewayType AND gi.id = :id",
            countQuery = "SELECT COUNT(gi) FROM GatewayInfo AS gi WHERE TYPE(gi) = :gatewayType AND gi.id = :id")
    <T extends GatewayInfo> T findOne(@Param("id") String id, @Param("gatewayType") Class<T> gatewayType);

    /**
     * Query untuk {@link GatewayInfo} berdasarkan jenis yang diharapin.
     *
     * @param String expectedType
     * @return List<? extends GatewayInfo>
     */
    @Query(
            value = "SELECT gi FROM GatewayInfo AS gi WHERE TYPE(gi) = :expectedType",
            countQuery = "SELECT COUNT(gi) FROM GatewayInfo AS gi WHERE TYPE(gi) = :expectedType"
    )
    <T extends GatewayInfo> List<T> findAll(@Param("expectedType") Class<T> expectedType);

    @Query(
            value = "SELECT gi FROM GatewayInfo AS gi WHERE TYPE(gi) = SerialGatewayInfo",
            countQuery = "SELECT COUNT(gi) FROM GatewayInfo AS gi WHERE TYPE(gi) = SerialGatewayInfo"
    )
    <T extends GatewayInfo> List<T> findSerialGateway();

    /**
     * Query apakah gateway info sudah terdaftar atau tidak di system
     * berdasarkan id-nya.
     *
     * @param String id
     * @return boolean
     */
    @Query(
            value = "SELECT CASE WHEN COUNT(gi)>0 THEN TRUE ELSE FALSE END FROM GatewayInfo AS gi WHERE gi.id = :id"
    )
    boolean isRegisteredGateway(@Param("id") String id);
}
