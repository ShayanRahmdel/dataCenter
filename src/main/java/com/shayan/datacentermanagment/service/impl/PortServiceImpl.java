package com.shayan.datacentermanagment.service.impl;

import com.shayan.datacentermanagment.exception.NotFoundException;
import com.shayan.datacentermanagment.model.Port;
import com.shayan.datacentermanagment.reposiory.PortRepository;
import com.shayan.datacentermanagment.service.PortService;
import com.shayan.datacentermanagment.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PortServiceImpl implements PortService {

    private final PortRepository portRepository;
    private final ValidationUtil validationUtil;

    @Override
    public Port save(Port port) {
        return portRepository.save(port);
    }

    @Override
    public Port load(Long id) {
        return portRepository.findById(id).orElseThrow(()-> new NotFoundException("not found Port"));
    }

    @Override
    public Port update(Port port) {
        return null;
    }

    @Override
    public void delete(Long id) {
        validationUtil.validateExists(portRepository, id, "Port");
        portRepository.deleteById(id);
    }


}
