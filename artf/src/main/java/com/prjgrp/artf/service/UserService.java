package com.prjgrp.artf.service;

import com.prjgrp.artf.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);

    Optional<User> getUserById(Long id);

    List<User> getAllUsers();

    Optional<User> updateUser(Long id, User userDetails);

    boolean deleteUser(Long id);

    Page<User> getAllUsersPaginateAndSort(Pageable pageable);

    public Page<User> getAllUsersPaginateAndSort();
 Page<User> getUsers(Pageable pageable);
   
}