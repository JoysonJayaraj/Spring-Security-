package com.te.jspiders.repository;

import com.te.jspiders.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer,String> {
}
