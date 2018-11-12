package org.societegenerale.fr.account.dao;

import org.societegenerale.fr.account.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OperationRepository extends JpaRepository<Operation, Long> {

    @Query("select o from Operation o where o.account.code=:x order by o.operationDate desc")
    Page<Operation> operationList(@Param("x") String code, Pageable pageable);
}
