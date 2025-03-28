package com.prjgrp.artf.repository;

import com.prjgrp.artf.model.InstructionalVideo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InstructionalVideoRepository extends JpaRepository<InstructionalVideo, Long> {
    @Query("SELECT iv FROM InstructionalVideo iv WHERE iv.user.id = :userId")
    List<InstructionalVideo> findInstructionalVideosByUserId(@Param("userId") Long userId);

    List<InstructionalVideo> findByTitle(String title);

    Page<InstructionalVideo> findAll(Pageable pageable);
}