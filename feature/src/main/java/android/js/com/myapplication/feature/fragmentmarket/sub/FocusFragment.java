package android.js.com.myapplication.feature.fragmentmarket.sub;


import android.js.com.myapplication.feature.R;
import android.os.Bundle;
//import android.app.Fragmentent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.js.com.myapplication.feature.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FocusFragment extends Fragment {


    public FocusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_focus, container, false);
        return view;
    }

}
