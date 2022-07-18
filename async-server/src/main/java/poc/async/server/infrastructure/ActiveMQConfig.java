package poc.async.server.infrastructure;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.jms.support.converter.MessagingMessageConverter;

import javax.jms.ConnectionFactory;

@Configuration
@EnableJms
public class ActiveMQConfig {

    @Configuration(
            proxyBeanMethods = false
    )
    @ConditionalOnClass({JmsMessagingTemplate.class})
    protected static class MessagingTemplateConfiguration {
        protected MessagingTemplateConfiguration() {
        }

        @Bean
        public JmsMessagingTemplate jmsMessagingTemplate(JmsProperties properties, JmsTemplate jmsTemplate) {
            JmsMessagingTemplate messagingTemplate = new JmsMessagingTemplate(jmsTemplate);
            this.mapTemplateProperties(properties.getTemplate(), messagingTemplate);
            final var messagingMessageConverter = new MessagingMessageConverter(jmsTemplate.getMessageConverter());
            messagingTemplate.setJmsMessageConverter(messagingMessageConverter);
            return messagingTemplate;
        }

        private void mapTemplateProperties(JmsProperties.Template properties, JmsMessagingTemplate messagingTemplate) {
            PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull();
            map.from(properties::getDefaultDestination).to(messagingTemplate::setDefaultDestinationName);
        }
    }

//    @Bean
//    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
//        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
//        jmsTemplate.setMessageConverter(new MappingJackson2MessageConverter());
//        return jmsTemplate;
//    }

    @Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}