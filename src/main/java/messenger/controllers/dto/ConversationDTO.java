package messenger.controllers.dto;

import lombok.Data;
import messenger.domain.model.User;

/**
 * Created by flogiston on 10.06.19.
 */
@Data
public class ConversationDTO {
    private String title;
    private User creator;
}
