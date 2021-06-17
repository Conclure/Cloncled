package me.conclure.concled;

import joptsimple.OptionSet;

import me.conclure.concled.util.URIHelper;

import java.net.URI;
import java.nio.file.Path;

public record Options(Path bootstrapPath, Path configPath) {
  static Options from(OptionSet set) {
    Path bootstrapPath = Options.mapPath(set,"bootstrap",Path.of("bootstrap.yml"));
    Path configPath = Options.mapPath(set,"config",Path.of("config.yml"));
    return new Options(bootstrapPath,configPath);
  }

  static Path mapPath(OptionSet set, String option, Path def) {
    return set.valueOfOptional(option)
        .filter(String.class::isInstance)
        .map(String.class::cast)
        .map(URIHelper::create)
        .map(URI::getPath)
        .map(Path::of)
        .map(Path::toAbsolutePath)
        .orElse(def);
  }

  @Override
  public String toString() {
    return "Options{" +
        "bootstrapPath=" + this.bootstrapPath +
        ", configPath=" + this.configPath +
        '}';
  }
}
