package com.voting.auth.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Value("${rabbitmq.novousuario.exchange}")
    private String exchangeNovoUsuario;

    @Value("${rabbitmq.novousuario.queue}")
    private String queueNovoUsuario;

    @Bean
    public Queue criarFilaNovoUsuarioMsNotificacao() {
        return QueueBuilder.durable(queueNovoUsuario).build();
    }

    @Bean
    public RabbitAdmin criarRabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public FanoutExchange criarFanoutNovoUsuario() {
        return ExchangeBuilder.fanoutExchange(exchangeNovoUsuario).build();
    }

    @Bean
    public Binding criarBindingNovoUsuarioMsNotificacao() {
        return BindingBuilder.bind(criarFilaNovoUsuarioMsNotificacao()).to(criarFanoutNovoUsuario());
    }

    @Bean
    public RabbitTemplate criarRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    public MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
