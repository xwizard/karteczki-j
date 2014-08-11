package xwizard.karteczki.events;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventEmitterMock implements EventEmitter {
  private Map<Class<?>,List<Event>> events;

  public EventEmitterMock() {
    events = new HashMap<Class<?>, List<Event>>();
  }

  public void emit(Event event) {
    List<Event> eventList = events.get(event.getClass());
    if (eventList == null) {
      eventList = new ArrayList<Event>();
      events.put(event.getClass(), eventList);
    }
    
    eventList.add(event);
  }
  
  public void assertEmitted(Class<?> clazz, int eventsCount) {
    if (eventsCount < 0) throw new IllegalArgumentException("eventsCount cannot be less than 0!");
    List<Event> eventList = events.get(clazz);
    if (eventList == null && eventsCount > 0) {
      throw new AssertionError("Expected " + eventsCount + " of " + clazz.getCanonicalName() + ", was 0");
    }
    if (eventList.size() != eventsCount) {
      throw new AssertionError("Expected " + eventsCount + " of " + clazz.getCanonicalName() + ", was " + eventList.size());
    }
  }
  
  @SuppressWarnings("unchecked")
  public <T extends Event> T getEvent(Class<T> clazz, int eventNumber) {
    List<Event> eventList = events.get(clazz);
    if (eventList == null) throw new AssertionError("Expected at least" + eventNumber + " of " + clazz.getCanonicalName() + ", was 0");
    return ((T)eventList.get(eventNumber));
  }
}
