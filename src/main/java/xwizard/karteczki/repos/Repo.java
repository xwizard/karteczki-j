package xwizard.karteczki.repos;

import java.util.UUID;

interface Repo<T> {

  void save(T entity);

  T get(UUID id);

  void delete(UUID id);
}