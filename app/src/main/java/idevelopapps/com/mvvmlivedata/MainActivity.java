package idevelopapps.com.mvvmlivedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import idevelopapps.com.mvvmlivedata.Adapter.quranAdapter;
import idevelopapps.com.mvvmlivedata.Model.quranModel;
import idevelopapps.com.mvvmlivedata.ViewModel.quranViewModel;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    quranViewModel quranViewModel;
    quranAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        quranViewModel = ViewModelProviders.of(MainActivity.this).get(quranViewModel.class);
        getLifecycle().addObserver(quranViewModel);
        quranViewModel.getList().observe(this, new Observer<ArrayList<quranModel>>() {
            @Override
            public void onChanged(ArrayList<quranModel> quranModels) {
                if(quranModels.size() > 0) {
                    init();
                    adapter.notifyDataSetChanged();
                }else {
                    Log.d("sizeQ", String.valueOf(quranModels.size()));
                }

            }
        });

    }

    private void init() {
        adapter = new quranAdapter(MainActivity.this,quranViewModel.getList().getValue());
        recyclerView.setAdapter(adapter);
    }
}
