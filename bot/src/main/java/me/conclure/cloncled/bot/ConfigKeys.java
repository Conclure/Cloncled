package me.conclure.cloncled.bot;

import me.conclure.cloncled.configuration.Key;
import me.conclure.cloncled.configuration.KeyFactory;

public interface ConfigKeys {

  Iterable<Key<?>> KEYS = KeyFactory.collectKeys(ConfigKeys.class);
}
