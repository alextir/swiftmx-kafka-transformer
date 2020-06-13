package org.swiftmxtransformer.serde;

import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.common.serialization.Serde;

public interface SerdeSupplier {
  <T extends SpecificRecord> Serde<T> get();
}
