package xwizard.karteczki.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

public class StubHandler {
  private final Map<Class<?>,List<Object>> events;

  public StubHandler() {
    events = new HashMap<Class<?>, List<Object>>();
  }

  @Subscribe
  public void handle(DeadEvent event) {
    Object realEvent = event.getEvent();
    List<Object> eventList = events.get(realEvent.getClass());
    if (eventList == null) {
      eventList = new ArrayList<Object>();
      events.put(realEvent.getClass(), eventList);
    }
    
    eventList.add(realEvent);
  }
  
  public void assertEmitted(Class<?> clazz, int eventsCount) {
    if (eventsCount < 0) throw new IllegalArgumentException("eventsCount cannot be less than 0!");
    List<Object> eventList = events.get(clazz);
    if (eventList == null && eventsCount > 0) {
      throw new AssertionError("Expected " + eventsCount + " of " + clazz.getCanonicalName() + ", was 0");
    }
    if (eventList.size() != eventsCount) {
      throw new AssertionError("Expected " + eventsCount + " of " + clazz.getCanonicalName() + ", was " + eventList.size());
    }
  }
  
  @SuppressWarnings("unchecked")
  public <T extends Event> T getEvent(Class<T> clazz, int eventNumber) {
    List<Object> eventList = events.get(clazz);
    if (eventList == null) throw new AssertionError("Expected at least" + eventNumber + " of " + clazz.getCanonicalName() + ", was 0");
    return ((T)eventList.get(eventNumber));
  }
}
