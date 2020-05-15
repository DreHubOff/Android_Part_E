package com.studying.myapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.studying.myapi.databinding.ItemCountryBinding;
import com.studying.myapi.network.model.Country;
import com.studying.myapi.ui.main.MainActivity;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryHolder> {

    private List<Country> list;

    public CountryAdapter(List<Country> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CountryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCountryBinding binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CountryHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryHolder holder, int position) {
        Country country = list.get(position);
        holder.bind(country);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void update(List<Country> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }


    static class CountryHolder extends RecyclerView.ViewHolder {
        private ItemCountryBinding binding;

        public CountryHolder(ItemCountryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Country country) {
            binding.countryNameItem.setText(country.name);
            binding.capitalItem.setText(country.capital);
            binding.populationItem.setText(country.population);
            binding.itemRoot.setOnClickListener(v -> ((MainActivity) binding.getRoot().getContext()).onItemClick(country));
        }
    }


}
