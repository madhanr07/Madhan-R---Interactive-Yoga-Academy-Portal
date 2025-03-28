package com.prjgrp.artf.service;

import com.prjgrp.artf.model.InstructionalVideo;
import java.util.List;
import java.util.Optional;

public interface InstructionalVideoService {
    InstructionalVideo createInstructionalVideo(InstructionalVideo video);

    Optional<InstructionalVideo> getInstructionalVideoById(Long id);

    List<InstructionalVideo> getAllInstructionalVideos();

    Optional<InstructionalVideo> updateInstructionalVideo(Long id, InstructionalVideo video);

    boolean deleteInstructionalVideo(Long id);
}