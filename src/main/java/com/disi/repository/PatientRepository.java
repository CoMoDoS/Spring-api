package com.disi.repository;

import com.disi.models.Patient;
import com.disi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Optional<Patient> findByName(String name);
    Patient findByUser(User user);
}