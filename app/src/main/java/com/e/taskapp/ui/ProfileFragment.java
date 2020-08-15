package com.e.taskapp.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.taskapp.MainActivity;
import com.e.taskapp.R;
import com.e.taskapp.interfaces.ImageListener;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {


    ImageListener listener;
    CircleImageView circleImageView;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (MainActivity) context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        circleImageView = view.findViewById(R.id.imageView);

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               listener.imageSet(circleImageView);
            }
        });
    }
}
