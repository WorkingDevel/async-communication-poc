package poc.async.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import poc.async.client.infrastructure.JmsTemplateApiClient;
import poc.async.server.api.model.impl.AntragImpl;

import javax.jms.JMSException;
import java.util.UUID;

@Component
public class Sender {

    private final JmsTemplateApiClient client;

    private static final Logger log = LoggerFactory.getLogger(Sender.class);

    public Sender(JmsTemplateApiClient client) {
        this.client = client;
    }


    @Scheduled(fixedDelay = 10000L)
    public void sendContinuously() throws JMSException {
        log.info("Sending");
        client.createAntrag(new AntragImpl(UUID.randomUUID()));
    }
}