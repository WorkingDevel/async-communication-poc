package poc.async.server;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;
import poc.async.server.api.JmsConfigurations;
import poc.async.server.api.model.Antrag;
import poc.async.server.api.model.AntragCreateResponse;

import javax.jms.*;

@Component
public class RequestListener implements SessionAwareMessageListener<Message> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestListener.class);

    @JmsListener(destination = JmsConfigurations.QUEUE_ANTRAG_REQUEST)
    public void onMessage(Message message, Session session) throws JMSException {
        LOGGER.info("Received message {}", message);
//        Antrag order = (Antrag) ((ActiveMQObjectMessage) message).getObject();
//        AntragCreateResponse shipment = new AntragCreateResponse(order);
//
//        // done handling the request, now create a response message
//        final ObjectMessage responseMessage = new ActiveMQObjectMessage();
//        responseMessage.setJMSCorrelationID(message.getJMSCorrelationID());
//        responseMessage.setObject(shipment);
//
//        // Message sent back to the replyTo address of the income message.
//        final MessageProducer producer = session.createProducer(message.getJMSReplyTo());
//        producer.send(responseMessage);
    }
}
