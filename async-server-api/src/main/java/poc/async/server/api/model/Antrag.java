package poc.async.server.api.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import poc.async.server.api.model.impl.AntragImpl;

import java.io.Serializable;

@JsonDeserialize(as = AntragImpl.class)
public interface Antrag extends Identifiable, Serializable {

}
