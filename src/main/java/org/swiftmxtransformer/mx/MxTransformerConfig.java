package org.swiftmxtransformer.mx;

import org.swiftmxtransformer.model.MxType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Configuration
public class MxTransformerConfig {

  @Bean
  @ConfigurationProperties(prefix = "transformer")
  MxTransformerProperties transformerProperties() {
    return new MxTransformerProperties();
  }

  @Bean
  Map<MxType, List<String>> typeToQueries(
          final MxTransformerProperties mxTransformerProperties, final MxTypeMapper mxTypeMapper) {
    return mxTransformerProperties.getTypeToQueries().entrySet().stream()
        .map(
            entry ->
                mxTypeMapper
                    .from(entry.getKey())
                    .map(mxType -> new AbstractMap.SimpleEntry<>(mxType, entry.getValue())))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .collect(
            Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
  }

  @Bean
  MxTransformer mxTransformer(
      final Map<MxType, List<String>> typeToQueries,
      final DocumentBuilderFactory documentBuilderFactory,
      final TransformerFactory transformerFactory) {
    return new MxTransformer(typeToQueries, documentBuilderFactory, transformerFactory);
  }

  @Bean
  DocumentBuilderFactory documentBuilderFactory() {
    return DocumentBuilderFactory.newInstance();
  }

  @Bean
  TransformerFactory transformerFactory() {
    return TransformerFactory.newInstance();
  }
}
