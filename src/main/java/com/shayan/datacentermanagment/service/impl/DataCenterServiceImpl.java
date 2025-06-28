package com.shayan.datacentermanagment.service.impl;

import com.shayan.datacentermanagment.exception.NotFoundException;
import com.shayan.datacentermanagment.model.DataCenter;
import com.shayan.datacentermanagment.reposiory.DataCenterRepository;
import com.shayan.datacentermanagment.service.DataCenterService;
import com.shayan.datacentermanagment.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class DataCenterServiceImpl implements DataCenterService {

    private final DataCenterRepository dataCenterRepository;
    private final ValidationUtil validationUtil;

    @Override
    public DataCenter save(DataCenter dataCenter) {
        return dataCenterRepository.save(dataCenter);
    }

    @Override
    public DataCenter load(Long id) {
        return dataCenterRepository.findById(id).orElseThrow(()-> new NotFoundException("not found DataCenter"));
    }

    @Override
    public DataCenter update(DataCenter dataCenter) {
        return dataCenterRepository.save(dataCenter);
    }

    @Override
    public void delete(Long id) {
        validationUtil.validateExists(dataCenterRepository,id,"DataCenter");
        dataCenterRepository.deleteById(id);
    }


}
