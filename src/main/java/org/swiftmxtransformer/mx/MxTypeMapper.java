package org.swiftmxtransformer.mx;

import lombok.extern.slf4j.Slf4j;
import org.swiftmxtransformer.model.MxType;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class MxTypeMapper {

  static final Pattern pattern = Pattern.compile("([a-zA-Z]{4}).(\\d{3}).(\\d{3}).(\\d{2})");

  public Optional<MxType> from(final String type) {
    Optional<MxType> mxType = Optional.empty();
    final Matcher matcher = pattern.matcher(type);
    if (matcher.matches()) {
      mxType =
          Optional.of(
              MxType.newBuilder()
                  .setBusinessProcess(matcher.group(1))
                  .setFunctionality(matcher.group(2))
                  .setVariant(matcher.group(3))
                  .setVersion(matcher.group(4))
                  .build());
    } else {
      log.warn("cannot match mx type: {}", type);
    }
    return mxType;
  }
}
