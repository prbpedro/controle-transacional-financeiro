package com.github.prbpedro.ctf.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.prbpedro.ctf.entidades.OperationType;

@Repository
public interface OperationTypeRepository extends JpaRepository<OperationType, Integer>{
}
