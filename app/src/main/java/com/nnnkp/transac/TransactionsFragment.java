package com.nnnkp.transac;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by npraj1 on 5/23/2017.
 */

public class TransactionsFragment extends Fragment implements View.OnClickListener {


    private TextView tvClientName, tvNetTransactionType, tvNetTransactionAmount;
    private TransactionDataSource transactionDB;
    private ClientDataSource clientDB;

    private Button addTransactionButton, addClientButton;

    public TransactionsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //  return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.layout_fragment_transactions, container, false);
        initViews(view);


        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onClick(View view) {


    }


    private void initViews(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_transactions);
        recyclerView.setHasFixedSize(true);

        //Using Activity's context :: in Fragment
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        clientDB = new ClientDataSource(getActivity());
        clientDB.open();
        clientDB.createClient("Praveen","pagni@gmail.com","9879",100,"DEBIT");
        clientDB.createClient("Chirag","cnaru@gmail.com","9879",200,"DEBIT");
        List<Client> clients = clientDB.getAllClients();

        transactionDB = new TransactionDataSource(getActivity());
        transactionDB.open();
        transactionDB.createTransaction("Praveen",100,"DEBIT","McD","may");
        List<Transaction> transactions = transactionDB.getAllTransactions();


        RecyclerView.Adapter adapter = new TransactionsDataAdapter(clients, transactions);
        recyclerView.setAdapter(adapter);

    }
}
