package poc.async.client.infrastructure;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import poc.async.server.api.ApiClient;
import poc.async.server.api.JmsConfigurations;
import poc.async.server.api.model.Antrag;
import poc.async.server.api.model.AntragCreateResponse;
import poc.async.server.api.model.impl.AntragImpl;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import java.util.UUID;

@Component
public class JmsTemplateApiClient implements ApiClient {

    private final JmsTemplate jmsTemplate;

    private final JmsMessagingTemplate jmsMessagingTemplate;

    public JmsTemplateApiClient(JmsTemplate jmsTemplate, JmsMessagingTemplate jmsMessagingTemplate) {
        this.jmsTemplate = jmsTemplate;
        this.jmsMessagingTemplate = jmsMessagingTemplate;
    }

    @Override
    public AntragCreateResponse createAntrag(Antrag antrag) throws JMSException {

        ObjectMessage msg = new ActiveMQObjectMessage();
        msg.setJMSCorrelationID(UUID.randomUUID().toString());
        msg.setObject(new AntragImpl());
        jmsMessagingTemplate.convertAndSend(
                JmsConfigurations.QUEUE_ANTRAG_REQUEST, msg
        );
//        jmsTemplate.convertAndSend(JmsConfigurations.QUEUE_ANTRAG_REQUEST, "Hello World" + OffsetDateTime.now());

        return null;
    }
}
