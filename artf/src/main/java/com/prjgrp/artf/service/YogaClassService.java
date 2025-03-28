package com.prjgrp.artf.service;

import com.prjgrp.artf.model.YogaClass;
import com.prjgrp.artf.repository.YogaClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class YogaClassService {
    @Autowired
    private YogaClassRepository yogaClassRepository;

    public YogaClass createYogaClass(YogaClass yogaClass) {
        return yogaClassRepository.save(yogaClass);
    }

    public Optional<YogaClass> getYogaClassById(Long id) {
        return yogaClassRepository.findById(id);
    }

    public List<YogaClass> getAllYogaClasses() {
        return yogaClassRepository.findAll();
    }

    public Optional<YogaClass> updateYogaClass(Long id, YogaClass yogaClassDetails) {
        return yogaClassRepository.findById(id).map(yogaClass -> {
            yogaClass.setName(yogaClassDetails.getName());
            yogaClass.setDescription(yogaClassDetails.getDescription());
            return yogaClassRepository.save(yogaClass);
        });
    }

    public boolean deleteYogaClass(Long id) {
        return yogaClassRepository.findById(id).map(yogaClass -> {
            yogaClassRepository.delete(yogaClass);
            return true;
        }).orElse(false);
    }
}