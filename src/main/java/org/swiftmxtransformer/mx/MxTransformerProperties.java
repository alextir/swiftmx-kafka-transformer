package org.swiftmxtransformer.mx;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class MxTransformerProperties {
    private Map<String, List<String>> typeToQueries;
}
