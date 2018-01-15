package com.breadboys.willcolin;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.breadboys.willcolin.JavaBeans.Loaf;

import java.util.ArrayList;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoavesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoavesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoavesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ListView list;

    private OnFragmentInteractionListener mListener;

    public LoavesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoavesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoavesFragment newInstance(String param1, String param2) {
        LoavesFragment fragment = new LoavesFragment();
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
        View view = inflater.inflate(R.layout.fragment_loaves, container, false);

        list = (ListView) view.findViewById(R.id.loavesListView);

        CustomAdapter adapter = new CustomAdapter(getContext(), Loaf.getList());
        list.setAdapter(adapter);

        return view;
    }

    public class CustomAdapter extends ArrayAdapter<Loaf>{
        public CustomAdapter(Context context, ArrayList<Loaf> items){
            super(context, 0, items);
        }

        public View getView(int position, View convertView,  ViewGroup parent) {
            //we do this by checking if the item already has a view
            //and if it does not we provide it with one
            if(convertView == null){
                //providing the view
                convertView =
                        LayoutInflater.from(
                                getContext()).inflate(R.layout.loaf_view, parent, false);
            }
            final Loaf item = Loaf.getList().get(position);

            ImageView image = (ImageView) convertView.findViewById(R.id.loafImage);
            TextView name = (TextView) convertView.findViewById(R.id.loafName);
            TextView description = (TextView) convertView.findViewById(R.id.description);
            TextView price = (TextView) convertView.findViewById(R.id.price);
            final TextView quantity = (TextView) convertView.findViewById(R.id.quantity);

            ImageView minusButton = (ImageView) convertView.findViewById(R.id.minusButton);
            ImageView plusButton = (ImageView) convertView.findViewById(R.id.plusButton);

            minusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(item.getQuantity() > 0){
                        item.decreaseQuantity();
                        quantity.setText(String.format("%s", item.getQuantity()));
                    }
                }
            });

            plusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    item.increaseQuantity();
                    quantity.setText(String.format("%s", item.getQuantity()));
                }
            });

            image.setImageResource(item.getImage());
            description.setText(item.getDescription());
            price.setText(String.format(Locale.getDefault(), "$%.2f", item.getPrice()));
            quantity.setText(String.format("%s", item.getQuantity()));
            name.setText(item.getName());

            return convertView;
        }
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
