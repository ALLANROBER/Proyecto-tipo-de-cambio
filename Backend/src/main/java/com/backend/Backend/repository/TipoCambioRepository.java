package com.backend.Backend.repository;

import com.backend.Backend.entity.TipoCambio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoCambioRepository extends JpaRepository<TipoCambio, Long> {
}
