package com.github.prbpedro.ctf.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.prbpedro.ctf.entidades.Account;
import com.github.prbpedro.ctf.util.Constantes;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	
	@Query(Constantes.QUERY_EXIST_ACCOUNT_BY_DOCUMENT_NUMBER)
	boolean existsByDocumentNumber(Long documentNumber);

}
