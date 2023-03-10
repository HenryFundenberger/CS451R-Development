package com.cs451.chadapplication.Repository;

import com.cs451.chadapplication.Entity.PositionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends CrudRepository<PositionEntity, String> {
}
