package com.example.gerimedicaexam.controller;

import com.example.gerimedicaexam.service.FileService;
import com.example.gerimedicaexam.domain.Record;
import com.example.gerimedicaexam.repository.RecordsRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RecordsController {

    private final FileService fileService;
    private final RecordsRepository recordsRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/upload")
    private void uploadFile(@NonNull @RequestParam("file") MultipartFile file) {
        fileService.upload(file);
    }

    @GetMapping("/records")
    public List<Record> getRecords() {
        return recordsRepository.findAll();
    }

    @GetMapping("/records/{code}")
    public Record getRecordByCode(@PathVariable String code) throws RuntimeException {
        return recordsRepository.findById(code).orElseThrow(()-> new RuntimeException(String.format("Record with code \"%s\" not found", code)));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/records")
    public void deleteRecords () {
        recordsRepository.deleteAll();
    }
}
