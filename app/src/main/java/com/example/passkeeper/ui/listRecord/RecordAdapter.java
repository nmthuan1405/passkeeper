package com.example.passkeeper.ui.listRecord;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passkeeper.R;
import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.databinding.ItemRecordBinding;
import com.example.passkeeper.ui.record.view.ViewRecordActivity;

import java.util.HashMap;
import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder> {

    private List<Record> mListRecord = null;
    private ItemRecordBinding binding;
    private HashMap<String, Integer> iconMap;

    @NonNull
    @Override
    public RecordAdapter.RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemRecordBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecordViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordViewHolder holder, int position) {
        Record record = mListRecord.get(position);

        String name = record.getFieldValue("name");
        if (name != null) {
            holder.binding.nameRecordTextView.setText(name);
        }

        String description = record.getDescription();
        if (description != null) {
            holder.binding.descriptionRecordTextView.setText(description);
        } else {
            holder.binding.descriptionRecordTextView.setVisibility(View.GONE);
        }

        Integer icon = getIconMap().get(record.getType());
        if (icon != null) {
            holder.binding.iconTypeRecord.setImageResource(icon);
        }

        holder.binding.favoriteToggle.setChecked(record.isFavorite());
        holder.binding.favoriteToggle.setOnClickListener(view -> {
            // TODO: Handle when changes favourite status of this item
            record.setFavoriteStatus(!record.isFavorite());
        });

        // It's temporary
        // TODO: need further discussion
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), ViewRecordActivity.class);
            intent.putExtra("id", record.getId());
            view.getContext().startActivity(intent);
        });
    }

    private HashMap<String, Integer> getIconMap() {
        if (iconMap == null) {
            iconMap = new HashMap<>();
            iconMap.put("password", R.drawable.ic_password);
            iconMap.put("card", R.drawable.ic_card);
            iconMap.put("note", R.drawable.ic_note);
        }
        return iconMap;
    }

    @Override
    public int getItemCount() {
        if (mListRecord != null)
            return mListRecord.size();
        return 0;
    }

    public void setListRecord(List<Record> listRecord) {
        if (listRecord != null) {
            mListRecord = listRecord;
            notifyDataSetChanged();
        }
    }

    public static class RecordViewHolder extends RecyclerView.ViewHolder {

        private final ItemRecordBinding binding;

        public RecordViewHolder(@NonNull ItemRecordBinding itemBinding) {
            super(itemBinding.getRoot());
            binding = itemBinding;
        }
    }
}
