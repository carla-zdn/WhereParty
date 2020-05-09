package com.example.whereparty.presentation.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whereparty.R;
import com.example.whereparty.presentation.model.concertAPI.Event;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private final List<Event> values;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Event item, String typeDetail);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtArtistName;
        TextView txtVenueName;
        TextView txtDate;
        View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            txtArtistName = v.findViewById(R.id.artistName);
            txtVenueName = v.findViewById(R.id.venue);
            txtDate = v.findViewById(R.id.date);
        }
    }

    ListAdapter(List<Event> myDataset, OnItemClickListener listener) {
        this.values = myDataset;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final Event currentEvent = values.get(position);

        String artistName = currentEvent.getPerformance().get(0).getDisplayName();
        if(artistName.length() > 27){
            artistName = artistName.substring(27);
        }
        holder.txtArtistName.setText(artistName);
        holder.txtArtistName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(currentEvent, "artistDetail");
            }
        });

        String displayName = currentEvent.getDisplayName();
        if(displayName.length() > 45){
            displayName = displayName.substring(0,45);
        }
        holder.txtVenueName.setText(displayName);
        holder.txtVenueName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(currentEvent, "venueDetail");
            }
        });

        holder.txtDate.setText(currentEvent.getStart().getDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(currentEvent, "reservation");
            }
        });

    }

    @Override
    public int getItemCount() {
        return values.size();
    }

}
