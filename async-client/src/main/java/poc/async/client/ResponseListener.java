package poc.async.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import poc.async.server.api.JmsConfigurations;
import poc.async.server.api.model.AntragCreateResponse;

import javax.jms.JMSException;
import javax.jms.Session;

//@Component
public class ResponseListener {

    private static final Logger log = LoggerFactory.getLogger(ResponseListener.class);

//    @JmsListener(destination = JmsConfigurations.QUEUE_ANTRAG_RESPONSE)
    public void onMessage(Message<AntragCreateResponse> message, Session session) throws JMSException {
        log.info("Received response message {} ## {}", message.getHeaders(), message);

    }
}
