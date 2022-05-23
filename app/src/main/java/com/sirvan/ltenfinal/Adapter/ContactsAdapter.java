package com.sirvan.ltenfinal.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.sirvan.ltenfinal.Activity.ActivityContacts;
import com.sirvan.ltenfinal.Activity.MainActivity;
import com.sirvan.ltenfinal.App.G;
import com.sirvan.ltenfinal.App.MyAlertDialog;
import com.sirvan.ltenfinal.App.SMSUtils;
import com.sirvan.ltenfinal.Model.ContactListModel;
import com.sirvan.ltenfinal.R;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyHolder> {

    Context context;
    List<ContactListModel> data;
    Activity activity;
    String number;
    String password;

    private MyAlertDialog myAlertDialog;

    public ContactsAdapter(Context context, Activity activity, List<ContactListModel> data, String number, String password) {
        this.context = context;
        this.data = data;
        this.number = number;
        this.password = password;
        this.activity = activity;

        myAlertDialog = new MyAlertDialog(activity,context);

    }

    @NonNull
    @Override
    public ContactsAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.rv_contacts_list, parent, false);
        return new ContactsAdapter.MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ContactsAdapter.MyHolder holder, final int position) {
        holder.txtName.setText(data.get(position).getName());
        holder.rvTextViewDeleteUser.setTypeface(G.face);
        holder.rvTextViewChangeUser.setTypeface(G.face);
        holder.txtName.setTypeface(G.faceBold);


        holder.rvLinearLayoutDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String func = "*09*"+position+"*" + "0000" + "#";
              //  Alert("آیا میخواهید شمار کاربر را از روی دستگاه حذف کنید؟", func);
                myAlertDialog.Show("آیا میخواهید شمار کاربر را از روی دستگاه حذف کنید؟",func,number,password);

            }
        });
        holder.rvLinearLayoutChangeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userNumber = holder.edtPhone.getText().toString();
                if (checkNumber(userNumber)) {
                    String func = "*09*"+position+"*" + userNumber + "#";
                  //  Alert("آیا میخواهید شمار کاربر را عوض کنید؟", func);
                    myAlertDialog.Show("آیا میخواهید شمار کاربر را عوض کنید؟",func,number,password);

                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView txtName, rvTextViewDeleteUser, rvTextViewChangeUser;
        EditText edtPhone;
        LinearLayout rvLinearLayoutDeleteUser, rvLinearLayoutChangeUser;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.rvTextViewUserName);
            rvTextViewDeleteUser = itemView.findViewById(R.id.rvTextViewDeleteUser);
            rvTextViewChangeUser = itemView.findViewById(R.id.rvTextViewChangeUser);


            edtPhone = itemView.findViewById(R.id.rvEditTextUserPhone);
            rvLinearLayoutDeleteUser = itemView.findViewById(R.id.rvLinearLayoutDeleteUser);
            rvLinearLayoutChangeUser = itemView.findViewById(R.id.rvLinearLayoutChangeUser);

        }

    }


   /* private void Alert(String note, final String func) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle(context.getString(R.string.alert));
        alert.setMessage(note);
        alert.setPositiveButton(context.getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                SMSUtils.sendSMS(context, number, "*#" + password + func);
            }
        });
        alert.setNegativeButton(context.getString(R.string.no), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "دستور لغو شد", Toast.LENGTH_LONG).show();
                alert.setCancelable(true);
            }
        });
        alert.show();
    }*/

    private boolean checkNumber(String number) {

        if (number.length() == 11) {
            return true;
        } else {
            Toast.makeText(context, "شماره موبایل صحیح نیست", Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
