package idevelopapps.com.mvvmlivedata.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import idevelopapps.com.mvvmlivedata.Model.quranModel;
import idevelopapps.com.mvvmlivedata.R;
import idevelopapps.com.mvvmlivedata.ViewModel.quranViewHolder;

public class quranAdapter extends RecyclerView.Adapter<quranViewHolder> {
    Context context;
    ArrayList<quranModel> list;

    public quranAdapter() {
    }

    public quranAdapter(Context context, ArrayList<quranModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public quranViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new quranViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull quranViewHolder holder, int position) {
        quranModel currentData = list.get(position);
        holder.textView.setText(currentData.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
