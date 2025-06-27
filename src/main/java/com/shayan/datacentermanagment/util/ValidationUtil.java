package com.shayan.datacentermanagment.util;

import com.shayan.datacentermanagment.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidationUtil {

    public <T, ID> void validateExists(JpaRepository<T, ID> repository, ID id, String entityName) {
        if (!repository.existsById(id)) {
            throw new NotFoundException(entityName + " not found with ID: " + id);
        }
    }
}
