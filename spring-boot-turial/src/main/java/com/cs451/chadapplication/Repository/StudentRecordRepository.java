package com.cs451.chadapplication.Repository;

import com.cs451.chadapplication.Entity.StudentRecordEntity;
import org.springframework.data.repository.CrudRepository;

public interface StudentRecordRepository extends CrudRepository<StudentRecordEntity, Integer> {
}
