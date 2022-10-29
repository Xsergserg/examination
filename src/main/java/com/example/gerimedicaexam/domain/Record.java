package com.example.gerimedicaexam.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "records")
@Getter
public class Record {

    @Column
    private String source;

    @Column
    private String codeListCode;

    @Column
    @Id
    @NonNull
    private String code;

    @Column
    private String displayValue;

    @Column
    private String longDescription;

    @Column
    private LocalDate fromDate;

    @Column
    private LocalDate toDate;

    @Column
    private Integer sortingPriority;
}
