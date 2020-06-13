package org.swiftmxtransformer.mx;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.swiftmxtransformer.model.MxType;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

@RequiredArgsConstructor
@Slf4j
public class MxValidator implements BiFunction<MxType, String, Optional<String>> {

  private final Map<MxType, Schema> typeToSchema;

  @Override
  @SneakyThrows
  public Optional<String> apply(final MxType mxType, final String mx) {
    Optional<String> validMx = Optional.empty();
    final Schema schema = typeToSchema.get(mxType);
    final Validator validator = schema.newValidator();
    final InputStream inputStream = new ByteArrayInputStream(mx.getBytes());
    try {
      validator.validate(new StreamSource(inputStream));
      validMx = Optional.of(mx);
    } catch (IOException e) {
      log.warn("invalid mx: {}", mx);
    }
    return validMx;
  }
}
