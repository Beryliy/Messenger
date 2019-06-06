package messenger.domain.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by flogiston on 01.06.19.
 */

@Data
@Entity
@Table(name = "contact", schema = "MDB")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_owner_id")
    private User userOwner;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_contact_id")
    private User userContact;
}
