package poc.async.server.api.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;

import java.util.Optional;
import java.util.UUID;

public interface Identifiable {
    @JsonSerialize(using= UUIDSerializer.class)
//    @JsonDeserialize(using=UUDIDeserializer.class)
    UUID getId();
}
