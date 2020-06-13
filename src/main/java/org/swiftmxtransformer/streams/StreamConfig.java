package org.swiftmxtransformer.streams;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.swiftmxtransformer.model.MxType;
import org.swiftmxtransformer.mx.MxTransformer;
import org.swiftmxtransformer.mx.MxTypeExtractor;
import org.swiftmxtransformer.mx.MxValidator;
import org.swiftmxtransformer.serde.SerdeSupplier;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Configuration
public class StreamConfig {

  @Bean
  FactoryBean<StreamsBuilder> streamsBuilder(
      final KafkaStreamsConfiguration kafkaStreamsConfiguration) {
    return new StreamsBuilderFactoryBean(kafkaStreamsConfiguration);
  }

  @Bean
  KafkaStreamsConfiguration kafkaStreamsConfiguration(final StreamsProperties streamsProperties) {
    final Map<String, Object> properties = new HashMap<>();
    properties.put(StreamsConfig.APPLICATION_ID_CONFIG, streamsProperties.getApplicationId());
    properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, streamsProperties.getBootstrapServers());
    return new KafkaStreamsConfiguration(properties);
  }

  @Bean
  KStream<MxType, String> transformerStream(
      final StreamsBuilder streamsBuilder,
      final StreamsProperties streamsProperties,
      final SerdeSupplier serdeSupplier,
      final MxTypeExtractor mxTypeExtractor,
      final MxValidator mxValidator,
      final MxTransformer mxTransformer) {
    return streamsBuilder.stream(
            streamsProperties.getTopics().getSwiftMxInboundTopic(),
            Consumed.with(Serdes.String(), Serdes.String()))
        .map((key, value) -> KeyValue.pair(mxTypeExtractor.extractType(value), value))
        .mapValues(mxValidator::apply)
        .filter((key, value) -> value.isPresent())
        .mapValues(Optional::get)
        .mapValues(mxTransformer)
        .through(
            streamsProperties.getTopics().getSwiftMxEventTopic(),
            Produced.with(serdeSupplier.get(), Serdes.String()));
  }

  @Bean
  @ConfigurationProperties(prefix = "streams")
  StreamsProperties streamsProperties() {
    return new StreamsProperties();
  }
}
