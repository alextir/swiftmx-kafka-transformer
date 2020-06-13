package org.swiftmxtransformer.serde;

import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.subject.RecordNameStrategy;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import lombok.RequiredArgsConstructor;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.common.serialization.Serde;
import org.swiftmxtransformer.streams.StreamsProperties;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class KeyAvroSerdeSupplier implements SerdeSupplier {
  private final StreamsProperties streamsProperties;

  @Override
  public <T extends SpecificRecord> Serde<T> get() {
    final Map<String, Object> serdeConfig = new HashMap<>();
    serdeConfig.put(
        AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,
        streamsProperties.getSchemaRegistryUrl());
    serdeConfig.put(
        AbstractKafkaAvroSerDeConfig.VALUE_SUBJECT_NAME_STRATEGY, RecordNameStrategy.class);
    final Serde<T> valueSpecificAvroSerde = new SpecificAvroSerde<>();
    valueSpecificAvroSerde.configure(serdeConfig, true);
    return valueSpecificAvroSerde;
  }
}
