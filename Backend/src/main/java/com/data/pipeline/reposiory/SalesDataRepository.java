package com.data.pipeline.reposiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.data.pipeline.entity.SalesData;

@Repository
public interface SalesDataRepository extends JpaRepository<SalesData, Long> {
}
