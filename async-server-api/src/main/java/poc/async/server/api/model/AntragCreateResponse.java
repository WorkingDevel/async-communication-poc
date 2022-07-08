package poc.async.server.api.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@RequiredArgsConstructor
public class AntragCreateResponse implements Serializable {

    @Getter
    @Setter
    @NonNull
    private Antrag antrag;
}
