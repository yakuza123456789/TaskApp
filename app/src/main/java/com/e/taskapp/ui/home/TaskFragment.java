package com.e.taskapp.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.e.taskapp.R;
import com.e.taskapp.interfaces.OnItemClickListener;
import com.e.taskapp.models.Task;

import java.util.ArrayList;

public class TaskFragment extends Fragment {

    NavController navController;
    private TaskAdapter adapter;
    ArrayList<Task> list;
    private int currentPos;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_task, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initlist(view);
        initResultListener();
    }

    private void initResultListener() {
        getParentFragmentManager().setFragmentResultListener("form", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Log.e("home", "onFragmentResult");
                Task task = (Task) result.getSerializable("task");
                boolean edit = result.getBoolean("edit");
                if (edit) {
                    list.set(currentPos, task);
                } else {
                    list.add(0, task);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initlist(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        if (list == null) {
            list = new ArrayList<>();
            list.add(new Task("Azamat", 0L));
            list.add(new Task("Aza", 0L));
            list.add(new Task("Azamatik", 0L));
            list.add(new Task("Azamatello", 0L));
            list.add(new Task("Azamatro", 0L));
        }
        adapter = new TaskAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemCLick(int position) {
                currentPos = position;
                Task task = list.get(position);
                openForm(task);

            }

            @Override
            public void onItemLongClick(int position) {
                currentPos = position;
                showAlert(list.get(position));
            }
        });
    }

    private void showAlert(Task task) {

    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_home, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_Add) {
            openForm(null);
        }
        return super.onOptionsItemSelected(item);
    }

    private void openForm(Task task) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("task", task);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.action_navigation_home_to_formFragment, bundle);
    }
}
