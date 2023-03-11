package com.cs451.chadapplication.Repository;

import com.cs451.chadapplication.Entity.ApplicationEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<ApplicationEntity, String> {
    List<ApplicationEntity> findAllByClassCode(String classCode);
    List<ApplicationEntity> findAllByUmkcEmail(String umkcEmail);

    // delete by class code
    @Transactional
    void deleteAllByClassCode(String classCode);

    @Transactional
    // delete where umkcEmail and classCode
    void deleteAllByUmkcEmailAndClassCode(String umkcEmail, String classCode);
}
