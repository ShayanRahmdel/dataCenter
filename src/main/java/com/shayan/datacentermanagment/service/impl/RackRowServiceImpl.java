package com.shayan.datacentermanagment.service.impl;

import com.shayan.datacentermanagment.aspect.LogActivity;
import com.shayan.datacentermanagment.exception.NotFoundException;
import com.shayan.datacentermanagment.model.RackRow;
import com.shayan.datacentermanagment.reposiory.RackRowRepository;
import com.shayan.datacentermanagment.service.RackRowService;
import com.shayan.datacentermanagment.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RackRowServiceImpl implements RackRowService {

    private final RackRowRepository rackRowRepository;
    private final ValidationUtil validationUtil;

    @LogActivity("adding new RackRow")
    @Override
    public RackRow save(RackRow rackRow) {
        return rackRowRepository.save(rackRow);
    }

    @Override
    public RackRow load(Long id) {
        return rackRowRepository.findById(id).orElseThrow(()-> new NotFoundException("not found RackRow"));
    }
    @LogActivity("update RackRow")
    @Override
    public RackRow update(RackRow rackRow) {
        return null;
    }

    @LogActivity("update RackRow")
    @Override
    public void delete(Long id) {
        validationUtil.validateExists(rackRowRepository,id,"RackRow");
        rackRowRepository.deleteById(id);
    }
}
