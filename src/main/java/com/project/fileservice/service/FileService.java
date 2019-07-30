package com.project.fileservice.service;

import com.project.fileservice.domain.File;
import com.project.fileservice.repository.*;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {

    private FileRepository fileRepository;

    public Iterable<File> findAll() {
        return fileRepository.findAll();
    }
}