package tech.codeislife.DocHelper.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.codeislife.DocHelper.Entity.Doctor;
import tech.codeislife.DocHelper.Entity.Patient;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    Optional<Patient> findByPtEmail(String email);
}
