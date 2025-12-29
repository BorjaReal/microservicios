package com.example.microservicios_examenes.app.examenes.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.example.commons_exams.exams.models.entity.Exam;

public interface ExamRepository extends PagingAndSortingRepository<Exam, Long> {

    @Query("SELECT e FROM Exam e WHERE e.name LIKE %:term%")
    public List<Exam> findByNameLike(@Param("term") String term);
}
