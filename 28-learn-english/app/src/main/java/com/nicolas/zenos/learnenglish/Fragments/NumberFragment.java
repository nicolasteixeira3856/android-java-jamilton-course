package com.nicolas.zenos.learnenglish.Fragments;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.nicolas.zenos.learnenglish.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NumberFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NumberFragment extends Fragment implements View.OnClickListener {

    private ImageButton buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix;
    private MediaPlayer mediaPlayer;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NumberFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NumberFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NumberFragment newInstance(String param1, String param2) {
        NumberFragment fragment = new NumberFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_number, container, false);

        buttonOne = view.findViewById(R.id.one);
        buttonTwo = view.findViewById(R.id.two);
        buttonThree = view.findViewById(R.id.three);
        buttonFour = view.findViewById(R.id.four);
        buttonFive = view.findViewById(R.id.five);
        buttonSix = view.findViewById(R.id.six);

        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);
        buttonThree.setOnClickListener(this);
        buttonFour.setOnClickListener(this);
        buttonFive.setOnClickListener(this);
        buttonSix.setOnClickListener(this);

        return view;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
    //Log.i("Elemento clicado", "Item: " + v.getId());
        switch (v.getId()) {
            case R.id.one:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.one);
                playSound();
                break;
            case R.id.two:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.two);
                playSound();
                break;
            case R.id.three:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.three);
                playSound();
                break;
            case R.id.four:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.four);
                playSound();
                break;
            case R.id.five:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.five);
                playSound();
                break;
            case R.id.six:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.six);
                playSound();
                break;
        }
    }

    public void playSound() {
        if (mediaPlayer != null) {
            mediaPlayer.start();

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.release();
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}