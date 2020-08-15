package com.e.taskapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.e.taskapp.R;
import com.e.taskapp.models.Task;


public class FormFragment extends Fragment {
  private NavController navController;
  private EditText editText;
   private boolean edit = false;

   private Task task;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.editText);
        task = (Task) requireArguments().getSerializable("task");
        if (task != null){
            editText.setText(task.getTitle());
            edit = true;
        }
        Button button = view.findViewById(R.id.btn_save);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            save();
            }
        });
    }

    private void save() {
        String s = editText.getText().toString().trim();
        if (!s.isEmpty()){
            Task task = new Task(s, System.currentTimeMillis());
            Bundle bundle = new Bundle();
            bundle.putSerializable("task", task);
            bundle.putSerializable("edit", edit);
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            getParentFragmentManager().setFragmentResult("form", bundle);
            navController.navigateUp();
        } else {
            editText.setError("Заполните это поле");
        }
    }
}
