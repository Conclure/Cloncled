package me.conclure.cloncled;

import joptsimple.OptionSet;

import me.conclure.cloncled.util.URIStaticFactory;

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
        .map(URIStaticFactory::create)
        .map(URI::getPath)
        .map(Path::of)
        .orElse(def)
        .toAbsolutePath();
  }

  @Override
  public String toString() {
    return "Options{" +
        "bootstrapPath=" + this.bootstrapPath +
        ", configPath=" + this.configPath +
        '}';
  }
}
