package com.shayan.datacentermanagment.service.impl;

import com.shayan.datacentermanagment.exception.NotFoundException;
import com.shayan.datacentermanagment.model.Invoice;
import com.shayan.datacentermanagment.model.ServiceInstance;
import com.shayan.datacentermanagment.reposiory.InvoiceRepository;
import com.shayan.datacentermanagment.service.InvoiceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Override
    public Page<Invoice> findUserInvoice(Long userId, Pageable pageable) {
        return invoiceRepository.findUserInvoice(userId,pageable);
    }

    @Override
    public Invoice load(Long id) {
        return invoiceRepository.findById(id).orElseThrow(()-> new NotFoundException("not found Invoice"));
    }


}
