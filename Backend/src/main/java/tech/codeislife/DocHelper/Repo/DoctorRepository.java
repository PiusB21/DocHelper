package tech.codeislife.DocHelper.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import tech.codeislife.DocHelper.Entity.Doctor;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    Optional<Doctor> findByEmail(String email);
}
