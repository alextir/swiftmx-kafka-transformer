package org.swiftmxtransformer.mx;

import org.junit.jupiter.api.Test;
import org.swiftmxtransformer.model.MxType;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MxTypeMapperTest {

  @Test
  void fromKnown() {
    // Given
    final MxTypeMapper classUnderTest = new MxTypeMapper();

    // When
    final Optional<MxType> mxType = classUnderTest.from("seev.031.001.10");

    // Then
    assertTrue(mxType.isPresent());
    assertEquals("seev", mxType.get().getBusinessProcess());
    assertEquals("031", mxType.get().getFunctionality());
    assertEquals("001", mxType.get().getVariant());
    assertEquals("10", mxType.get().getVersion());
  }

  @Test
  void fromRandom() {
    // Given
    final MxTypeMapper classUnderTest = new MxTypeMapper();

    // When
    final Optional<MxType> mxType = classUnderTest.from(UUID.randomUUID().toString());

    // Then
    assertFalse(mxType.isPresent());
  }
}
