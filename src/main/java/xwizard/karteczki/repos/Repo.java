package xwizard.karteczki.repos;

import java.util.UUID;

interface Repo<T> {

  public abstract void save(T entity);

  public abstract T get(UUID id);

  public abstract void delete(UUID id);

}