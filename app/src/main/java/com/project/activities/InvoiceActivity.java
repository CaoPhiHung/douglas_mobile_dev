package com.project.activities;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.project.db.Invoice;
import com.project.db.InvoiceItem;
import com.project.db.User;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InvoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        SimpleDateFormat df = new SimpleDateFormat("MMMM d, YYYY");

        // get invoice
        User currentUser = User.getCurrentUser();
        Invoice invoice = Invoice.getByUser(currentUser.id);

        // fill-in user info
        TextView tvName = findViewById(R.id.cellName);
        TextView tvPhone = findViewById(R.id.cellPhone);
        TextView tvdate = findViewById(R.id.cellDate);
        tvName.setText(currentUser.name);
        tvPhone.setText(currentUser.phone);
        tvdate.setText(df.format(new Date(invoice.date)));

        // set table
        TableLayout tb = findViewById(R.id.tablePurchases);
        tb.setStretchAllColumns(true);

        for (InvoiceItem item : invoice.invoiceItems){
            TableRow row = new TableRow(this);
            row.setPadding(0, 5, 0, 5);
            // heading
            TextView heading = new TextView(this);
            heading.setText(item.name);
            heading.setTextSize(16);
            heading.setTextColor(Color.parseColor("#3f3f3f"));

            TextView value = new TextView(this);
            value.setText(String.format("$%.2f", item.price));
            value.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            value.setTextSize(16);
            value.setTextColor(Color.parseColor("#3f3f3f"));
            row.addView(heading);
            row.addView(value);
            tb.addView(row);
        }

        // close button
        Button btnClose = findViewById(R.id.btnCloseInvoice);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
