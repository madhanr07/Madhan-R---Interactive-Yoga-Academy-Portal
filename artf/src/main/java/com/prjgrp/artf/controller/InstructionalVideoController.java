package com.prjgrp.artf.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.prjgrp.artf.model.InstructionalVideo;
import com.prjgrp.artf.service.InstructionalVideoService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/videos")
public class InstructionalVideoController {
    private final InstructionalVideoService instructionalVideoService;

    @Autowired
    public InstructionalVideoController(InstructionalVideoService instructionalVideoService) {
        this.instructionalVideoService = instructionalVideoService;
    }

    @PostMapping
    public ResponseEntity<InstructionalVideo> createInstructionalVideo(@RequestBody InstructionalVideo video) {
        InstructionalVideo createdVideo = instructionalVideoService.createInstructionalVideo(video);
        return new ResponseEntity<>(createdVideo, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstructionalVideo> getInstructionalVideoById(@PathVariable Long id) {
        Optional<InstructionalVideo> video = instructionalVideoService.getInstructionalVideoById(id);
        return video.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<InstructionalVideo>> getAllInstructionalVideos() {
        List<InstructionalVideo> videos = instructionalVideoService.getAllInstructionalVideos();
        return new ResponseEntity<>(videos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstructionalVideo> updateInstructionalVideo(@PathVariable Long id,
            @RequestBody InstructionalVideo video) {
        Optional<InstructionalVideo> updatedVideo = instructionalVideoService.updateInstructionalVideo(id, video);
        return updatedVideo.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructionalVideo(@PathVariable Long id) {
        boolean deleted = instructionalVideoService.deleteInstructionalVideo(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}