package me.immathan.biryanipoints.events;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Event type definition to address the type of event.
 */

@IntDef({EventType.HOTEL_DETAILS_NOT_RECEIVED, EventType.HOTEL_DETAILS_RECEIVED, EventType.VIEW_BY_GRID, EventType.VIEW_BY_LIST})
@Retention(RetentionPolicy.SOURCE)
public @interface EventType {

    int HOTEL_DETAILS_RECEIVED = 0;
    int HOTEL_DETAILS_NOT_RECEIVED = 1;
    int VIEW_BY_GRID = 2;
    int VIEW_BY_LIST = 3;

}
