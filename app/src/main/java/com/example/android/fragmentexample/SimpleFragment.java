package com.example.android.fragmentexample;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class SimpleFragment extends Fragment {
    private static final int YES = 0, NO = 1;

    public static SimpleFragment newInstance() {
        return new SimpleFragment();
    }

    public SimpleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View rootView = inflater.inflate(R.layout.fragment_simple, container, false);
        final RadioGroup rg = rootView.findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb = radioGroup.findViewById(i);
                TextView tv = rootView.findViewById(R.id.tv_fragment_header);
                switch (radioGroup.indexOfChild(rb)) {
                    case YES:
                        tv.setText(R.string.yes_message);
                        break;
                    case NO:
                        tv.setText(R.string.no_message);
                        break;
                    default:
                        break;
                }
            }
        });

        RatingBar bar = rootView.findViewById(R.id.rbar_rating);
        bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText(getActivity(), "My rating: " + v, Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }


}
