package xwizard.karteczki.repos;

import java.util.UUID;

interface Repo<T> {

  public abstract void save(T box);

  public abstract T get(UUID boxId);

  public abstract void delete(UUID boxId);

}