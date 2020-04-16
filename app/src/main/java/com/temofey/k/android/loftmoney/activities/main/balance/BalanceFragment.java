package com.temofey.k.android.loftmoney.activities.main.balance;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.temofey.k.android.loftmoney.App;
import com.temofey.k.android.loftmoney.R;
import com.temofey.k.android.loftmoney.components.DiagramView;
import com.temofey.k.android.loftmoney.data.api.WebFactory;
import com.temofey.k.android.loftmoney.data.prefs.Prefs;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class BalanceFragment extends Fragment implements BalanceViewState {

    private BalancePresenter balancePresenter;
    private AppCompatTextView balanceTextView;
    private AppCompatTextView incomeTextView;
    private AppCompatTextView outcomeTextView;
    private DiagramView diagramView;
    private List<Disposable> disposables = new ArrayList<>();

    public static BalanceFragment newInstance() {
        return new BalanceFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_balance, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        diagramView = view.findViewById(R.id.dvBalance);
        balanceTextView = view.findViewById(R.id.txtBalanceAvailable);
        incomeTextView = view.findViewById(R.id.txtBalanceIncomes);
        outcomeTextView = view.findViewById(R.id.txtBalanceOutcomes);

        balancePresenter = new BalancePresenter();
        balancePresenter.setBalanceViewState(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        Activity activity = getActivity();
        Context context = getContext();

        if (context == null | activity == null) {
            return;
        }

        Prefs prefs = ((App) activity.getApplication()).getPrefs();
        String token = prefs.getToken();

        disposables.add(balancePresenter.fetchBalance(context, WebFactory.getInstance().getItemsRequest(), token));
    }

    @Override
    public void onStop() {

        for (Disposable disposable : disposables) {
            disposable.dispose();
        }
        disposables.clear();
        super.onStop();
    }


    @Override
    public void setBalance(String balance) {
        balanceTextView.setText(balance);
    }

    @Override
    public void setIncome(String income) {
        incomeTextView.setText(income);
    }

    @Override
    public void setOutcome(String outcome) {
        outcomeTextView.setText(outcome);
    }

    @Override
    public void setDiagram(int outcome, int income) {
        diagramView.update(outcome, income);
    }
}
