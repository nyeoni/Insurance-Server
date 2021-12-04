package com.hm.contractservice.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContractRepo extends JpaRepository<Contract, Long> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Contract c set c.insuranceName = :insuranceName where c.insuranceId = :insuranceId")
    void bulkModifyInsuranceName(@Param("insuranceId") Long insuranceId, @Param("insuranceName") String insuranceName);
}