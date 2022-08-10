package com.example.collections_and_maps;

import android.os.Build;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.time.temporal.Temporal;

public class DataView {
    private TextView nameView; // название
    private ProgressBar progressBar;  // состояние загрузки
    private TextView resultView; // отображение результата


   // Нужно в конструктор принять правильные вводные данные и присвоить их вьюшкам

    public DataView(String nameView, Boolean progressBar, String resultView){

        this.nameView = nameView.findViewById(R.id.nameView);
        this.progressBar=progressBar.findViewById(R.id.progressBar);
        this.resultView=resultView.findViewById(R.id.resultView);
    }

    public String getName() {
        return String.valueOf(nameView.getText());
    }

    public String getResult() {
        return String.valueOf(resultView.getText());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean getActivateted() {
        return progressBar.isAnimating();
    }
}
