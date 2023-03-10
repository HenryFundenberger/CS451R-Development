package com.cs451.chadapplication.Repository;

import com.cs451.chadapplication.Entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {
}
