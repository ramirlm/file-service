package com.project.fileservice.repository;

import com.project.fileservice.domain.File;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends CrudRepository<File, Long> {
    
}