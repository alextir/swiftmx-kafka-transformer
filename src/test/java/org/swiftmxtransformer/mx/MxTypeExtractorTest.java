package org.swiftmxtransformer.mx;

import org.junit.jupiter.api.Test;
import org.swiftmxtransformer.Utils;
import org.swiftmxtransformer.model.MxType;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MxTypeExtractorTest {

  @Test
  void extractType() {
    // Given
    final MxTypeExtractor classUnderTest = new MxTypeExtractor();

    // When
    final String message =
        Utils.readFileAsString("seev.031.001.10.xml");

    // Then
    final MxType mxType = classUnderTest.extractType(message);
    assertEquals("seev", mxType.getBusinessProcess());
    assertEquals("031", mxType.getFunctionality());
    assertEquals("001", mxType.getVariant());
    assertEquals("10", mxType.getVersion());
  }
}
