package com.example.whereparty;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Event> values;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView txtArtistName;
        TextView txtVenueName;
        TextView txtDate;
        View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            txtArtistName = (TextView) v.findViewById(R.id.artistName);
            txtVenueName = (TextView) v.findViewById(R.id.venue);
            txtDate = (TextView) v.findViewById(R.id.date);
        }
    }

    public void add(int position, Event item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    private void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListAdapter(List<Event> myDataset) {
        values = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Event currentEvent = values.get(position);

        String artistName = currentEvent.getPerformance().get(0).getDisplayName();
        if(artistName.length() > 27){
            artistName = artistName.substring(27);
        }
        holder.txtArtistName.setText(artistName);

        String displayName = currentEvent.getDisplayName();
        if(displayName.length() > 45){
            displayName = displayName.substring(0,45);
        }
        holder.txtVenueName.setText(displayName);

        holder.txtDate.setText(currentEvent.getStart().getDate());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}
