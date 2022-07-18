package poc.async.server.api.model.impl;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import poc.async.server.api.model.Antrag;

import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor
public class AntragImpl implements Antrag {
    @Setter
    private UUID id;

    public AntragImpl(UUID randomUUID) {
        id=randomUUID;
    }

    @Override
    public UUID getId() {
        return id;
    }
}
