package org.swiftmxtransformer.mx;

import org.junit.jupiter.api.Test;
import org.swiftmxtransformer.model.MxType;
import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

class MxValidatorTest {

  @Test
  void apply() throws IOException, SAXException {
    // Given
    final MxType mxType =
            buildMxType();
    final Schema schema = mock(Schema.class);
    final Map<MxType, Schema> typeToSchema = Collections.singletonMap(mxType, schema);
    final Validator validator = mock(Validator.class);
    when(schema.newValidator()).thenReturn(validator);
    doNothing().when(validator).validate(any(StreamSource.class));
    final MxValidator classUnderTest = new MxValidator(typeToSchema);

    // When
    Optional<String> mx = classUnderTest.apply(mxType, "mx");

    // Then
    verify(schema).newValidator();
    verify(validator).validate(any(StreamSource.class));
    assertTrue(mx.isPresent());
    assertEquals("mx", mx.get());
  }

  @Test
  void applyFail() throws IOException, SAXException {
    // Given
    final MxType mxType =
            buildMxType();
    final Schema schema = mock(Schema.class);
    final Map<MxType, Schema> typeToSchema = Collections.singletonMap(mxType, schema);
    final Validator validator = mock(Validator.class);
    when(schema.newValidator()).thenReturn(validator);
    doThrow(IOException.class).when(validator).validate(any(StreamSource.class));
    final MxValidator classUnderTest = new MxValidator(typeToSchema);

    // When
    Optional<String> mx = classUnderTest.apply(mxType, "mx");

    // Then
    verify(schema).newValidator();
    verify(validator).validate(any(StreamSource.class));
    assertFalse(mx.isPresent());
  }

  private MxType buildMxType() {
    return MxType.newBuilder()
            .setBusinessProcess("seev")
            .setFunctionality("031")
            .setVariant("001")
            .setVersion("10")
            .build();
  }
}
