package org.swiftmxtransformer.mx;

import com.prowidesoftware.swift.io.parser.MxParser;
import com.prowidesoftware.swift.model.MxId;
import org.swiftmxtransformer.model.MxType;

public class MxTypeExtractor {

  public MxType extractType(final String swiftMx) {
    final MxParser mxParser = new MxParser(swiftMx);
    final MxId mxId = mxParser.detectMessage();
    return MxType.newBuilder()
        .setBusinessProcess(mxId.getBusinessProcess().name())
        .setFunctionality(mxId.getFunctionality())
        .setVariant(mxId.getVariant())
        .setVersion(mxId.getVersion())
        .build();
  }
}
