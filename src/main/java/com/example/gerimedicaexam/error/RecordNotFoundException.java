package com.example.gerimedicaexam.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecordNotFoundException extends Throwable {
    public RecordNotFoundException(String code) {
        super("Record with code " + code + " not found");
    }
}
