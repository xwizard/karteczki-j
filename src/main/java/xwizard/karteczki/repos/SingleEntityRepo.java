package xwizard.karteczki.repos;

public interface SingleEntityRepo<ID, T extends Entity<ID>> extends Repo<ID, Entity<ID>> {
  T get();
}
