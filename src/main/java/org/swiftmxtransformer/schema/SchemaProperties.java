package org.swiftmxtransformer.schema;

import lombok.Data;
import org.springframework.core.io.Resource;

import java.util.Map;

@Data
public class SchemaProperties {
  private Map<String, Resource> typeToSchema;
}
