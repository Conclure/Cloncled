package me.conclure.concled.configuration;

public interface KeyFactory {
  SimpleTranformer<String> STRING = (node, path, fallback) -> node.node(path).getString(fallback);

  static Key<String> stringKey(String path, String fallback) {
    return new Key<>(new DelegatedSimpleTransformer<>(STRING,fallback,path));
  }

  static <T> Key<T> key(Key.Transformer<T> transformer) {
    return new Key<>(transformer);
  }
}
