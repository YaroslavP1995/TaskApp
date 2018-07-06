package com.example.slavik.taskapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentOne extends Fragment {

    public static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    private  int pageNumber ;
    private OnFragment1DataListener mListener;
    private final int KEY_PLUS = 1;
    private final int KEY_MINUS = 2;
    private final int KEY_SHOW = 3;

    interface OnFragment1DataListener {
        void onFragment1DataListener(int i);
    }

    static FragmentOne newInstance(int page) {
        FragmentOne fragmentOne = new  FragmentOne();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER,page);
        fragmentOne.setArguments(arguments);
        return fragmentOne;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1,null);
        TextView numOfPage = view.findViewById(R.id.numFragmentsID);

        ImageView imageViewPlus = view.findViewById(R.id.buttonBgPlusID);
        imageViewPlus.setImageResource(R.mipmap.button_bg_plus);
        imageViewPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onFragment1DataListener(KEY_PLUS);
            }
        });

        ImageView imageViewMinus = view.findViewById(R.id.buttonBgMinusID);
        imageViewMinus.setImageResource(R.mipmap.button_bg_minus);
        imageViewMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onFragment1DataListener(KEY_MINUS);
            }
        });

        ImageView imageViewCreateNotification = view.findViewById(R.id.create_new_notificationID);
        imageViewCreateNotification.setImageResource(R.mipmap.create_new);
        imageViewCreateNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onFragment1DataListener(KEY_SHOW);
            }
        });
        numOfPage.setText(""+(pageNumber+1));
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragment1DataListener){
            mListener = (OnFragment1DataListener)context;
        }else {
            throw new RuntimeException(context.toString()+"must be implement OnFragment");
        }

    }


}

