package sg.edu.rp.c346.id20011262.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    TextView tvDisplaytotalBillPayable;
    TextView tvDisplayEachPayable;
    TextView tvStore;
    Button btnClickSplit;
    Button btnClickReset;
    EditText etInputDiscount;
    EditText etInputAmt;
    EditText etInputPax;
    ToggleButton tbtnSVS;
    ToggleButton tbtnGST;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tbtnSVS = findViewById(R.id.toggleButtonSVS);
        tbtnGST = findViewById(R.id.toggleButtonGST);
        etInputAmt = findViewById(R.id.editTextAmt);
        etInputPax = findViewById(R.id.editTextPax);
        btnClickSplit = findViewById(R.id.buttonSplit);
        btnClickReset = findViewById(R.id.buttonReset);
        tvDisplaytotalBillPayable = findViewById(R.id.textViewTotalBillPayable);
        tvDisplayEachPayable = findViewById(R.id.textViewEachPay);
        rg = findViewById(R.id.radiogroupPayment);
        etInputDiscount = findViewById(R.id.editTextDiscount);

        double cost = 0.00;

        tbtnSVS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 double original = Double.parseDouble(etInputAmt.getText().toString());
                double newest = 0.00;
;                if(!tbtnSVS.isChecked() && !tbtnGST.isChecked()) {
                    newest = original;
                } else if(tbtnSVS.isChecked() && !tbtnGST.isChecked()) {
                    newest = original * 1.1;
                } else if(!tbtnSVS.isChecked() && tbtnGST.isChecked()) {
                    newest = original * 1.07;
                } else if(tbtnSVS.isChecked() && tbtnGST.isChecked()) {
                    newest = original * 1.17;
                }
                double cost = newest;
            }
        });

        rg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double userInput = Double.parseDouble(etInputAmt.getText().toString());
                double userpayable = Double.parseDouble(etInputAmt.getText().toString());
                double discount = Double.parseDouble(etInputDiscount.getText().toString());
                int pax = Integer.parseInt(etInputPax.getText().toString());
                if(rg.getCheckedRadioButtonId() == R.id.radioButtonCash) {
                    double total = (userInput*discount);
                    double payable = total/pax;
                    tvDisplaytotalBillPayable.setText(total+"");
                    tvDisplayEachPayable.setText(payable+" in cash");
                } else if(rg.getCheckedRadioButtonId() == R.id.radioButtonPaynow) {
                    double total = (userInput*discount);
                    double payable = total/pax;
                    tvDisplaytotalBillPayable.setText(total+"");
                    tvDisplayEachPayable.setText(payable+" via PayNow to 912345678");
                }
            }
        });

        btnClickReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etInputAmt.setText("");
                etInputPax.setText("");
                etInputDiscount.setText("");
                rg.clearCheck();
                tbtnGST.setChecked(false);
                tvDisplaytotalBillPayable.setText("");
                tvDisplayEachPayable.setText("");
            }
        });

    }
}