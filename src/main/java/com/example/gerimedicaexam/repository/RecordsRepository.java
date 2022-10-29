package com.example.gerimedicaexam.repository;

import com.example.gerimedicaexam.dto.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordsRepository extends JpaRepository<Record, String> {
}
