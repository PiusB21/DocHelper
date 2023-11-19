package tech.codeislife.DocHelper.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DocLoginDTO {
    String email;
    String password;
}
