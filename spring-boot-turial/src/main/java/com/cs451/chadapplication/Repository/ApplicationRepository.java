package com.cs451.chadapplication.Repository;

import com.cs451.chadapplication.Entity.ApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<ApplicationEntity, String> {
    List<ApplicationEntity> findAllByClassCode(String classCode);
    List<ApplicationEntity> findAllByUmkcEmail(String umkcEmail);
}
