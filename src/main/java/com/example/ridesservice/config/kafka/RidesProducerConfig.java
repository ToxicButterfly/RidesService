package com.example.ridesservice.config.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

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
}
