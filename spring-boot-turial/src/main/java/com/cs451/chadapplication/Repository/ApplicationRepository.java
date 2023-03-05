package com.cs451.chadapplication.Repository;

import com.cs451.chadapplication.Entity.ApplicationEntity;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationRepository extends CrudRepository<ApplicationEntity, Integer> {
}
