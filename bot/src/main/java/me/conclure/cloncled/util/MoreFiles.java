package me.conclure.cloncled.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public interface MoreFiles {
  static void copyIfNotExistsAndCreateDirectories(InputStream inputStream, Path target) throws IOException {
    if (Files.exists(target)) {
      return;
    }

    if (inputStream == null) {
      throw new IOException(new NullPointerException());
    }

    Files.createDirectories(target.getParent());

    try (inputStream) {
      Files.copy(inputStream,target);
    }
  }
}
