package tech.codeislife.DocHelper.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    private String ptName;
    private String gender;
    private String ptEmail;
    private String ptPhoneNo;
}
