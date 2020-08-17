package com.e.taskapp.ui.onboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.e.taskapp.Prefs;
import com.e.taskapp.R;
import com.e.taskapp.interfaces.OnViewListener;

public class PageAdapter extends PagerAdapter {

    private String[] titles = new String[] {"Привет!", "Как дела?", "Что делаешь?"};
    private int[] images = {R.drawable.dragon, R.drawable.griffin2, R.drawable.griffin};

    OnViewListener onViewListener;

    @Override
    public int getCount() {
        return 3;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.page_board, container, false);
        container.addView(view);

        TextView textView = view.findViewById(R.id.board_title);


        ImageView imageView = view.findViewById(R.id.board_imageView);
        Button button_finish = view.findViewById(R.id.board_btn);
        button_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Prefs(container.getContext()).setShown();
                onViewListener.onViewPagerClick();
            }
        });

        textView.setText(titles[position]);
        if (position == 0 || position == 1){
            button_finish.setVisibility(View.GONE);
        }

      imageView.setImageResource(images[position]);
        return view;
    }

    public void setOnViewListener(OnViewListener onViewListener) {
        this.onViewListener = onViewListener;

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
