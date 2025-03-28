package com.prjgrp.artf.repository;

import com.prjgrp.artf.model.YogaClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface YogaClassRepository extends JpaRepository<YogaClass, Long> {
    @Query("SELECT yc FROM YogaClass yc WHERE yc.user.id = :userId")
    List<YogaClass> findYogaClassesByUserId(@Param ("userId") Long userId);

    @Query("SELECT yc FROM YogaClass yc WHERE yc.name = :name")
    YogaClass findYogaClassByName(@Param("name") String name);
}