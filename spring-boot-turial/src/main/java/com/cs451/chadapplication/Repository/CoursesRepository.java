package com.cs451.chadapplication.Repository;

import com.cs451.chadapplication.Entity.CoursesEntity;
import org.springframework.data.repository.CrudRepository;

public interface CoursesRepository extends CrudRepository<CoursesEntity, Integer> {
}
