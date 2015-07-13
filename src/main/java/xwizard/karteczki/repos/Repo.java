package xwizard.karteczki.repos;

import java.util.UUID;

interface Repo<T> {

  abstract void save(T entity);

  abstract T get(UUID id);

  abstract void delete(UUID id);
}