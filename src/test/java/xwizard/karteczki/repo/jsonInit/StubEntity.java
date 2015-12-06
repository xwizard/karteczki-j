package xwizard.karteczki.repo.jsonInit;

import java.util.UUID;

import xwizard.karteczki.repos.Entity;

public class StubEntity implements Entity<UUID> {
  private final UUID id;
  
  @SuppressWarnings("unused")
  private StubEntity() {
    this.id = UUID.randomUUID();
  }

  public StubEntity(UUID id) {
    super();
    this.id = id;
  }

  public UUID getId() {
    return id;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    StubEntity other = (StubEntity) obj;
    if (id == null) {
      if (other.id != null) return false;
    } else if (!id.equals(other.id)) return false;
    return true;
  }
}
