package com.example.passkeeper.ui.generatePass;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.passkeeper.data.model.PasswordGenerator;
import com.example.passkeeper.databinding.ActivityGeneratePasswordBinding;
import com.travijuu.numberpicker.library.Enums.ActionEnum;

public class GeneratePasswordActivity extends AppCompatActivity {

    ActivityGeneratePasswordBinding binding;
    PasswordGenerator passwordGenerator = new PasswordGenerator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGeneratePasswordBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.txvPassword.setSelected(true);
        binding.txvLengthValue.setText(String.valueOf(binding.sbrLength.getProgress()));

        binding.sbrLength.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int minValue = getMinValidValueOfSeekbar();
                if(progress < minValue)
                    seekBar.setProgress(minValue);
                else
                    binding.txvLengthValue.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        binding.swtUppercase.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Log.v("Switch State",String.valueOf(isChecked));
            Log.v("Progress",String.valueOf(binding.sbrLength.getProgress()));
            Log.v("Min Progress",String.valueOf(getMinValidValueOfSeekbar()));
            if(!isChecked)
            {
                if(!binding.swtLowercase.isChecked() && !binding.swtNumber.isChecked() && !binding.swtSpecial.isChecked())
                    buttonView.setChecked(true);
            }
            else if (getMinValidValueOfSeekbar() - 1 == binding.sbrLength.getMax())
                buttonView.setChecked(false);
            else if (binding.sbrLength.getProgress() == getMinValidValueOfSeekbar() - 1)
                binding.sbrLength.incrementProgressBy(1);
        });

        binding.swtLowercase.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Log.v("Switch State",String.valueOf(isChecked));
            Log.v("Progress",String.valueOf(binding.sbrLength.getProgress()));
            Log.v("Min Progress",String.valueOf(getMinValidValueOfSeekbar()));
            if(!isChecked)
            {
                if(!binding.swtUppercase.isChecked() && !binding.swtNumber.isChecked() && !binding.swtSpecial.isChecked())
                    buttonView.setChecked(true);
            }
            else if (getMinValidValueOfSeekbar() - 1 == binding.sbrLength.getMax())
                buttonView.setChecked(false);
            else if (binding.sbrLength.getProgress() == getMinValidValueOfSeekbar() - 1)
                binding.sbrLength.incrementProgressBy(1);
        });

        binding.swtNumber.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Log.v("Switch State",String.valueOf(isChecked));
            if(!isChecked)
            {
                if(!binding.swtUppercase.isChecked() && !binding.swtLowercase.isChecked() && !binding.swtSpecial.isChecked())
                    buttonView.setChecked(true);
                else {
                    binding.npkMinNumber.setMin(0);
                    binding.npkMinNumber.setValue(0);
                }
            }
            else if (getMinValidValueOfSeekbar() == binding.sbrLength.getMax())
            {
                buttonView.setChecked(false);
            }
            else
            {
                binding.npkMinNumber.setMin(1);
                binding.npkMinNumber.setValue(1);
                Log.v("Progress",String.valueOf(binding.sbrLength.getProgress()));
                Log.v("Min Progress",String.valueOf(getMinValidValueOfSeekbar()));
                if(binding.sbrLength.getProgress() == getMinValidValueOfSeekbar() - 1)
                    binding.sbrLength.incrementProgressBy(1);
            }
        });

        binding.swtSpecial.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Log.v("Switch State",String.valueOf(isChecked));
            if(!isChecked)
            {
                if(!binding.swtUppercase.isChecked() && !binding.swtLowercase.isChecked() && !binding.swtNumber.isChecked())
                    buttonView.setChecked(true);
                else {
                    binding.npkMinSpecial.setMin(0);
                    binding.npkMinSpecial.setValue(0);
                }
            }
            else if (getMinValidValueOfSeekbar() == binding.sbrLength.getMax())
            {
                buttonView.setChecked(false);
            }
            else
            {
                binding.npkMinSpecial.setMin(1);
                binding.npkMinSpecial.setValue(1);
                Log.v("Progress",String.valueOf(binding.sbrLength.getProgress()));
                Log.v("Min Progress",String.valueOf(getMinValidValueOfSeekbar()));
                if(binding.sbrLength.getProgress() == getMinValidValueOfSeekbar() - 1)
                    binding.sbrLength.incrementProgressBy(1);
            }
        });

        binding.npkMinNumber.setValueChangedListener((value, action) -> {
            if(action == ActionEnum.INCREMENT)
            {
                binding.swtNumber.setChecked(true);
                Log.v("Progress",String.valueOf(binding.sbrLength.getProgress()));
                Log.v("Min Progress",String.valueOf(getMinValidValueOfSeekbar()));
                if(getMinValidValueOfSeekbar() - 1 == binding.sbrLength.getMax())
                    binding.npkMinNumber.decrement(1);
                else if(binding.sbrLength.getProgress() == getMinValidValueOfSeekbar() - 1)
                {
                    binding.sbrLength.incrementProgressBy(1);
                }
            }
        });

        binding.npkMinSpecial.setValueChangedListener((value, action) -> {
            if(action == ActionEnum.INCREMENT)
            {
                binding.swtSpecial.setChecked(true);
                Log.v("Progress",String.valueOf(binding.sbrLength.getProgress()));
                Log.v("Min Progress",String.valueOf(getMinValidValueOfSeekbar()));
                if(getMinValidValueOfSeekbar() - 1 == binding.sbrLength.getMax())
                    binding.npkMinSpecial.decrement(1);
                else if(binding.sbrLength.getProgress() == getMinValidValueOfSeekbar() - 1)
                {
                    binding.sbrLength.incrementProgressBy(1);
                }
            }
        });

        binding.btnGenPass.setOnClickListener(v -> {
            passwordGenerator.setLength(binding.sbrLength.getProgress());
            passwordGenerator.setHaveUpper(binding.swtUppercase.isChecked());
            passwordGenerator.setHaveLower(binding.swtLowercase.isChecked());
            passwordGenerator.setHaveNumber(binding.swtNumber.isChecked());
            passwordGenerator.setHaveSpecial(binding.swtSpecial.isChecked());
            passwordGenerator.setMinNumber(binding.npkMinNumber.getValue());
            passwordGenerator.setMinSpecial(binding.npkMinSpecial.getValue());
            String password = passwordGenerator.GeneratePassword();
            binding.txvPassword.setText(password);
        });

        binding.btnCopy.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Copied Password", binding.txvPassword.getText());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(GeneratePasswordActivity.this, "Copied Password!", Toast.LENGTH_SHORT).show();
        });
    }

    public int getMinValidValueOfSeekbar()
    {
        int minValue = (binding.swtUppercase.isChecked() ? 1 : 0) +
                (binding.swtNumber.isChecked() ? 1 : 0) +
                (binding.swtLowercase.isChecked() ? 1 : 0) +
                (binding.swtSpecial.isChecked() ? 1 : 0) +
                binding.npkMinNumber.getValue() +
                binding.npkMinSpecial.getValue();

        if(binding.swtNumber.isChecked())
            minValue -= 1;
        if(binding.swtSpecial.isChecked())
            minValue -= 1;

        return minValue;
    }
}