package me.conclure.cloncled.configuration;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public interface KeyFactory {
  SimpleTranformer<String> STRING = (node, path, fallback) -> node.node(path).getString(fallback);

  static Key<String> stringKey(String path, String fallback) {
    return new Key<>(new DelegatedSimpleTransformer<>(STRING,fallback,path));
  }

  static <T> Key<T> key(Key.Transformer<T> transformer) {
    return new Key<>(transformer);
  }

  static Iterable<Key<?>> collectKeys(Class<?> clazz) {
    return Arrays.stream(clazz.getFields())
        .filter(field -> Modifier.isStatic(field.getModifiers()))
        .filter(field -> field.getType() == Key.class)
        .map(field -> {
          try {
            return (Key<?>) field.get(null);
          } catch (IllegalAccessException e) {
            e.printStackTrace();
          }
          return null;
        })
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
  }
}
