package com.shayan.datacentermanagment.service.impl;

import com.shayan.datacentermanagment.aspect.LogActivity;
import com.shayan.datacentermanagment.exception.NotFoundException;
import com.shayan.datacentermanagment.model.Equipment;
import com.shayan.datacentermanagment.model.Invoice;
import com.shayan.datacentermanagment.model.ServiceInstance;
import com.shayan.datacentermanagment.reposiory.ServiceInstanceRepository;
import com.shayan.datacentermanagment.service.EquipmentService;
import com.shayan.datacentermanagment.service.ServiceInstanceService;
import com.shayan.datacentermanagment.util.ValidationUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ServiceInstanceServiceImpl implements ServiceInstanceService {

    private final ServiceInstanceRepository serviceInstanceRepository;
    private final EquipmentService equipmentService;
    private final ValidationUtil validationUtil;

    @Transactional
    @Override
    @LogActivity("buy Service Instance and create Invoice")
    public ServiceInstance create(ServiceInstance serviceInstance/*,Long equipmentId*/) {
//        Equipment equipment = equipmentService.load(equipmentId);
//        serviceInstance.setEquipment(equipment);
//        Invoice invoice = Invoice.builder().paid(false).amount(equipment.getPrice()).issueDate(serviceInstance.getStartDate()).serviceInstance(serviceInstance).build();
//        serviceInstance.setInvoice(invoice);
        return serviceInstanceRepository.save(serviceInstance);
    }

    @Override
    public ServiceInstance load(Long id) {
        return serviceInstanceRepository.findById(id).orElseThrow(()-> new NotFoundException("not found Service instance"));
    }

    @LogActivity("change ServiceInstance")
    @Override
    public ServiceInstance update(Long id,ServiceInstance serviceInstance) {
        ServiceInstance dbServiceInstance = load(id);
        if (dbServiceInstance.getStartDate().equals(serviceInstance.getStartDate())||
        dbServiceInstance.getEndDate().equals(serviceInstance.getEndDate())){
            throw  new RuntimeException("same data for update");
        }
        dbServiceInstance.setUser(serviceInstance.getUser());
        dbServiceInstance.setStartDate(serviceInstance.getStartDate());
        dbServiceInstance.setEndDate(serviceInstance.getEndDate());
        Equipment equipment = equipmentService.load(serviceInstance.getEquipment().getId());
        dbServiceInstance.setEquipment(equipment);
        dbServiceInstance.setInvoice(serviceInstance.getInvoice());
        return serviceInstanceRepository.save(dbServiceInstance);
    }

    @LogActivity("delete ServiceInstance")
    @Override
    public void delete(Long id) {
        validationUtil.validateExists(serviceInstanceRepository,id,"ServiceInstance");
        serviceInstanceRepository.deleteById(id);
    }

    @Transactional
    @LogActivity("renew ServiceInstance")
    @Override
    public ServiceInstance renew(Long serviceInstanceId, int additionalMonths) {
        ServiceInstance instance = serviceInstanceRepository.findById(serviceInstanceId)
                .orElseThrow(() -> new NotFoundException("ServiceInstance not found"));

        // new endDate
        LocalDate newEndDate = instance.getEndDate().plusMonths(additionalMonths);
        instance.setEndDate(newEndDate);

        // create new Invoice
        BigDecimal amount = instance.getEquipment().getPrice()
                .multiply(BigDecimal.valueOf(additionalMonths));

        Invoice invoice = Invoice.builder()
                .amount(amount)
                .paid(false)
                .issueDate(LocalDate.now())
                .serviceInstance(instance)
                .build();

        instance.setInvoice(invoice);

        return serviceInstanceRepository.save(instance);
    }
}
