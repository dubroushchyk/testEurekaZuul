package my.task.authservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.task.authservice.Enum.EnumCharacteristics;
import my.task.authservice.Enum.EnumPurpose;
import my.task.authservice.Enum.EnumRole;
import my.task.authservice.Enum.EnumSex;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String username;

    @Column(name = "password")
    @NotNull
    @Size(min = 4, max = 100)
    private String password;

    @Column(name = "name")
    @NotNull
    @Size(min = 4, max = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    @NotNull
    private EnumSex sex;

    @Column(name = "age")
    @NotNull
    @Min(1)
    @Max(110)
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @NotNull
    private EnumRole role;

    @Enumerated(EnumType.STRING)
    @Column(name = "purpose")
    @NotNull
    private EnumPurpose purpose;

    @Enumerated(EnumType.STRING)
    @Column(name = "additional_characteristics")
    @NotNull
    private EnumCharacteristics additionalСharacteristics;

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
}
