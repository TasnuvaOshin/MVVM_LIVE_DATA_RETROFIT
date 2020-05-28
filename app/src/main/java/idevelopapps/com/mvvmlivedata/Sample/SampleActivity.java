package idevelopapps.com.mvvmlivedata.Sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import idevelopapps.com.mvvmlivedata.Model.resultModel;
import idevelopapps.com.mvvmlivedata.R;
import idevelopapps.com.mvvmlivedata.Repository.resultRepository;
import idevelopapps.com.mvvmlivedata.ViewModel.resultViewModel;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SampleActivity extends AppCompatActivity {
    private TextView result;
    resultViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        result = findViewById(R.id.result);
        viewModel = ViewModelProviders.of(this).get(resultViewModel.class);
        getLifecycle().addObserver(viewModel);
        viewModel.getList().observe(this, new Observer<ArrayList<resultModel>>() {
            @Override
            public void onChanged(ArrayList<resultModel> resultModels) {
                   if(resultModels.size() > 0){
                       result.setText(resultModels.get(0).getValue());
                   }
            }
        });


    }

    public void CheckUpdate(View view) {
        resultRepository.getInstance().fetchresult();

    }
}
