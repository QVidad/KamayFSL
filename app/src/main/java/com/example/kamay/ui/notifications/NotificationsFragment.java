package com.example.kamay.ui.notifications;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.kamay.R;
import com.example.kamay.databinding.FragmentNotificationsBinding;
import com.example.kamay.ui.home.DBHelper;

public class NotificationsFragment extends Fragment {
    DBHelper DB;

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        DB = new DBHelper(getContext());

        Cursor res = DB.getDate();
        if(res.getCount()==0 ){
            Toast.makeText(getActivity(), "No data exist yet",Toast.LENGTH_SHORT).show();
        }
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()) {
            buffer.append(res.getString(0));
        }
 /*       while(res.moveToNext()) {
            buffer.append("ID: "+res.getString(0)+"\n");
            buffer.append("Date: "+res.getString(1)+"\n");
            buffer.append("Translation: "+res.getString(2)+"\n");
        }*/
        //binding.getDateText.setText(DB.getData().toString());
        binding.getDateText.setText(buffer.toString());

        binding.viewHistory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Cursor data = DB.getData();
                StringBuffer buffer2 = new StringBuffer();
                while(data.moveToNext()) {
                    buffer2.append("ID: "+data.getString(0)+"\n");
                    buffer2.append("Date: "+data.getString(1)+"\n");
                    buffer2.append("Translation: "+data.getString(2)+"\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setCancelable(true);
                builder.setTitle("Translation History");
                builder.setMessage(buffer2.toString());
                builder.show();
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}