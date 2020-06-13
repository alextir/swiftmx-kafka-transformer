package org.swiftmxtransformer.streams;

import lombok.SneakyThrows;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import org.swiftmxtransformer.KafkaTestClient;
import org.swiftmxtransformer.TestConfig;
import org.swiftmxtransformer.Utils;
import org.swiftmxtransformer.model.MxType;
import org.swiftmxtransformer.serde.SerdeSupplier;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("local")
@SpringBootTest(
    classes = TestConfig.class,
    properties = {"streams.bootstrap-servers=127.0.0.1:9097"})
@EmbeddedKafka(partitions = 1, ports = 9097)
public class FlowIntegrationTest {

  private final StreamsProperties streamsProperties;
  private final FactoryBean<StreamsBuilder> streamsBuilder;
  private final SerdeSupplier mockKeyAvroSerdeSupplier;
  private final KafkaTestClient kafkaTestClient;

  @Autowired
  public FlowIntegrationTest(
      StreamsProperties streamsProperties,
      FactoryBean<StreamsBuilder> streamsBuilder,
      SerdeSupplier mockKeyAvroSerdeSupplier,
      KafkaTestClient kafkaTestClient) {
    this.streamsProperties = streamsProperties;
    this.streamsBuilder = streamsBuilder;
    this.mockKeyAvroSerdeSupplier = mockKeyAvroSerdeSupplier;
    this.kafkaTestClient = kafkaTestClient;
  }

  @BeforeEach
  @SneakyThrows
  public void init() {
    final StreamsBuilderFactoryBean streamsBuilderFactoryBean =
        (StreamsBuilderFactoryBean) streamsBuilder;
    waitKafka(streamsBuilderFactoryBean);
  }

  @Test
  public void test() {
    // Given
    final String inputMx = Utils.readFileAsString("seev.031.001.10.xml");
    final String expectedMx = Utils.readFileAsString("seev.031.001.10-transformed.xml");

    // When
    kafkaTestClient.send(
        new ProducerRecord<>(
            streamsProperties.getTopics().getSwiftMxInboundTopic(), "1234bbb", inputMx),
        Serdes.String().serializer(),
        Serdes.String().serializer());

    // Then
    final List<KeyValue<MxType, String>> keyValues =
        kafkaTestClient.receiveWithAwait(
            streamsProperties.getTopics().getSwiftMxEventTopic(),
            mockKeyAvroSerdeSupplier.<MxType>get().deserializer(),
            Serdes.String().deserializer());

    assertNotNull(keyValues);
    assertThat(keyValues.size()).isEqualTo(1);
    assertThat(keyValues.iterator().next().value).isEqualTo(expectedMx);
  }

  private void waitKafka(StreamsBuilderFactoryBean streamsBuilderFactoryBean) {
    Awaitility.await()
        .atMost(Duration.ofSeconds(15))
        .pollDelay(Duration.ofMillis(100))
        .until(streamsBuilderFactoryBean::isRunning);
  }
}
