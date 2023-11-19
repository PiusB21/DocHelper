package tech.codeislife.DocHelper.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocRegDTO {
    private String docName;
    private String email;
    private String password;
}

