package com.sistemabancario.notifications.config;

import com.sistemabancario.notifications.events.Event;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    private final String bootstrapAddres="localhost:9092";

    @Bean
    public DefaultKafkaConsumerFactory consumerFactory(){
        Map<String,String> props=new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddres);
        props.put(JsonSerializer.TYPE_MAPPINGS,"com.sistemabancario.notifications:package com.sistemabancario.notifications.payments.Payment");
        final JsonDeserializer <Event<?>> jsonDeserializer=
                new JsonDeserializer<>();
        return new DefaultKafkaConsumerFactory(
                props,
                new StringDeserializer(),
                jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,Event<?>>
    kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,Event<?>> factory=
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}
