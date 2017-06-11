package me.immathan.biryanipoints.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.immathan.biryanipoints.R;
import me.immathan.biryanipoints.model.HotelModel;

/**
 * Created by Mathan-GG on 10-Jun-17.
 */

public class HotelsAdapter extends RecyclerView.Adapter<HotelsAdapter.HotelViewHolder> {
    private Context mContext;
    private List<HotelModel> mHotelModels;
    private boolean isList = true;

    public HotelsAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<HotelModel> hotelModels) {
        mHotelModels = hotelModels;
    }

    public void changeViewType(boolean isList) {
        this.isList = isList;
    }

    @Override
    public HotelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (isList) {
            view = LayoutInflater.from(mContext).inflate(R.layout.hotels_list_item, parent, false);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.hotel_grid_item, parent, false);
        }
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HotelViewHolder holder, int position) {
        HotelModel hotelModel = mHotelModels.get(position);
        holder.mHotelName.setText(hotelModel.getName());

        Transformation transformation = new RoundedTransformationBuilder()
                .cornerRadiusDp(4)
                .oval(false)
                .build();

        Picasso.with(mContext)
                .load(hotelModel.getImageUrl())
                .fit()
                .transform(transformation)
                .into(holder.mHotelImage);
        holder.mAddress.setText(hotelModel.getAddress());
    }

    @Override
    public int getItemCount() {
        return mHotelModels == null ? 0 : mHotelModels.size();
    }

    class HotelViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.hotel_image)
        ImageView mHotelImage;
        @BindView(R.id.hotel_name)
        TextView mHotelName;
        @BindView(R.id.address)
        TextView mAddress;

        public HotelViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
