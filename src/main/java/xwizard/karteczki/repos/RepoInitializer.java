package xwizard.karteczki.repos;

import java.util.Map;

public interface RepoInitializer<ID, E extends Entity<ID>> {
  Map<ID, E> getValues();
}
