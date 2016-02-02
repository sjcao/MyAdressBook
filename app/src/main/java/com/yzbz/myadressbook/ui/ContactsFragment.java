package com.yzbz.myadressbook.ui;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.yzbz.myadressbook.R;
import com.yzbz.myadressbook.ui.adapter.AdapterContacts;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView lv_contacts;
    private SearchView searchView;
    private AdapterContacts adapter;

    public ContactsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactsFragment newInstance(String param1, String param2) {
        ContactsFragment fragment = new ContactsFragment();
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

    private void initView() {
        View headView= LayoutInflater.from(getActivity()).inflate(R.layout.layout_head_search,null);
        searchView= (SearchView) headView.findViewById(R.id.searchView);
        lv_contacts= (ListView) getView().findViewById(R.id.lv_contacts);
        final List<String> testData=new ArrayList<String>();
        for (int i=0;i<10;i++) {
            testData.add("Jaykey");
            testData.add("John");
            testData.add("Adapi");
        }
        adapter=new AdapterContacts(getActivity(),testData);
        lv_contacts.setAdapter(adapter);
        lv_contacts.setTextFilterEnabled(true);
        lv_contacts.addHeaderView(headView);

        searchView.setFocusable(false);
        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText==null||newText.length()==0) {
                    lv_contacts.clearTextFilter();
                    //adapter.setDataList(testData);
                    //adapter.notifyDataSetChanged();
                }
                else {
                    lv_contacts.setFilterText(newText);
                }
                return true;
            }
        });

        lv_contacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),PersonInfoActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

}
