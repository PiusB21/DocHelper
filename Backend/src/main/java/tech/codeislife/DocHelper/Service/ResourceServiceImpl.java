package tech.codeislife.DocHelper.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import tech.codeislife.DocHelper.DTO.PatientDTO;
import tech.codeislife.DocHelper.Entity.Doctor;
import tech.codeislife.DocHelper.Entity.Patient;
import tech.codeislife.DocHelper.Repo.DoctorRepository;
import tech.codeislife.DocHelper.Repo.PatientRepository;

import java.util.Optional;

@Service
public class ResourceServiceImpl implements ResourceService{

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Patient addPatient(@RequestBody PatientDTO patientDTO, String email) {

        Optional<Doctor> optionalDoctor = doctorRepository.findByEmail(email);

        Doctor doctor = optionalDoctor.get();


        var patient= Patient.builder()

                .gender(patientDTO.getGender())
                .ptEmail(patientDTO.getPtEmail())
                .ptName(patientDTO.getPtName())
                .ptPhoneNo(patientDTO.getPtPhoneNo())
                .build();

        doctor.getPatients().add(patient);


        patientRepository.save(patient);
        //doctorRepository.save(doctor);

        return patient;
    }

    @Override
    public Patient updatePatient(@RequestBody PatientDTO patientDTO, String email) {
        Optional<Doctor> optionalDoctor = doctorRepository.findByEmail(email);

        Doctor doctor = optionalDoctor.get();

        Optional<Patient> optpatient = patientRepository.findByPtEmail(patientDTO.getPtEmail());

        if(optpatient.isEmpty()){
            return null;
        }

        Patient patient =optpatient.get();
        patient.setPtName(patientDTO.getPtName());
        patient.setPtEmail(patientDTO.getPtEmail());
        patient.setGender(patientDTO.getGender());
        patient.setPtPhoneNo(patientDTO.getPtPhoneNo());

        patientRepository.save(patient);

        return patient;
    }

    @Override
    public String deletePatient( @RequestBody Long Id, String email) {
        var patient = patientRepository.findById(Id);
        patientRepository.deleteById(Id);

        return "Successfully deleted "+patient.get().getPtName();
    }
}
