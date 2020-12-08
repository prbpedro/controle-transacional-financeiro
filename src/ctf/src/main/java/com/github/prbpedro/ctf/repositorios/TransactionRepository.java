package com.github.prbpedro.ctf.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.prbpedro.ctf.entidades.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
