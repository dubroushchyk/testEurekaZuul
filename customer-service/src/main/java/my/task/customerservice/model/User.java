package my.task.customerservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @Column(name = "sex")
    @NotNull
    @Size(min = 4, max = 5)
    private String sex;

    @Column(name = "age")
    @NotNull
    @Min(1)
    @Max(110)
    private Integer age;

    @Column(name = "purpose")
    @NotNull
    @Size(min = 4, max = 50)
    private String purpose;

    @Column(name = "additional_characteristics")
    @NotNull
    private String additionalСharacteristics;

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @JsonProperty
    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
}
