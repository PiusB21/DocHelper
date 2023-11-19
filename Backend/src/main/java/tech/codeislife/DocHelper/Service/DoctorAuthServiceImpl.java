package tech.codeislife.DocHelper.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import tech.codeislife.DocHelper.Controller.AuthenticationResponse;
import tech.codeislife.DocHelper.DTO.DocLoginDTO;
import tech.codeislife.DocHelper.DTO.DocRegDTO;
import tech.codeislife.DocHelper.DTO.PatientDTO;
import tech.codeislife.DocHelper.Entity.Doctor;
import tech.codeislife.DocHelper.Entity.Patient;
import tech.codeislife.DocHelper.Entity.Role;
import tech.codeislife.DocHelper.Repo.DoctorRepository;
import tech.codeislife.DocHelper.config.JwtService;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorAuthServiceImpl implements DoctorAuthService {

    @Autowired
    private  DoctorRepository doctorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;



    @Override
    public AuthenticationResponse registerDoctor(@RequestBody DocRegDTO docRegDTO) throws Exception {
        if((doctorRepository.findByEmail(docRegDTO.getEmail())).isPresent()){
            throw new Exception("USER ALREADY EXISTS");
        };
        var doctor= Doctor.builder()
                .email(docRegDTO.getEmail())
                .docName(docRegDTO.getDocName())
                .password(passwordEncoder.encode(docRegDTO.getPassword()))
                .role(Role.USER)
                .build();

            doctorRepository.save(doctor);

             var jwtToken = jwtService.generateToken(doctor);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .email(doctor.getEmail())
                .message("successfully registered ")
                .build();

       // return doctor.getDocName();
    }


    @Override
    public AuthenticationResponse login(@RequestBody DocLoginDTO docLoginDTO) throws Exception {
        System.out.println("REACHES HERE 1");
        try{
            Authentication authentication= authenticationManager.authenticate(

                    new UsernamePasswordAuthenticationToken(
                            docLoginDTO.getEmail(),
                            docLoginDTO.getPassword()
                    )
            );
        }
        catch (Exception e){
            System.out.println("An exception occurred: " + e.getMessage());
            return null;
        }
           Authentication authentication= authenticationManager.authenticate(

                    new UsernamePasswordAuthenticationToken(
                            docLoginDTO.getEmail(),
                            docLoginDTO.getPassword()
                    )
            );

           if(authentication.isAuthenticated()){
               System.out.println("REACHES HERE 2");
           }
           else {
               throw new UsernameNotFoundException("invalid user request !");

           }
            System.out.println("REACHES HERE 2");



        var doctor = doctorRepository.findByEmail(docLoginDTO.getEmail())
                .orElseThrow();


        System.out.println("REACHES HERE 3");
        var jwtToken = jwtService.generateToken(doctor);

        var emailLogged = doctor.getEmail();
        return  AuthenticationResponse.builder()
                .token(jwtToken)
                .email(emailLogged)
                .message("successfully logged in")
                .build();
    }

    @Override //checks the JWT token for authorization of the request
    public List<Patient> fetchData(String email) throws Exception{
        Optional<Doctor> doctor = doctorRepository.findByEmail(email);

        if (doctor.isPresent()){
            return doctor.get().getPatients();
        }
        else throw  new Exception("no such user");

    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

}
