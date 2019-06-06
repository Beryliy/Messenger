package messenger.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "conversation", schema = "MDB", catalog = "")
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//change to IDENTITY
    private int id;
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_id")
    private User creator;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}
