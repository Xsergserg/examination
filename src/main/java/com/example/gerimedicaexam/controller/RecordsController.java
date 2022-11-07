package com.example.gerimedicaexam.controller;

import com.example.gerimedicaexam.dto.RecordDto;
import com.example.gerimedicaexam.error.ParsingException;
import com.example.gerimedicaexam.error.RecordNotFoundException;
import com.example.gerimedicaexam.service.FileService;
import com.example.gerimedicaexam.repository.RecordsRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RecordsController {

    private final FileService fileService;
    private final RecordsRepository recordsRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/upload")
    private void uploadFile(@NonNull @RequestParam("file") MultipartFile file) throws ParsingException, MultipartException {
        fileService.upload(file);
    }

    @GetMapping("/records")
    public List<RecordDto> getRecords() {
        return recordsRepository.findAll().stream().map(RecordDto::new).collect(Collectors.toList());
    }

    @GetMapping("/records/{code}")
    public RecordDto getRecordByCode(@PathVariable String code) throws RecordNotFoundException {
        return new RecordDto(recordsRepository.findById(code).orElseThrow(()-> new RecordNotFoundException(code)));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/records")
    public void deleteRecords () {
        recordsRepository.deleteAll();
    }
}
