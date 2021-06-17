package me.conclure.cloncled.bootstrap;

import me.conclure.cloncled.configuration.Key;
import me.conclure.cloncled.configuration.KeyFactory;

import static me.conclure.cloncled.configuration.KeyFactory.*;

public interface BootstrapKeys {
  Key<String> TOKEN = key(node -> node.node("token").getString());

  Iterable<Key<?>> KEYS = KeyFactory.collectKeys(BootstrapKeys.class);
}
