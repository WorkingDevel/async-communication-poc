package poc.async.server.api;

import poc.async.server.api.model.Antrag;
import poc.async.server.api.model.AntragCreateResponse;

import javax.jms.JMSException;

public interface ApiClient {
    AntragCreateResponse createAntrag(Antrag antrag) throws JMSException;
}
