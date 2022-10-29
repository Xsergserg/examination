package com.example.gerimedicaexam.domain;

import com.example.gerimedicaexam.repository.RecordsRepository;
import com.example.gerimedicaexam.utils.IntUtils;
import com.example.gerimedicaexam.dto.Record;
import com.example.gerimedicaexam.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {
    private final RecordsRepository recordsRepository;

    public void upload(MultipartFile file) {
        List<Record> records = getRecordsFromCsv(file);
        recordsRepository.saveAll(records);
    }

    public static List<Record> getRecordsFromCsv(MultipartFile csvFile) {
        try (
                InputStream is = csvFile.getInputStream();
                BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))
                )
        {
            CSVFormat csvFormat = CSVFormat.Builder.create()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .build();
            List<Record> records = new LinkedList<>();
            Iterable<CSVRecord> csvRecords = csvFormat.parse(fileReader);
            for (CSVRecord csvRecord : csvRecords)
                records.add(new Record(
                        csvRecord.get("source"),
                        csvRecord.get("codeListCode"),
                        csvRecord.get("code"),
                        csvRecord.get("displayValue"),
                        csvRecord.get("longDescription"),
                        DateUtils.parseDate(csvRecord.get("fromDate")),
                        DateUtils.parseDate(csvRecord.get("toDate")),
                        IntUtils.parseInt(csvRecord.get("sortingPriority"))));
            return records;
        } catch (Exception e) {
            throw new RuntimeException("Check your CSV file. Parsing error: " + e.getMessage());
        }
    }
}
