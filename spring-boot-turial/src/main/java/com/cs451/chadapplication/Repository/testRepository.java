package com.cs451.chadapplication.Repository;

import com.cs451.chadapplication.Entity.testEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface testRepository extends CrudRepository<testEntity, String> {
//    public String findByPdfID(String pdfId);
}
