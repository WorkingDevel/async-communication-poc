package poc.async.client.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.JmsHeaders;
import org.springframework.messaging.core.MessagePostProcessor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import poc.async.client.Sender;
import poc.async.server.api.ApiClient;
import poc.async.server.api.JmsConfigurations;
import poc.async.server.api.model.Antrag;
import poc.async.server.api.model.AntragCreateResponse;

import javax.jms.JMSException;
import java.util.UUID;

@Component
public class JmsTemplateApiClient implements ApiClient {

    private static final Logger log = LoggerFactory.getLogger(Sender.class);

    private final JmsTemplate jmsTemplate;

    private final JmsMessagingTemplate jmsMessagingTemplate;

    public JmsTemplateApiClient(JmsTemplate jmsTemplate, JmsMessagingTemplate jmsMessagingTemplate) {
        this.jmsTemplate = jmsTemplate;
        this.jmsMessagingTemplate = jmsMessagingTemplate;
    }

    @Override
    public AntragCreateResponse createAntrag(Antrag antrag) throws JMSException {
        MessagePostProcessor mpp = message -> {
            message = MessageBuilder.fromMessage(message)
                    .setHeader(JmsHeaders.CORRELATION_ID, UUID.randomUUID().toString())
//                    .setHeader(JmsHeaders.REPLY_TO, JmsConfigurations.QUEUE_ANTRAG_RESPONSE) // this triggers queue creation
                    .build();
            log.info("Message to send {}: ", message);
            return message;
        };

        log.info("Sending... ");
        var response =
                jmsMessagingTemplate.convertSendAndReceive(
                        JmsConfigurations.QUEUE_ANTRAG_REQUEST,
                        antrag,
                        AntragCreateResponse.class,
                        mpp
                );

        log.info("Received {}", response);
        return response;
    }
}
