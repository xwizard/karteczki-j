package xwizard.karteczki.events;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventEmitterMock implements EventEmitter {
  private Map<Class<?>, List<EventObject>> events;

  public EventEmitterMock() {
    events = new HashMap<Class<?>, List<EventObject>>();
  }

  public void emit(EventObject event) {
    List<EventObject> eventList = events.get(event.getClass());
    if (eventList == null) {
      eventList = new ArrayList<EventObject>();
      events.put(event.getClass(), eventList);
    }
    
    eventList.add(event);
  }
  
  public void assertEmitted(Class<?> clazz, int eventsCount) {
    if (eventsCount < 0) throw new IllegalArgumentException("eventsCount cannot be less than 0!");
    List<EventObject> eventList = events.get(clazz);
    if (eventList == null && eventsCount > 0) {
      throw new AssertionError("Expected " + eventsCount + " of " + clazz.getCanonicalName() + ", was 0");
    }
    if (eventList.size() != eventsCount) {
      throw new AssertionError("Expected " + eventsCount + " of " + clazz.getCanonicalName() + ", was " + eventList.size());
    }
  }
  
  @SuppressWarnings("unchecked")
  public <T extends EventObject> T getEvent(Class<T> clazz, int eventNumber) {
    List<EventObject> eventList = events.get(clazz);
    if (eventList == null) throw new AssertionError("Expected at least" + eventNumber + " of " + clazz.getCanonicalName() + ", was 0");
    return ((T)eventList.get(eventNumber));
  }
}
