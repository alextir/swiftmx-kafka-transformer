package org.swiftmxtransformer;

import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.streams.KeyValue;
import org.awaitility.Awaitility;
import org.swiftmxtransformer.streams.StreamsProperties;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
public class KafkaTestClient {

  private final StreamsProperties streamsProperties;

  public <K, V> void send(
      final ProducerRecord<K, V> record,
      final Serializer<K> keySerializer,
      final Serializer<V> valueSerializer) {
    final Map<String, Object> senderProps = new HashMap<>();
    senderProps.put(
        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, streamsProperties.getBootstrapServers());
    final Producer<K, V> producer =
        new KafkaProducer<>(senderProps, keySerializer, valueSerializer);
    producer.send(record);
  }

  public <K, V> Stream<ConsumerRecord<K, V>> receiveRecords(
      final String topic,
      final Deserializer<K> keyDeserializer,
      final Deserializer<V> valueDeserializer) {
    final Map<String, Object> consumerProps = new HashMap<>();
    consumerProps.put(
        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, streamsProperties.getBootstrapServers());
    consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupId());
    consumerProps.put(
        AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,
        streamsProperties.getSchemaRegistryUrl());
    final Consumer<K, V> consumer =
        new KafkaConsumer<>(consumerProps, keyDeserializer, valueDeserializer);
    consumer.subscribe(Collections.singleton(topic));
    ConsumerRecords<K, V> consumerRecords = consumer.poll(Duration.ofSeconds(1));
    return StreamSupport.stream(consumerRecords.spliterator(), false);
  }

  public <K, V> List<KeyValue<K, V>> receive(
      final String topic,
      final Deserializer<K> keyDeserializer,
      final Deserializer<V> valueDeserializer) {
    return receiveRecords(topic, keyDeserializer, valueDeserializer)
        .map(consumerRecord -> KeyValue.pair(consumerRecord.key(), consumerRecord.value()))
        .collect(Collectors.toList());
  }

  public <K, V> List<KeyValue<K, V>> receiveWithAwait(
      final String topic,
      final Deserializer<K> keyDeserializer,
      final Deserializer<V> valueDeserializer) {
    Awaitility.await()
        .atMost(Duration.ofSeconds(5))
        .pollInterval(Duration.ofMillis(100))
        .until(() -> receive(topic, keyDeserializer, valueDeserializer).size() > 0);
    return receive(topic, keyDeserializer, valueDeserializer);
  }

  private static String groupId() {
    return "swift-mx-" + RandomStringUtils.randomAlphabetic(10);
  }
}
