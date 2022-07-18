package poc.async.server.api.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.io.Serializable;

@RequiredArgsConstructor
@NoArgsConstructor
public class AntragCreateResponse implements Serializable {

    @Getter
    @Setter
    @NonNull
    @JsonDeserialize
    private Antrag antrag;
}
