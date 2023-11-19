package tech.codeislife.DocHelper.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import tech.codeislife.DocHelper.Controller.AuthenticationResponse;
import tech.codeislife.DocHelper.DTO.DocLoginDTO;
import tech.codeislife.DocHelper.DTO.DocRegDTO;
import tech.codeislife.DocHelper.DTO.PatientDTO;
import tech.codeislife.DocHelper.Entity.Doctor;
import tech.codeislife.DocHelper.Entity.Patient;

import java.util.List;
import org.springframework.security.core.Authentication;


public interface DoctorAuthService {

    public AuthenticationResponse registerDoctor(@RequestBody DocRegDTO docRegDTO) throws Exception;
    public AuthenticationResponse login(@RequestBody DocLoginDTO docLoginDTO) throws Exception;

    public List<Patient> fetchData( String email) throws Exception;

    public List<Doctor> findAll();


}
