package org.swiftmxtransformer.mx;

import org.swiftmxtransformer.model.MxType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.validation.Schema;
import java.util.Map;

@Configuration
public class MxConfig {

  @Bean
  MxTypeMapper mxTypeMapper() {
    return new MxTypeMapper();
  }

  @Bean
  MxTypeExtractor mxTypeExtractor() {
    return new MxTypeExtractor();
  }

  @Bean
  MxValidator mxValidator(final Map<MxType, Schema> typeToSchema) {
    return new MxValidator(typeToSchema);
  }
}
