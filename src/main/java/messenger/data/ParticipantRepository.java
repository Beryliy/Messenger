package messenger.data;

import messenger.domain.model.Participant;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by flogiston on 10.06.19.
 */
public interface ParticipantRepository extends CrudRepository<Participant, Integer> {
    Participant save(Participant participant);
}
