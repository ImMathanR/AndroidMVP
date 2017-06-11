package me.immathan.biryanipoints.events;

/**
 * EventModel delivers the message with @{@link EventType} and it accepts any type of data to be
 * delivered.
 */

public class EventModel {

    @EventType
    private int mEventType;
    private Object mData;

    public EventModel(@EventType int eventType) {
        mEventType = eventType;
    }

    @EventType
    public int getEventType() {
        return mEventType;
    }

    public void setEventType(int eventType) {
        mEventType = eventType;
    }

    public Object getData() {
        return mData;
    }

    public void setData(Object data) {
        mData = data;
    }
}
