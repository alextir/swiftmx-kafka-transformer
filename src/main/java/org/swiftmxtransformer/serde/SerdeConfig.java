package org.swiftmxtransformer.serde;

import org.swiftmxtransformer.streams.StreamsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SerdeConfig {

  @Bean
  SerdeSupplier keyAvroSerdeSupplier(final StreamsProperties streamsProperties) {
    return new KeyAvroSerdeSupplier(streamsProperties);
  }
}
