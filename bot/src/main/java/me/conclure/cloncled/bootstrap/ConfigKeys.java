package me.conclure.cloncled.bootstrap;

import me.conclure.cloncled.configuration.Key;
import me.conclure.cloncled.configuration.KeyFactory;

import static me.conclure.cloncled.configuration.KeyFactory.*;

public interface ConfigKeys {
  Key<String> PREFIX = stringKey("prefix","!");

  Iterable<Key<?>> KEYS = KeyFactory.collectKeys(ConfigKeys.class);
}
