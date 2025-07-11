package com.shayan.datacentermanagment.service;


import com.shayan.datacentermanagment.model.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface InvoiceService {


    Page<Invoice> findUserInvoice(Long userId, Pageable pageable);


    Invoice load(Long id);

}
