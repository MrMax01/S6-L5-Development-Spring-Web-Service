
package massimomauro.S6L5DevelopmentSpringWebService.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String avatarURL;
    @OneToMany(mappedBy = "user")
    private List<Device> listDevice ;

}
