package tech.codeislife.DocHelper.Service;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import tech.codeislife.DocHelper.DTO.PatientDTO;
import tech.codeislife.DocHelper.Entity.Patient;

public interface ResourceService {
    public Patient addPatient(@RequestBody PatientDTO patientDTO,String email);

    public Patient updatePatient(@RequestBody PatientDTO patientDTO, String email);

    public String deletePatient(@RequestBody Long Id, String email);
}
