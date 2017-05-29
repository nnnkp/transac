package com.nnnkp.transac;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by npraj1 on 5/26/2017.
 */

public class TransactionsDataAdapter extends RecyclerView.Adapter<TransactionsDataAdapter.ViewHolder> {

    private TransactionDataSource transactionDB;
    private ClientDataSource clientDB;

    List<Client> clientList = new ArrayList<>();
    List<Transaction> transactionList =  new ArrayList<>();

//    private List<Client> clientList;
//    private List<Transaction> transactionList;
    private AlertDialog.Builder alertDialog;
    private EditText etTransactionAmount, etTransactionType, etClientName, etClientEmail, etClientMobile;

    private boolean add = false;

    private View view;
    private RecyclerView recyclerView;

    public TransactionsDataAdapter(List<Client> clientList, List<Transaction> transactionList) {
        this.clientList = clientList;
        this.transactionList = transactionList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        view = inflater.inflate(R.layout.transactions_summary, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TransactionsDataAdapter.ViewHolder holder, int i) {
        holder.tvClientName.setText(clientList.get(i).getClientName());
        holder.tvNetTransactionType.setText(clientList.get(i).getNetType());
        holder.tvNetTransactionAmount.setText(String.valueOf(clientList.get(i).getNetAmount()));

        holder.tvPrevTransaction1.setText(String.valueOf(transactionList.get(i).getAmount()));
        holder.tvPrevTransaction2.setText(String.valueOf(transactionList.get(i).getAmount()));
        holder.tvPrevTransaction3.setText(String.valueOf(transactionList.get(i).getAmount()));
        holder.tvPrevTransaction4.setText(String.valueOf(transactionList.get(i).getAmount()));
        holder.tvPrevTransaction5.setText(String.valueOf(transactionList.get(i).getAmount()));

    //    holder.btnAddTransaction.setOnClickListener(addTransactionListener);
    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    public void addItemInTransactionDB(String clientName, int transactionAmount, String transactionType,String remarks,String transactionDate) {
        transactionDB = new TransactionDataSource(view.getContext());
        transactionDB.open();
        transactionList.add(transactionDB.createTransaction(clientName, transactionAmount, transactionType, remarks, transactionDate));
        notifyItemInserted(transactionList.size());
    }

    public void addItemInClientDB(String clientName, String clientEmail, String clientMobile,int netAmount,String netType) {
        clientDB = new ClientDataSource(view.getContext());
        clientDB.open();
        clientList.add(clientDB.createClient(clientName, clientEmail, clientMobile, netAmount, netType));
        notifyItemInserted(clientList.size());
    }
    public void removeItem(int position) {
        transactionList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, transactionList.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvClientName;
        private TextView tvNetTransactionType;
        private TextView tvNetTransactionAmount;

        private TextView tvPrevTransaction1;
        private TextView tvPrevTransaction2;
        private TextView tvPrevTransaction3;
        private TextView tvPrevTransaction4;
        private TextView tvPrevTransaction5;


        private Button btnTransactionHistory;
        private Button btnAddTransaction;


        public ViewHolder(View itemView) {

            super(itemView);
            tvClientName = (TextView) itemView.findViewById(R.id.tv_client_name);
            tvNetTransactionType = (TextView) itemView.findViewById(R.id.tv_net_transaction_type);
            tvNetTransactionAmount = (TextView) itemView.findViewById(R.id.tv_net_transaction_amount);

            tvPrevTransaction1 = (TextView) itemView.findViewById(R.id.tv_prev_transaction_1);
            tvPrevTransaction2 = (TextView) itemView.findViewById(R.id.tv_prev_transaction_2);
            tvPrevTransaction3 = (TextView) itemView.findViewById(R.id.tv_prev_transaction_3);
            tvPrevTransaction4 = (TextView) itemView.findViewById(R.id.tv_prev_transaction_4);
            tvPrevTransaction5 = (TextView) itemView.findViewById(R.id.tv_prev_transaction_5);

            btnTransactionHistory = (Button) itemView.findViewById(R.id.btn_transaction_history);
            btnAddTransaction = (Button) itemView.findViewById(R.id.btn_add_transaction);


        }
    }

   /* private View.OnClickListener addTransactionListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            initDialog();
        }
    }*/

 /*   private void initDialog() {
        alertDialog = new AlertDialog.Builder(this);
        view = getLayoutInflater().inflate(R.layout.layout_add_transaction_dialog, null);
        alertDialog.setView(view);
        alertDialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String transactionAmount = etTransactionAmount.getText().toString();
                String transactionType = etTransactionType.getText().toString();

                if (add) {
                    add = false;

                    dataAdapter.addItem(employeeName, employeeAge, employeeDomain, employeeProject, employeeImageBitmap);
                    dialog.dismiss();
                } else {
                    employees.set(edit_position, new Employee(employeeName, employeeAge, employeeDomain, employeeProject, employeeImageBitmap));
                    dataAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                }

            }
        });
        etTransactionAmount = (EditText) view.findViewById(R.id.et_transaction_amount);
        etTransactionType = (EditText) view.findViewById(R.id.et_transaction_type);
    }*/
 /*   private void initViews() {


        recyclerView = (RecyclerView) view.findViewById(R.id.rv_transactions);
        recyclerView.setHasFixedSize(true);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        employeeImageBitmap = getImageFromDB();


        //     employees = new ArrayList<>(Arrays.asList(emp1, emp2, emp3, emp4, emp5, emp6, emp7, emp8));


        dataAdapter = new DataAdapter(employees);
        recyclerView.setAdapter(dataAdapter);
        dataAdapter.notifyDataSetChanged();


        initSwipe();

    }*/


}
