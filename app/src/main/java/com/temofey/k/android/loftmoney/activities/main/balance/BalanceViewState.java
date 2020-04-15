package com.temofey.k.android.loftmoney.activities.main.balance;

public interface BalanceViewState {

    void setBalance(String balance);

    void setIncome(String income);

    void setOutcome(String outcome);

    void setDiagram(int outcome, int income);
}
