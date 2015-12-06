package xwizard.karteczki.repos;

interface Repo<ID, T extends Entity<ID>> {

  void save(T entity);

  T get(ID id);

  void delete(ID id);
}