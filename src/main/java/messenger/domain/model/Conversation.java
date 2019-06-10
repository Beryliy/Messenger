package messenger.domain.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "conversation", schema = "MDB", catalog = "")
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//change to IDENTITY
    private int id;
    private String title;
}
