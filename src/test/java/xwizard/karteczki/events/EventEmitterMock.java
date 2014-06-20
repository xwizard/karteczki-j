package xwizard.karteczki.events;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventEmitterMock implements EventEmitter {
  public Map<Class<?>, List<EventObject>> events;

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
    List<EventObject> eventList = events.get(clazz);
    if (eventList.size() != eventsCount) {
      throw new AssertionError();
    }
  }

}
