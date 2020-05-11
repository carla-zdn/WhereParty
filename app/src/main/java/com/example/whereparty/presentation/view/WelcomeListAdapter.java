package com.example.whereparty.presentation.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whereparty.R;
import com.example.whereparty.presentation.model.areaAPI.Location;
import com.example.whereparty.presentation.model.concertAPI.Event;

import java.util.List;

public class WelcomeListAdapter extends RecyclerView.Adapter<WelcomeListAdapter.ViewHolder> {
    private final List<Location> values;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String id);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCountry;
        TextView txtTown;
        View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            txtCountry = v.findViewById(R.id.textViewAreaCountry);
            txtTown = v.findViewById(R.id.textViewArea);
        }
    }

    WelcomeListAdapter(List<Location> myDataset, OnItemClickListener listener) {
        this.values = myDataset;
        this.listener = listener;
    }

    @NonNull
    @Override
    public WelcomeListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_welcome_layout, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final Location currentEvent = values.get(position);

        holder.txtCountry.setText(currentEvent.getMetroArea().getCountry().getDisplayName());

        holder.txtTown.setText(currentEvent.getMetroArea().getDisplayName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(Integer.toString(currentEvent.getMetroArea().getId()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return values.size();
    }

}
