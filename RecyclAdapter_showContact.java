package com.example.contactdayari;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclAdapter_showContact extends RecyclerView.Adapter<RecyclAdapter_showContact.ViewHolder> {

    ShowAllContactActivity showAllContactActivity;
    ArrayList<PersonData_store> arrayList;

    public RecyclAdapter_showContact(ShowAllContactActivity showAllContactActivity, ArrayList<PersonData_store> arrayList) {
        this.showAllContactActivity = showAllContactActivity;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(showAllContactActivity).inflate(R.layout.aapdu_layout_recycl, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        PersonData_store personDataStore = new PersonData_store();
        personDataStore = arrayList.get(position);

        //String firstlatter = personDataStore.getName().substring(0, 1);

        int id = personDataStore.getId();
        String name = personDataStore.getName();
        String contact = personDataStore.getContact();

        holder.first_ltr.setText(name.substring(0, 1));
        holder.tvname.setText(name);
        holder.tvcontact.setText(contact);

        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(showAllContactActivity);
                dialog.setContentView(R.layout.delete_dialog);

                Button btn_yes, btn_no;
                btn_yes = dialog.findViewById(R.id.btn_yes);
                btn_no = dialog.findViewById(R.id.btn_no);

                dialog.setCanceledOnTouchOutside(false);       //aa dialog no bar touch karva thi. dialog remove nathi thato. aa no lakhyu hoy to bar touch karva thi remove thay jay.

                btn_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();

                        ShowAllContactActivity.myDatabase.deleteData(id);

                        arrayList.remove(position);
                        notifyDataSetChanged();
                    }
                });
                btn_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        holder.iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(showAllContactActivity);
                dialog.setContentView(R.layout.update_dialog);

                EditText new_name, new_contact;
                Button btn_update;
                new_name = dialog.findViewById(R.id.new_name);
                new_contact = dialog.findViewById(R.id.new_contact);
                btn_update = dialog.findViewById(R.id.btn_update);

                new_name.setText(name);
                new_contact.setText(contact);

                btn_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();

                        String str_name = new_name.getText().toString();
                        String str_contact = new_contact.getText().toString();

                        showAllContactActivity.myDatabase.updateData(id, str_name, str_contact);

                        arrayList.remove(position);
                        PersonData_store personDataStore1 = new PersonData_store();
                        personDataStore1.setName(str_name);
                        personDataStore1.setContact(str_contact);

                        arrayList.add(position, personDataStore1);

                        notifyDataSetChanged();
                    }
                });

                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvname, tvcontact, first_ltr;
        ImageView iv_delete, iv_edit;

        public ViewHolder(@NonNull View view) {
            super(view);

            tvname = view.findViewById(R.id.tvname);
            tvcontact = view.findViewById(R.id.tvcontact);

            first_ltr = view.findViewById(R.id.first_ltr);

            iv_delete = view.findViewById(R.id.iv_delete);
            iv_edit = view.findViewById(R.id.iv_edit);
        }
    }
}
