package idevelopapps.com.mvvmlivedata.ViewModel;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import idevelopapps.com.mvvmlivedata.R;

public class quranViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;
    public Button button;
    public quranViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.tv_text);
        button = itemView.findViewById(R.id.bt_play);
    }
}
