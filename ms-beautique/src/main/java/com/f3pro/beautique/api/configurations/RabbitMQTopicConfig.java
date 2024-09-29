package com.f3pro.beautique.api.configurations;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQTopicConfig {

    @Value("${rabbitmq.exchange.name:beautiqueExchange}")
    public String   exchangeName; // Nome do exchange, pode ser configurado externamente

    /**
     * Cria o exchange do tipo Topic com o nome definido.
     * @return TopicExchange
     */
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    /**
     * Cria a fila "customerQueue" e a torna durável.
     * @return Queue
     */
    @Bean
    public Queue customerQueue() {
        return createQueue("customerQueue");
    }

    /**
     * Cria a fila "beautyProcedureQueue" e a torna durável.
     * @return Queue
     */
    @Bean
    public Queue beautyProcedureQueue() {
        return createQueue("beautyProcedureQueue");
    }

    /**
     * Cria a fila "appointmentQueue" e a torna durável.
     * @return Queue
     */
    @Bean
    public Queue appointmentQueue() {
        return createQueue("appointmentQueue");
    }

    /**
     * Faz o binding da fila "customerQueue" com o exchange usando a chave de roteamento "customer.#".
     * @param customerQueue Fila a ser ligada
     * @param exchange Exchange a ser usado
     * @return Binding
     */
    @Bean
    public Binding bindingCustomer(Queue customerQueue, TopicExchange exchange) {
        return createBinding(customerQueue, exchange, "customer.#");
    }

    /**
     * Faz o binding da fila "beautyProcedureQueue" com o exchange usando a chave de roteamento "beautyProcedures.#".
     * @param beautyProcedureQueue Fila a ser ligada
     * @param exchange Exchange a ser usado
     * @return Binding
     */
    @Bean
    public Binding bindingBeautyProcedure(Queue beautyProcedureQueue, TopicExchange exchange) {
        return createBinding(beautyProcedureQueue, exchange, "beautyProcedures.#");
    }

    /**
     * Faz o binding da fila "appointmentQueue" com o exchange usando a chave de roteamento "appointments.#".
     * @param appointmentQueue Fila a ser ligada
     * @param exchange Exchange a ser usado
     * @return Binding
     */
    @Bean
    public Binding bindingAppointment(Queue appointmentQueue, TopicExchange exchange) {
        return createBinding(appointmentQueue, exchange, "appointments.#");
    }

    /**
     * Método auxiliar para criar filas duráveis.
     * @param queueName Nome da fila
     * @return Queue
     */
    private Queue createQueue(String queueName) {
        return new Queue(queueName, true);
    }

    /**
     * Método auxiliar para criar bindings entre filas e exchanges.
     * @param queue Fila a ser ligada
     * @param exchange Exchange a ser usado
     * @param routingKey Chave de roteamento
     * @return Binding
     */
    private Binding createBinding(Queue queue, TopicExchange exchange, String routingKey) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }
}