package me.immathan.biryanipoints.model.rest;

import java.util.List;

import me.immathan.biryanipoints.model.HotelModel;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Mathan-GG on 09-Jun-17.
 */

public interface ApiClient {

    @GET("e13fd67be56efdaff38368c428b066a7/raw/0ccdc9a1ee11129e06c439fcdee06fc9252158c1/locations.json")
    Call<List<HotelModel>> getHotelDetails();

}
