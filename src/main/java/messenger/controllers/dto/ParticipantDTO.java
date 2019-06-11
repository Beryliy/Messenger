package messenger.controllers.dto;

import lombok.Data;
import messenger.domain.model.Conversation;
import messenger.domain.model.Role;
import messenger.domain.model.User;

import java.util.Set;

/**
 * Created by flogiston on 11.06.19.
 */
@Data
public class ParticipantDTO {
    private String username;
    private Conversation conversation;
    private User user;
    private Set<Role> roles;
}
