package com.studying.myapi.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.studying.myapi.CountryAdapter;
import com.studying.myapi.R;
import com.studying.myapi.databinding.ActivityMainBinding;
import com.studying.myapi.navigate.OnItemClickListener;
import com.studying.myapi.network.ApiService;
import com.studying.myapi.network.model.Country;
import com.studying.myapi.network.model.CountryDatabase;
import com.studying.myapi.ui.info.InfoActivity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {
    private ActivityMainBinding binding;

    private Disposable disposable;
    private long lastTimeBack = 0L;
    Toast toastBackBut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        toastBackBut = Toast.makeText(this,"Click again to exit", Toast.LENGTH_SHORT);

        disposable = ApiService.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showInfo, this::showError);
    }

    public void showInfo(List<Country> countries) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.root, ListFragment.getInstance(new CountryDatabase(countries)))
                .commit();
    }

    public void showError(Throwable t) {

    }

    public void onItemClick (Country country){
        Intent intent = new Intent(MainActivity.this, InfoActivity.class);
        intent.putExtra(InfoActivity.EXTRA_COUNTRY, country);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - lastTimeBack > 3000) {
            toastBackBut.show();
            lastTimeBack = System.currentTimeMillis();
        } else {
            toastBackBut.cancel();
            super.onBackPressed();
        }
    }

}
