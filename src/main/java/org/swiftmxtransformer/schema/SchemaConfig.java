package org.swiftmxtransformer.schema;

import lombok.extern.slf4j.Slf4j;
import org.swiftmxtransformer.model.MxType;
import org.swiftmxtransformer.mx.MxTypeMapper;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Configuration
@EnableConfigurationProperties
@Slf4j
public class SchemaConfig {

  @Bean
  @ConfigurationProperties(prefix = "schemas")
  SchemaProperties schemaProperties() {
    return new SchemaProperties();
  }

  @Bean
  Map<MxType, Schema> typeToSchema(
      final SchemaProperties schemaProperties, final MxTypeMapper mxTypeMapper) {
    final SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    return schemaProperties.getTypeToSchema().entrySet().stream()
        .map(
            entry ->
                mxTypeMapper
                    .from(entry.getKey())
                    .flatMap(
                        mxType -> {
                          Optional<AbstractMap.SimpleEntry<MxType, Schema>> optionalSimpleEntry =
                              Optional.empty();
                          try {
                            final Source schemaFile =
                                new StreamSource(entry.getValue().getInputStream());
                            final Schema schema = factory.newSchema(schemaFile);
                            optionalSimpleEntry =
                                Optional.of(new AbstractMap.SimpleEntry<>(mxType, schema));
                          } catch (IOException | SAXException e) {
                            log.warn(
                                "cannot create schema for type {}; {}",
                                entry.getKey(),
                                e.getMessage());
                          }
                          return optionalSimpleEntry;
                        }))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .collect(
            Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
  }
}
