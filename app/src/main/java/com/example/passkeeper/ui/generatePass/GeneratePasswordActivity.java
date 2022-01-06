package com.example.passkeeper.ui.generatePass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.passkeeper.R;
import com.example.passkeeper.data.model.PasswordGenerator;
import com.example.passkeeper.databinding.ActivityGeneratePasswordBinding;
import com.travijuu.numberpicker.library.Enums.ActionEnum;
import com.travijuu.numberpicker.library.Interface.ValueChangedListener;

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

        binding.swtUppercase.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
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
            }
        });

        binding.swtLowercase.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
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
            }
        });

        binding.swtNumber.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
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
            }
        });

        binding.swtSpecial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
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
            }
        });

        binding.npkMinNumber.setValueChangedListener(new ValueChangedListener() {
            @Override
            public void valueChanged(int value, ActionEnum action) {
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
            }
        });

        binding.npkMinSpecial.setValueChangedListener(new ValueChangedListener() {
            @Override
            public void valueChanged(int value, ActionEnum action) {
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
            }
        });

        binding.btnGenPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordGenerator.setmLength(binding.sbrLength.getProgress());
                passwordGenerator.setmHaveUpper(binding.swtUppercase.isChecked());
                passwordGenerator.setmHaveLower(binding.swtLowercase.isChecked());
                passwordGenerator.setmHaveNumber(binding.swtNumber.isChecked());
                passwordGenerator.setmHaveSpecial(binding.swtSpecial.isChecked());
                passwordGenerator.setmMinNumber(binding.npkMinNumber.getValue());
                passwordGenerator.setmMinSpecial(binding.npkMinSpecial.getValue());
                String password = passwordGenerator.GeneratePassword();
                binding.txvPassword.setText(password);
            }
        });

        binding.btnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Copied Password", binding.txvPassword.getText());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(GeneratePasswordActivity.this, "Copied Password!", Toast.LENGTH_SHORT).show();
            }
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
    };
}