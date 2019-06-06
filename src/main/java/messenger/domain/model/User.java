package messenger.domain.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user", schema = "MDB")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String login;
    private String password;
    private boolean acctive;
}
