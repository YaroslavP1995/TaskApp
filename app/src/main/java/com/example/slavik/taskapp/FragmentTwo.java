package com.example.slavik.taskapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentTwo extends Fragment {

    public static final String ARGUMENT_PAGE_NUMBER = "arg_page_number_two";
    private  int pageNumber ;

    static FragmentTwo newInstance(int page) {
        FragmentTwo fragmentTwo = new FragmentTwo();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER,page);
        fragmentTwo.setArguments(arguments);
        return fragmentTwo;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2,null);
        TextView textView = view.findViewById(R.id.numFragments);
        textView.setText(""+pageNumber);
        return view;
    }
}