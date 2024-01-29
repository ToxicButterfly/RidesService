package com.example.ridesservice.config.kafka;

import com.example.ridesservice.dto.request.DriverRequest;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class RidesProducerConfig {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String servers;
    @Value("${kafka.partitions.count}")
    private int PARTITIONS_COUNT;
    @Value("${kafka.replicas.count}")
    private int REPLICAS_COUNT;
    @Value("${topic.name.driver}")
    private String driverTopic;
    @Value("${topic.name.payment}")
    private String paymentTopic;
    @Value("${topic.name.rating}")
    private String ratingTopic;

    @Bean
    public NewTopic DriverTopic() {
        return TopicBuilder.name(driverTopic)
                .partitions(PARTITIONS_COUNT)
                .replicas(REPLICAS_COUNT)
                .build();
    }

    @Bean
    public NewTopic PaymentTopic() {
        return TopicBuilder.name(paymentTopic)
                .partitions(PARTITIONS_COUNT)
                .replicas(REPLICAS_COUNT)
                .build();
    }

    @Bean
    public NewTopic RatingTopic() {
        return TopicBuilder.name(ratingTopic)
                .partitions(5)
                .replicas(1)
                .build();
    }

//    @Bean
//    public ProducerFactory<String, Object> producerFactory() {
//        Map<String, Object> props = Map.of(
//                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers,
//                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
//                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class,
//                JsonSerializer.ADD_TYPE_INFO_HEADERS, false,
//                JsonSerializer.TYPE_MAPPINGS, "driverRequest:" + DriverRequest.class
//        );
//
//        return new DefaultKafkaProducerFactory<>(props);
//    }
//
//    @Bean
//    public KafkaTemplate<String, Object> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }

}
