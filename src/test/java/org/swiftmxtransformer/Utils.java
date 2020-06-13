package org.swiftmxtransformer;

import lombok.SneakyThrows;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {
  @SneakyThrows
  public static String readFileAsString(final String filePath, final Charset encoding) {
    final byte[] bytes =
        Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(filePath).toURI()));
    return new String(bytes, encoding);
  }

  @SneakyThrows
  public static String readFileAsString(final String filePath) {
    return readFileAsString(filePath, StandardCharsets.UTF_8);
  }
}
