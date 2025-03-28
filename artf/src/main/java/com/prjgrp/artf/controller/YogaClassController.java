package com.prjgrp.artf.controller;
import com.prjgrp.artf.model.YogaClass;
import com.prjgrp.artf.service.YogaClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class YogaClassController {
    @Autowired
    private YogaClassService yogaClassService;

    @PostMapping
    public ResponseEntity<YogaClass> createYogaClass(@RequestBody YogaClass yogaClass) {
        return ResponseEntity.status(HttpStatus.CREATED).body(yogaClassService.createYogaClass(yogaClass));
    }

    @GetMapping("/{id}")
    public ResponseEntity<YogaClass> getYogaClassById(@PathVariable Long id) {
        return yogaClassService.getYogaClassById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<YogaClass>> getAllYogaClasses() {
        return ResponseEntity.ok(yogaClassService.getAllYogaClasses());
    }

    @PutMapping("/{id}")
    public ResponseEntity<YogaClass> updateYogaClass(@PathVariable Long id, @RequestBody YogaClass yogaClassDetails) {
        return yogaClassService.updateYogaClass(id, yogaClassDetails).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteYogaClass(@PathVariable Long id) {
        if (yogaClassService.deleteYogaClass(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}