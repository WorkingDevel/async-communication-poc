package poc.async.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.support.JmsHeaders;
import org.springframework.messaging.core.MessagePostProcessor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import poc.async.server.api.JmsConfigurations;
import poc.async.server.api.model.Antrag;
import poc.async.server.api.model.AntragCreateResponse;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Session;
import java.util.Map;

@Component
public class RequestListener {

    private static final Logger log = LoggerFactory.getLogger(RequestListener.class);

    private final JmsMessagingTemplate jmsTemplate;
    MessagePostProcessor mpp = message -> {
        message = MessageBuilder
                .fromMessage(message)
                .setHeader(JmsHeaders.CORRELATION_ID, message.getHeaders().get(JmsHeaders.CORRELATION_ID))
//                .setHeader(JmsHeaders.REPLY_TO, JmsConfigurations.QUEUE_ANTRAG_RESPONSE) // this triggers queue creation
                .build();
        return message;
    };

    public RequestListener(JmsMessagingTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @JmsListener(destination = JmsConfigurations.QUEUE_ANTRAG_REQUEST)
    public void onMessage(org.springframework.messaging.Message<Antrag> message, Session session) throws JMSException {
        log.info("Received message {}, headers: {}", message.getPayload(), message.getHeaders());

        Antrag antrag = message.getPayload();
        AntragCreateResponse response = new AntragCreateResponse(antrag);

        jmsTemplate.convertAndSend(
                ((Queue) message.getHeaders().get(JmsHeaders.REPLY_TO)).getQueueName(),
                response,
                Map.of(JmsHeaders.CORRELATION_ID, message.getHeaders().getOrDefault(JmsHeaders.CORRELATION_ID, "unknown"))
//                mpp
        );
    }
}
