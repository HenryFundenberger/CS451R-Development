package com.cs451.chadapplication.Repository;

import com.cs451.chadapplication.Entity.PositionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends CrudRepository<PositionEntity, Integer> {
    //public List<PositionEntity> findByClass_code(String class_code);
}
