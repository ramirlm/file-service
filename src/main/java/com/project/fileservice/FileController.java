package com.project.fileservice.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Iterator;

import com.project.fileservice.domain.File;
import com.project.fileservice.service.FileService;
import com.project.fileservice.utils.FileUtil;

import org.assertj.core.util.Arrays;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileController {
    
    private FileService fileService;

    @CrossOrigin(origins = "${fileservice.config.api}")
    @GetMapping("/")
    public ResponseEntity<Iterator<File>> findAll() {
        try {
            Iterator<File> result = fileService.findAll().iterator();
            return ResponseEntity.ok().body(result);
        } catch (IOException exception) {
            exception.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }

    }
    
    @CrossOrigin(origins = "${fileservice.config.api}")
    @PostMapping("/")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        try {
            Path filePath = Paths.get(FileUtil.multipartToFile(file).getPath());
            BasicFileAttributes attr = Files.readAttributes(filePath, BasicFileAttributes.class);
        } catch (IOException exception) {
            exception.printStackTrace();
            return ResponseEntity.badRequest().body("An error occurred uploading the file");
        }

        return ResponseEntity.ok().body("Success");
    }
    
    @CrossOrigin(origins = "${fileservice.config.api}")
    @PutMapping("/")
    public ResponseEntity<String> update(@RequestParam("file") MultipartFile file) {
        try {
            Path filePath = Paths.get(FileUtil.multipartToFile(file).getPath());
            BasicFileAttributes attr = Files.readAttributes(filePath, BasicFileAttributes.class);
            return ResponseEntity.ok().body("Success");
        } catch (IOException exception) {
            exception.printStackTrace();
            return ResponseEntity.badRequest().body("An error occurred updating the file");
        }

    }
    
    @CrossOrigin(origins = "${fileservice.config.api}")
    @DeleteMapping("/")
    public ResponseEntity<String> delete(String fileId) {
        try {
            fileService.delete(fileId);
            return ResponseEntity.ok().body("Success");
        } catch (IOException exception) {
            exception.printStackTrace();
            return ResponseEntity.badRequest().body("An error occurred uploading the file");
        }

    }


}