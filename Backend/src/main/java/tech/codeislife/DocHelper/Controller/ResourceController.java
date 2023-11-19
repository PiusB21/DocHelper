package tech.codeislife.DocHelper.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import tech.codeislife.DocHelper.DTO.PatientDTO;
import tech.codeislife.DocHelper.Entity.Patient;
import tech.codeislife.DocHelper.Service.ResourceService;
import tech.codeislife.DocHelper.Service.ResourceServiceImpl;

//@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("api/v1/demo")
public class ResourceController {

    @Autowired
    private ResourceServiceImpl service;

    @GetMapping("/mambo")
    public ResponseEntity<String> sayHello()  {
        return ResponseEntity.ok("Hello from the secured endpoint");
    }

    @PostMapping("/add")
    public ResponseEntity<Patient> addPatient(@RequestBody PatientDTO patientDTO, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

       Patient addedPatient=  service.addPatient(patientDTO,username);

        return new ResponseEntity<>(addedPatient, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Patient> updatePatient(@RequestBody PatientDTO patientDTO, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        Patient addedPatient=  service.updatePatient(patientDTO,username);

        return new ResponseEntity<>(addedPatient, HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        String feedback=  service.deletePatient(id, username);

        return new ResponseEntity<>(feedback, HttpStatus.OK);
    }

}
