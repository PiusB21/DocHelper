package tech.codeislife.DocHelper.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="patients")
public class Patient {

    @Id
    @GeneratedValue
    private Long pid;
    private String ptName;
    private String gender;
    private String ptEmail;
    private String ptPhoneNo;

}
