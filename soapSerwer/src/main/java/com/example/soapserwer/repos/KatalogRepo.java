package com.example.soapserwer.repos;

import com.example.soapserwer.entity.KatalogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KatalogRepo extends JpaRepository<KatalogEntity, Long> {

    @Query(value = "select count(d) from data_entity d where d.manufacturer=:manufacturer", nativeQuery = true)
    public Long getCoutRowByManufacturer(@Param("manufacturer") String manufacturer);
}
