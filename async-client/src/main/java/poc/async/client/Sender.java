package poc.async.client;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import poc.async.client.infrastructure.JmsTemplateApiClient;
import poc.async.server.api.JmsConfigurations;
import poc.async.server.api.model.impl.AntragImpl;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import java.util.UUID;

@Component
public class Sender {

    private final JmsTemplateApiClient client;

    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    public Sender(JmsTemplateApiClient client) {
        this.client = client;
    }


    @Scheduled(fixedDelay = 5000L)
    public void sendContinuously() throws JMSException {
        LOGGER.info("Sending");
    }
}