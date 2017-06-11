package me.immathan.biryanipoints.bus;

import org.greenrobot.eventbus.EventBus;

/**
 * Bus handles publish/subscribe event system. Any class can subscribe for a event and it will be
 * delivered to the the observers(methods) which is subscribed with @{@link org.greenrobot.eventbus.Subscribe}.
 * Currently it uses Green robot's EventBus to publish events.
 */

public class Bus {

    private final EventBus mEventBus;

    private static class SingleTonHelper {
        private static final Bus INSTANCE = new Bus();
    }

    private Bus() {
        mEventBus = EventBus.getDefault();
    }

    public static Bus getDefault() {
        return SingleTonHelper.INSTANCE;
    }

    /**
     * To subscribe for an Observer for an Observable.
     * @param subscriber
     */
    public void register(Object subscriber) {
        mEventBus.register(subscriber);
    }

    /**
     * To Unsubscribe an Observer.
     * @param subscriber
     */
    public void unregister(Object subscriber) {
        mEventBus.unregister(subscriber);
    }

    public boolean isRegistered(Object subscriber) {
        return mEventBus.isRegistered(subscriber);
    }

    public void post(Object event) {
        mEventBus.post(event);
    }

    public void postSticky(Object event) {
        mEventBus.postSticky(event);
    }

}
