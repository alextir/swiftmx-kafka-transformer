package org.swiftmxtransformer.streams;

import io.confluent.kafka.schemaregistry.client.MockSchemaRegistryClient;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import lombok.RequiredArgsConstructor;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.swiftmxtransformer.serde.SerdeSupplier;

import java.util.Map;

@RequiredArgsConstructor
public class MockKeyAvroSerdeSupplier implements SerdeSupplier {
  private final Map<String, Object> serdeConfig;
  private final MockSchemaRegistryClient mockSchemaRegistryClient;

  @Override
  public <T extends SpecificRecord> Serde<T> get() {
    final SpecificAvroSerde<T> specificAvroSerde =
        new SpecificAvroSerde<>(mockSchemaRegistryClient);
    specificAvroSerde.configure(serdeConfig, true);
    return Serdes.serdeFrom(specificAvroSerde.serializer(), specificAvroSerde.deserializer());
  }
}
