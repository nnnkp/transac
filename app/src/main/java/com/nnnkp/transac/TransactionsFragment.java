package com.nnnkp.transac;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by npraj1 on 5/23/2017.
 */

public class TransactionsFragment extends Fragment implements View.OnClickListener {


    private TextView tvClientName, tvNetTransactionType, tvNetTransactionAmount;
    private TransactionDataSource transactionDB;
    private ClientDataSource clientDB;
    private int edit_position;

//    RecyclerView.ViewHolder holder = new TransactionsDataAdapter();

    List<Client> clients = new ArrayList<>();
    List<Transaction> transactions =  new ArrayList<>();

    private TransactionsDataAdapter transactionsDataAdapter ;

    private Button addTransactionButton, addClientButton;

    //new
    private AlertDialog.Builder alertDialogForTransaction, alertDialogForClient;
    private EditText etTransactionAmount, etTransactionType, etClientName, etClientEmail, etClientMobile;

    private boolean add = false;

    private View view, viewAddClient, viewAddTransaction;
    public TransactionsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //  return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.layout_fragment_transactions, container, false);
        initViews(view);

        initDialogForClient();
        initDialogForTransaction();

        // Inflate the layout for this fragment
        return view;
    }



    private void initViews(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_transactions);
        recyclerView.setHasFixedSize(true);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(this);



        //Using Activity's context :: in Fragment
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        clientDB = new ClientDataSource(getActivity());
        clientDB.open();
        clientDB.createClient("Praveen","pagni@gmail.com","9879",100,"DEBIT");
        clientDB.createClient("Chirag","cnaru@gmail.com","9879",200,"DEBIT");
        clientDB.createClient("Chirag","cnaru@gmail.com","9879",200,"DEBIT");
        clientDB.createClient("Chirag","cnaru@gmail.com","9879",200,"DEBIT");
        clientDB.createClient("Chirag","cnaru@gmail.com","9879",200,"DEBIT");
        clientDB.createClient("Chirag","cnaru@gmail.com","9879",200,"DEBIT");
        clientDB.createClient("Chirag","cnaru@gmail.com","9879",200,"DEBIT");
        clientDB.createClient("Chirag","cnaru@gmail.com","9879",200,"DEBIT");

        clients = clientDB.getAllClients();

        transactionDB = new TransactionDataSource(getActivity());
        transactionDB.open();
        transactionDB.createTransaction("Praveen",100,"DEBIT","McD","may");
        transactionDB.createTransaction("Praveen",100,"DEBIT","McD","may");
        transactionDB.createTransaction("Praveen",100,"DEBIT","McD","may");
        transactionDB.createTransaction("Praveen",100,"DEBIT","McD","may");
        transactionDB.createTransaction("Praveen",100,"DEBIT","McD","may");
        transactionDB.createTransaction("Praveen",100,"DEBIT","McD","may");
        transactionDB.createTransaction("Praveen",100,"DEBIT","McD","may");
        transactionDB.createTransaction("Chirag",200,"DEBIT","McD","may");

        transactions = transactionDB.getAllTransactions();


      //  RecyclerView.Adapter adapter = new TransactionsDataAdapter(clients, transactions);
         transactionsDataAdapter = new TransactionsDataAdapter(clients, transactions);
        recyclerView.setAdapter(transactionsDataAdapter);
        //view as RecyclerView contains this id
//        Button btnAddTransaction = (Button) recyclerView.findViewById(R.id.btn_add_transaction);
//        btnAddTransaction.setOnClickListener(this);

    }

    private void initDialogForClient() {
        //this :: context = getActivity()
        alertDialogForClient = new AlertDialog.Builder(getActivity());
        viewAddClient = getActivity().getLayoutInflater().inflate(R.layout.layout_add_client_dialog, null);
        alertDialogForClient.setView(viewAddClient);
        alertDialogForClient.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String clientName = etClientName.getText().toString();
                String clientEmail = etClientEmail.getText().toString();
                String clientMobile = etClientMobile.getText().toString();

                if (add) {
                    add = false;

                    transactionsDataAdapter.addItemInClientDB(clientName,clientEmail, clientMobile,0,"CREDIT");
                    dialog.dismiss();
                } else {
                    clientDB = new ClientDataSource(getActivity());
                    clientDB.open();
                    clients.set(edit_position, clientDB.createClient(clientName,clientEmail, clientMobile,0,"CREDIT"));
                    transactionsDataAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                }

            }
        });

        etClientName = (EditText) viewAddClient.findViewById(R.id.et_client_name);
        etClientEmail = (EditText) viewAddClient.findViewById(R.id.et_client_email);
        etClientMobile = (EditText) viewAddClient.findViewById(R.id.et_client_mobile);
    }



    private void initDialogForTransaction() {
        //this :: context = getActivity()
        alertDialogForTransaction = new AlertDialog.Builder(getActivity());
        viewAddTransaction = getActivity().getLayoutInflater().inflate(R.layout.layout_add_transaction_dialog, null);
        alertDialogForTransaction.setView(viewAddTransaction);
        alertDialogForTransaction.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int transactionAmount = Integer.getInteger(etTransactionAmount.getText().toString());
                String transactionType = etTransactionType.getText().toString();

                if (add) {
                    add = false;

                    transactionsDataAdapter.addItemInTransactionDB("cname",transactionAmount, transactionType,"remarks","date");
                    dialog.dismiss();
                } else {
                    transactionDB = new TransactionDataSource(getActivity());
                    transactionDB.open();
                    transactions.set(edit_position, transactionDB.createTransaction("cname",transactionAmount, transactionType,"remarks","date"));
                    transactionsDataAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                }

            }
        });
        etTransactionAmount = (EditText) viewAddTransaction.findViewById(R.id.et_transaction_amount);
        etTransactionType = (EditText) viewAddTransaction.findViewById(R.id.et_transaction_type);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.fab:
                removeView();
                add = true;
                alertDialogForClient.setTitle("Add Client");
                etClientName.setText("");
                etClientEmail.setText("");
                etClientMobile.setText("");

                alertDialogForClient.show();
                break;

            case R.id.btn_add_transaction:
            //    removeView();
                add = true;
                alertDialogForTransaction.setTitle("Add Transaction");
                etTransactionAmount.setText("");
                etTransactionType.setText("");

                alertDialogForTransaction.show();
                break;

        }
    }

    private void removeView() {
        if (viewAddClient.getParent() != null) {
            ((ViewGroup) viewAddClient.getParent()).removeView(viewAddClient);
        }
    }

}
