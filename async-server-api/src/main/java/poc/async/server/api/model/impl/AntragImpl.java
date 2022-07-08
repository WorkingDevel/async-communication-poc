package poc.async.server.api.model.impl;

import lombok.Setter;
import poc.async.server.api.model.Antrag;

import java.util.Optional;
import java.util.UUID;

public class AntragImpl implements Antrag {
    @Setter
    private UUID id;

    @Override
    public Optional<UUID> getId() {
        return Optional.of(id);
    }
}
