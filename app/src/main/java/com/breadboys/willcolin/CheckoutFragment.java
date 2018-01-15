package com.breadboys.willcolin;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.breadboys.willcolin.JavaBeans.Loaf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CheckoutFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CheckoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckoutFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ListView list;

    //Create cart list for checkout
    ArrayList<Loaf> cartItems = Loaf.getItemsWithQuantity();
    CustomAdapter adapter;

    Button orderBtn;
    Button calendarBtn;
    Button locationBtn;

    long eventStartInMillis= System.currentTimeMillis();
    long eventEndInMillis= 1234567890;

    private OnFragmentInteractionListener mListener;

    public CheckoutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CheckoutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CheckoutFragment newInstance(String param1, String param2) {
        CheckoutFragment fragment = new CheckoutFragment();
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
        View view = inflater.inflate(R.layout.fragment_checkout, container, false);
        TextView totalCostText = (TextView) view.findViewById(R.id.totalCostText);

        //Calculate total cost
        double totalCost = 0;
        double cost = 0;

        for(int i = 0; i < Loaf.getList().size(); i++){
            //Set cost to loaf quantity and price for current item
            cost = Loaf.getList().get(i).getQuantity() * Loaf.getList().get(i).getPrice();
            totalCost += cost;
        }

        //Update total cost text
        totalCostText.setText(String.format(Locale.getDefault(), "$%.2f", totalCost));

        calendarBtn = (Button) view.findViewById(R.id.calendarBtn);
        locationBtn = (Button) view.findViewById(R.id.locationBtn);
        orderBtn = (Button) view.findViewById(R.id.submit);

        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);

                i.setData(Uri.parse("geo:42.317432,-83.026772"));

                if(i.resolveActivity(getActivity().getPackageManager()) != null){
                    startActivity(i);
                }
            }
        });

        calendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_INSERT);
                i.setType("vnd.android.cursor.item/event") ;
                i.putExtra("title", "Pick Up");
                i.putExtra("description", "Some description");
                i.putExtra("beginTime", eventStartInMillis);
                i.putExtra("endTime", eventEndInMillis);



                if(i.resolveActivity(getActivity().getPackageManager()) != null){
                    startActivity(i);
                }
            }
        });

        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListView loafList = (ListView) view.findViewById(R.id.cartList);

                //if loaf list has no items with a quantity
                if(Loaf.getItemsWithQuantity().size() == 0){
                    //Show no items in list notification
                    Toast.makeText(getContext(), "No items in checkout list.", Toast.LENGTH_SHORT).show();
                }else {
                    //Show notification for placed order
                    Toast.makeText(getContext(), "Your order was placed!", Toast.LENGTH_SHORT).show();

                    //loop through items with quantity and set to 0
                    for(int i = 0; i < Loaf.getItemsWithQuantity().size(); i++){
                        Loaf.getItemsWithQuantity().get(i).setQuantity(0);
                    }

                    //Notify the adapter and clear
                    adapter.notifyDataSetChanged();
                    adapter.clear();
                }
            }
        });

        list = (ListView) view.findViewById(R.id.cartList);

        //Set adapter to custom adapter and pass cartItems
        adapter = new CustomAdapter(getContext(), cartItems);
        list.setAdapter(adapter);

        return view;
    }

    public class CustomAdapter extends ArrayAdapter<Loaf> {
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
                                getContext()).inflate(R.layout.simple_loaf_view, parent, false);
            }
            Loaf item = cartItems.get(position);

            TextView name = (TextView) convertView.findViewById(R.id.loafName);
            TextView price = (TextView) convertView.findViewById(R.id.price);
            TextView total = (TextView) convertView.findViewById(R.id.total);
            TextView quantity = (TextView) convertView.findViewById(R.id.quantity);

            total.setText(String.format(Locale.getDefault(), "$%.2f", (item.getPrice() * item.getQuantity())));
            price.setText(String.format(Locale.getDefault(), "$%.2f", item.getPrice()));
            quantity.setText(String.format("%s", item.getQuantity()));
            name.setText(item.getName());

            return convertView;
        }
    }

    // TODO: Rename method, update  argument and hook method into UI event
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
