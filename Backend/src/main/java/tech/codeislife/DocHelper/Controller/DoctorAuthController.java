package tech.codeislife.DocHelper.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import tech.codeislife.DocHelper.DTO.DocLoginDTO;
import tech.codeislife.DocHelper.DTO.DocRegDTO;
import tech.codeislife.DocHelper.Entity.Doctor;
import tech.codeislife.DocHelper.Entity.Patient;
import tech.codeislife.DocHelper.Service.DoctorAuthServiceImpl;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/v1/auth")

public class DoctorAuthController {


    @Autowired
    private DoctorAuthServiceImpl service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody DocRegDTO docRegDTO
    ) throws Exception {
        AuthenticationResponse response = service.registerDoctor(docRegDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody DocLoginDTO docLoginDTO
    ) throws Exception {
        return ResponseEntity.ok(service.login(docLoginDTO));

    }
    @GetMapping("/fetch_data")
    public ResponseEntity<List<Patient>> fetchData(Authentication authentication) throws Exception {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        return ResponseEntity.ok(service.fetchData(username));

    }

    @GetMapping("/fetchAll")
    public List<Doctor> fetch(){
        return service.findAll();
    }
}
