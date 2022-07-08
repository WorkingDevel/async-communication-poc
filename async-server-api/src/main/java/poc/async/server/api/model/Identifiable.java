package poc.async.server.api.model;

import java.util.Optional;
import java.util.UUID;

public interface Identifiable {
    Optional<UUID> getId();
}
