package com.example.gerimedicaexam.dto;

import com.example.gerimedicaexam.domain.Record;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class RecordDto {
    private String source;
    private String codeListCode;
    private String code;
    private String displayValue;
    private String longDescription;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Integer sortingPriority;

    public RecordDto(Record record) {
        this.source = record.getSource();
        this.codeListCode = record.getCodeListCode();
        this.code = record.getCode();
        this.displayValue = record.getDisplayValue();
        this.longDescription = record.getLongDescription();
        this.fromDate = record.getFromDate();
        this.toDate = record.getToDate();
        this.sortingPriority = record.getSortingPriority();
    }
}
