package com.example.pawrescue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;

import com.example.pawrescue.databinding.ActivityDonationCenterBinding;
import org.json.JSONObject;

import java.io.InputStream;

public class DonationCenter extends AppCompatActivity {

    private ActivityDonationCenterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_center);

        binding = ActivityDonationCenterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        try {
            Resources resources = getResources();
            InputStream inputStream = resources.openRawResource(R.raw.bank_info);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, "UTF-8");

            // Parse the JSON string
            JSONObject jsonObject = new JSONObject(json);

            // Access the individual fields from the JSON object
            String bankName = jsonObject.getString("bank_name");
            String iban = jsonObject.getString("iban");
            String accountOwner = jsonObject.getString("account_owner");


            binding.bankName.setText(bankName);
            binding.ibanNumber.setText(iban);
            binding.accountOwner.setText(accountOwner);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}