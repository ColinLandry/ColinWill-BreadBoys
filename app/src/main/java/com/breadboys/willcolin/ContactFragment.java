package com.breadboys.willcolin;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //Create buttons
    Button websiteButton;
    Button emailButton;
    Button callButton;
    Button fbButton;

    private OnFragmentInteractionListener mListener;

    public ContactFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactFragment newInstance(String param1, String param2) {
        ContactFragment fragment = new ContactFragment();
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
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        //Connect buttons with layout items
        websiteButton = (Button) view.findViewById(R.id.websiteButton);
        emailButton = (Button) view.findViewById(R.id.emailButton);
        callButton = (Button) view.findViewById(R.id.callButton);
        fbButton = (Button) view.findViewById(R.id.fbButton);

        //Launch call intent
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("tel:1111111111"));
                if(i.resolveActivity(getActivity().getPackageManager()) != null){
                    startActivity(i);
                }
            }
        });

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SENDTO);
                String[] emailAddress = {"bread.boys@hotmail.com"};
                i.setData(Uri.parse("mailto:"));
                i.putExtra(Intent.EXTRA_EMAIL, emailAddress);
                i.putExtra(Intent.EXTRA_SUBJECT, "Bread Boys Question");
                i.putExtra(Intent.EXTRA_TEXT, "Greetings Bread Boys, ");
                if(i.resolveActivity(getActivity().getPackageManager()) != null){
                    startActivity(i);
                }
            }
        });

        fbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri web = Uri.parse("https://www.facebook.com/Bread-Boys-416581148756920/");
                Intent i = new Intent(Intent.ACTION_VIEW, web);
                if(i.resolveActivity(getActivity().getPackageManager()) != null){
                    startActivity(i);
                }
            }
        });

        websiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri web = Uri.parse("https://en.wikipedia.org/wiki/Bread");
                Intent i = new Intent(Intent.ACTION_VIEW, web);
                if(i.resolveActivity(getActivity().getPackageManager()) != null){
                    startActivity(i);
                }
            }
        });
        return view;
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
