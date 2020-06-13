package org.swiftmxtransformer;

import io.confluent.kafka.schemaregistry.client.MockSchemaRegistryClient;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.swiftmxtransformer.streams.MockKeyAvroSerdeSupplier;
import org.swiftmxtransformer.serde.SerdeSupplier;
import org.swiftmxtransformer.streams.StreamsProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@TestConfiguration
public class TestConfig {

  @Bean
  public NewTopic swiftMxRawTopic(final StreamsProperties streamsProperties) {
    return TopicBuilder.name(streamsProperties.getTopics().getSwiftMxInboundTopic())
        .partitions(1)
        .replicas(1)
        .build();
  }

  @Bean
  public NewTopic swiftMxEventTopic(final StreamsProperties streamsProperties) {
    return TopicBuilder.name(streamsProperties.getTopics().getSwiftMxEventTopic())
        .partitions(1)
        .replicas(1)
        .build();
  }

  @Bean
  public KafkaAdmin admin(final StreamsProperties streamsProperties) {
    final Map<String, Object> configs = new HashMap<>();
    configs.put(
        AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, streamsProperties.getBootstrapServers());
    return new KafkaAdmin(configs);
  }

  @Bean
  MockSchemaRegistryClient mockSchemaRegistryClient() {
    return new MockSchemaRegistryClient();
  }

  @Bean
  @Primary
  SerdeSupplier mockKeyAvroSerdeSupplier(
      final StreamsProperties streamsProperties,
      final MockSchemaRegistryClient mockSchemaRegistryClient) {
    Map<String, Object> serdeConfig =
        Collections.singletonMap(
            AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,
            streamsProperties.getSchemaRegistryUrl());
    return new MockKeyAvroSerdeSupplier(serdeConfig, mockSchemaRegistryClient);
  }

  @Bean
  KafkaTestClient kafkaTestClient(final StreamsProperties streamsProperties) {
    return new KafkaTestClient(streamsProperties);
  }
}
