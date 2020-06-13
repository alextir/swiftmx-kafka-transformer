package org.swiftmxtransformer.streams;

import lombok.Data;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Data
@Validated
public class StreamsProperties {
  @NotBlank private String applicationId;
  @NotBlank private String bootstrapServers;
  @NotBlank private String schemaRegistryUrl;
  @Valid @NestedConfigurationProperty private TopicProperties topics;
}
