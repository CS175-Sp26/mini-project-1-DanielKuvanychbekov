package edu.sjsu.android.project1danielkuvan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import edu.sjsu.android.project1danielkuvan.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;//here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();

        //
        EdgeToEdge.enable(this);
        setContentView(root);
//        setContentView(R.layout.activity_main);
        //Also here I changed, added root instead
        ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Honestly this class I asked from ChatGPT so that it won't take more than 2 numbers after decimal point
        binding.principleEditText.setFilters(new InputFilter[] {
                new DecimalDigitInputFilter(2)
        });

        binding.InterestRate.setText(String.format("Rate: %.1f", (float)binding.seekBar.getProgress()));

        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                double rate = progress / 10.0;//let's us to add up by 0.1, the max is 200 in activity.main

                binding.InterestRate.setText(String.format("Rate: %.1f", (float)rate));
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        binding.CalculateBtn.setOnClickListener(v -> {
            calculate();
        });

        binding.DeleteBtn.setOnClickListener(v -> {
            uninstall();
        });

    }

    private void calculate(){
        //to get a amount for principle from EditText, 2 number after the decimal point is max
        String text = binding.principleEditText.getText().toString();
        double  principle = text.isEmpty() ? 0d : Double.parseDouble(text);

        boolean isValid = isValid();
        if(!isValid){
            return;
        }

        //years from Radio Buttons Group
        int years = getYears();

        //true or false from check box
        boolean tax = binding.taxCheckBox.isChecked();

        //amount from seekBar
        double rate = (double) (binding.seekBar.getProgress()) / 10.0;

        //calculate result using our own created Calculator class
        double result = Calculator.calculateResult(principle, rate, years, tax);

        //
        binding.CalculateResultText.setText(getString(R.string.result_text, result));
    }

    private int getYears(){
        int checkedId = binding.years.getCheckedRadioButtonId();

        if (checkedId == R.id.radioButton) return 15;
        else if (checkedId == R.id.radioButton2) return 20;
        else return 30;
    }

    private boolean isValid() {

        String text = binding.principleEditText.getText().toString().trim();

        if (text.isEmpty()) {
            binding.PrincipleIsValidText.setText("Please enter the principle.");
            return false;
        }

        try {
            double value = Double.parseDouble(text);

            if (value <= 0) {
                binding.PrincipleIsValidText.setText("Amount must be greater than 0.");
                return false;
            } else {
                binding.PrincipleIsValidText.setText("Valid amount entered.");
                return true;
            }

        } catch (NumberFormatException e) {
            binding.PrincipleIsValidText.setText("Invalid number.");
            return false;
        }
    }
    
    private void uninstall(){
        Intent delete = new Intent(Intent.ACTION_DELETE, Uri.parse("package:" + getPackageName()));
        startActivity(delete);
    }
}