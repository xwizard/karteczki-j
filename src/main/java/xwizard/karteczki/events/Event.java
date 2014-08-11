package xwizard.karteczki.events;

import java.util.UUID;

public abstract class Event {
  public final UUID id;

  Event(UUID id) {
    super();
    this.id = id;
  }
  
  public Event() {
    super();
    this.id = UUID.randomUUID();
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
    Event other = (Event) obj;
    if (id == null) {
      if (other.id != null) return false;
    } else if (!id.equals(other.id)) return false;
    return true;
  }

  @Override
  public String toString() {
    return "Event [id=" + id + "]";
  }
  
}
