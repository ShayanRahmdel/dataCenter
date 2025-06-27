package com.shayan.datacentermanagment.service.impl;

import com.shayan.datacentermanagment.exception.NotFoundException;
import com.shayan.datacentermanagment.model.Rack;
import com.shayan.datacentermanagment.model.RackRow;
import com.shayan.datacentermanagment.reposiory.RackRepository;
import com.shayan.datacentermanagment.service.RackRowService;
import com.shayan.datacentermanagment.service.RackService;
import com.shayan.datacentermanagment.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RackServiceImpl implements RackService {

    private final RackRepository rackRepository;
    private final ValidationUtil validationUtil;


    @Override
    public Rack save(Rack rack) {
        return rackRepository.save(rack);
    }

    @Override
    public Rack load(Long id) {
        return rackRepository.findById(id).orElseThrow(()-> new NotFoundException("not found Rack "));
    }

    @Override
    public Rack update(Rack rack) {
        return null;
    }

    @Override
    public void delete(Long id) {
        validationUtil.validateExists(rackRepository,id,"Rack");
        rackRepository.deleteById(id);
    }
}
