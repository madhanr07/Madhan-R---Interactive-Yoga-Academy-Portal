package com.prjgrp.artf.service;
import com.prjgrp.artf.model.InstructionalVideo;
import com.prjgrp.artf.repository.InstructionalVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service 
public class InstructionalVideoServiceImpl implements InstructionalVideoService {    
    @Autowired    
    private InstructionalVideoRepository instructionalVideoRepository;    
    @Override    
    public InstructionalVideo createInstructionalVideo(InstructionalVideo video) {        
        return instructionalVideoRepository.save(video);    
    }    
    @Override    
    public Optional<InstructionalVideo> getInstructionalVideoById(Long id) {        
        return instructionalVideoRepository.findById(id);    
    }    
    @Override    
    public List<InstructionalVideo> getAllInstructionalVideos() {        
        return instructionalVideoRepository.findAll();    
    }    
    @Override    
    public Optional<InstructionalVideo> updateInstructionalVideo(Long id, InstructionalVideo video) {        
        return instructionalVideoRepository.findById(id).map(existingVideo -> {            
            existingVideo.setTitle(video.getTitle());            
            existingVideo.setUrl(video.getUrl());            
            return instructionalVideoRepository.save(existingVideo);        
        });    
    }    
    @Override    
    public boolean deleteInstructionalVideo(Long id) {        
        return instructionalVideoRepository.findById(id).map(video -> {            
            instructionalVideoRepository.delete(video);            
            return true;        
        }).orElse(false);    
    }}