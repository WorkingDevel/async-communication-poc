package poc.async.client;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import poc.async.server.api.JmsConfigurations;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Component
public class ResponseListener {

    @JmsListener(destination = JmsConfigurations.QUEUE_ANTRAG_RESPONSE)
    public void onMessage(Message message, Session session) throws JMSException {

    }
}
