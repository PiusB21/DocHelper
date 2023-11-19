package tech.codeislife.DocHelper.Controller;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.codeislife.DocHelper.Entity.Patient;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
     private String token;
     private String email;
     private String message;
}
