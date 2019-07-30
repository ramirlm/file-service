package com.project.fileservice.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

import com.project.fileservice.domain.File;
import com.project.fileservice.service.FileService;
import com.project.fileservice.utils.FileUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<Iterable<File>> findAll() {
        try {
            Iterable<File> result = fileService.findAll();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return ResponseEntity.ok().body(result);
    }
    
    @CrossOrigin(origins = "${fileservice.config.api}")
    @PostMapping("/")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        try {
            Path filePath = Paths.get(FileUtil.multipartToFile(file).getPath());
            BasicFileAttributes attr = Files.readAttributes(filePath, BasicFileAttributes.class);
            System.out.println(file.getContentType());
            System.out.println(attr.lastModifiedTime().toMillis());
        } catch (IOException exception) {
            exception.printStackTrace();
            return ResponseEntity.badRequest().body("Error when uploading the file");
        }

        return ResponseEntity.ok().body("Success");
    }


}