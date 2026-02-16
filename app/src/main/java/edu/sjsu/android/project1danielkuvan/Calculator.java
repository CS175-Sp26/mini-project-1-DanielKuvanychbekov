package edu.sjsu.android.project1danielkuvan;

public class Calculator {

    //I asked ChatGPT to write this method, mine was not accurate for some reason
    public static double calculateResult(double principle, double rate, int years, boolean tax){
        int N = years * 12;

        // Convert annual % to monthly decimal
        double J = (rate / 100.0) / 12.0;

        // Monthly tax = 0.1% of P
        double T = 0;
        if (tax) {
            T = 0.001 * principle;  // 0.1% = 0.001
        }

        double M;

        if (rate == 0) {
            M = (principle / N) + T;
        } else {
            M = (principle * J) / (1 - Math.pow(1 + J, -N)) + T;
        }

        return M;
    }

}
