package com.studying.myapi.ui.info;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.studying.myapi.databinding.ActivityInfoBinding;
import com.studying.myapi.network.model.Country;
import com.studying.myapi.network.model.Currency;


public class InfoActivity extends AppCompatActivity {
    public static final String EXTRA_COUNTRY = "InfoActivity.EXTRA_COUNTRY";

    private ActivityInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Country country = getIntent().getParcelableExtra(EXTRA_COUNTRY);

        binding.countryNameInfo.setText("Name: " + country.name);
        binding.countryAreaInfo.setText("Area: " + country.area);
        StringBuilder callingCodes = new StringBuilder();
        for (String callingCode : country.callingCodes) {
            callingCodes.append("+" + callingCode + " ");
        }
        binding.countryCallingCodesInfo.setText("Calling codes: " + callingCodes);
        binding.countryCapitalInfo.setText("Capital: " + country.capital);
        if (country.currencies != null) {
            StringBuilder currencies = new StringBuilder();
            for (Currency currency : country.currencies) {
                currencies.append(currency.code + " ");
            }
            binding.countryCurrenciesInfo.setText("Currencies: " + currencies);
        }
        binding.countryPopulationInfo.setText("Population: " + country.population);
        binding.countryRegionInfo.setText("Region: " + country.region);
    }
}
