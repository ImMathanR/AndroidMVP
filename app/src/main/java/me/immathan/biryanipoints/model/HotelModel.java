
package me.immathan.biryanipoints.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelModel implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lon")
    @Expose
    private Double lon;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("highlights")
    @Expose
    private String highlights;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHighlights() {
        return highlights;
    }

    public void setHighlights(String highlights) {
        this.highlights = highlights;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.id);
        dest.writeValue(this.lat);
        dest.writeValue(this.lon);
        dest.writeString(this.address);
        dest.writeString(this.highlights);
        dest.writeString(this.imageUrl);
    }

    public HotelModel() {
    }

    protected HotelModel(Parcel in) {
        this.name = in.readString();
        this.id = in.readString();
        this.lat = (Double) in.readValue(Double.class.getClassLoader());
        this.lon = (Double) in.readValue(Double.class.getClassLoader());
        this.address = in.readString();
        this.highlights = in.readString();
        this.imageUrl = in.readString();
    }

    public static final Parcelable.Creator<HotelModel> CREATOR = new Parcelable.Creator<HotelModel>() {
        @Override
        public HotelModel createFromParcel(Parcel source) {
            return new HotelModel(source);
        }

        @Override
        public HotelModel[] newArray(int size) {
            return new HotelModel[size];
        }
    };
}
