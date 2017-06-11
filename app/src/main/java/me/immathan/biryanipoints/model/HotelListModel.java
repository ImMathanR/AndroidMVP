package me.immathan.biryanipoints.model;

import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.List;

import me.immathan.biryanipoints.bus.Bus;
import me.immathan.biryanipoints.events.EventModel;
import me.immathan.biryanipoints.events.EventType;
import me.immathan.biryanipoints.model.rest.RestAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Model class makes HTTP requests and caches the response locally to provide quicker subsequent
 * response. It uses Retrofit library to make HTTP requests.
 */

public class HotelListModel extends ViewModel {

    private static final String TAG = HotelListModel.class.getSimpleName();

    private List<HotelModel> mHotelModels;

    private static class SingleTonHelper {
        private static final HotelListModel INSTANCE = new HotelListModel();
    }

    private HotelListModel() {

    }

    public static HotelListModel getInstance() {
        return SingleTonHelper.INSTANCE;
    }

    /**
     * To fetch list of Hotels information
     */
    public void fetchHotelList() {
        Log.d(TAG, "Fetch hotel list");
        if (mHotelModels != null) {
            Log.d(TAG, "Already received. So returning from cache.");
            EventModel eventModel = new EventModel(EventType.HOTEL_DETAILS_RECEIVED);
            eventModel.setData(mHotelModels);
            Bus.getDefault().post(eventModel);
        }
        RestAdapter.getInstance().getApiClient().getHotelDetails().enqueue(new Callback<List<HotelModel>>() {
            @Override
            public void onResponse(Call<List<HotelModel>> call, Response<List<HotelModel>> response) {
                if (response.isSuccessful()) {
                    mHotelModels = response.body();
                    EventModel eventModel = new EventModel(EventType.HOTEL_DETAILS_RECEIVED);
                    eventModel.setData(mHotelModels);
                    Bus.getDefault().post(eventModel);
                    Log.d(TAG, "Hotel information received");
                } else {
                    EventModel eventModel = new EventModel(EventType.HOTEL_DETAILS_NOT_RECEIVED);
                    Bus.getDefault().post(eventModel);
                    Log.e(TAG, "Hotel information not received");
                }
            }

            @Override
            public void onFailure(Call<List<HotelModel>> call, Throwable t) {
                EventModel eventModel = new EventModel(EventType.HOTEL_DETAILS_NOT_RECEIVED);
                Bus.getDefault().post(eventModel);
                Log.e(TAG, t.getLocalizedMessage());
            }
        });
    }
}
