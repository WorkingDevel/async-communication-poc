package poc.async.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import poc.async.server.api.JmsConfigurations;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Component
public class ResponseListener {

    private static final Logger log = LoggerFactory.getLogger(ResponseListener.class);

    @JmsListener(destination = JmsConfigurations.QUEUE_ANTRAG_RESPONSE)
    public void onMessage(Message message, Session session) throws JMSException {
        log.info("Received response message {}", message);

    }
}
