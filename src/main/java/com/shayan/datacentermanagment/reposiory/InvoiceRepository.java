package com.shayan.datacentermanagment.reposiory;


import com.shayan.datacentermanagment.model.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface InvoiceRepository extends JpaRepository<Invoice,Long> {

    @Query("SELECT i from Invoice i where i.serviceInstance.user.id =:userId")
    Page<Invoice> findUserInvoice(@Param("userId")Long userId, Pageable pageable);
}
