package me.conclure.concled.bootstrap;

import me.conclure.concled.configuration.Key;

import static me.conclure.concled.configuration.KeyFactory.*;

public interface BootstrapKeys {
  Key<String> TOKEN = stringKey("token",null);
}
