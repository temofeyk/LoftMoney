package com.temofey.k.android.loftmoney.activities.main.balance;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import com.temofey.k.android.loftmoney.data.api.GetItemsRequest;
import com.temofey.k.android.loftmoney.data.api.model.ItemRemote;
import com.temofey.k.android.loftmoney.data.model.Item;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

class BalancePresenter {

    private BalanceViewState balanceViewState;

    void setBalanceViewState(BalanceViewState balanceViewState) {
        this.balanceViewState = balanceViewState;
    }

    @SuppressLint("CheckResult")
    Disposable fetchBalance(Context context, GetItemsRequest getItemsRequest, String authToken) {
        Single<List<ItemRemote>> outcomeRequest = getItemsRequest.request(GetItemsRequest.EXPENSE, authToken);
        Single<List<ItemRemote>> incomeRequest = getItemsRequest.request(GetItemsRequest.INCOME, authToken);

        return Single.zip(outcomeRequest, incomeRequest, (outcomes, incomes) -> {
            List<Item> totalFinances = new ArrayList<>(outcomes.size() + incomes.size());

            for (ItemRemote item : outcomes) {
                totalFinances.add(new Item(item, true));
            }

            for (ItemRemote item : incomes) {
                totalFinances.add(new Item(item, false));
            }

            return totalFinances;
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Items -> {
                    int balance = 0;
                    int income = 0;
                    int outcome = 0;

                    for (Item Item : Items) {
                        if (Item.isOutcome()) {
                            outcome += Item.getPrice();
                            balance -= Item.getPrice();
                        } else {
                            income += Item.getPrice();
                            balance += Item.getPrice();
                        }
                    }
                    balanceViewState.setBalance(balance + "₽");
                    balanceViewState.setIncome(income + "₽");
                    balanceViewState.setOutcome(outcome + "₽");
                    balanceViewState.setDiagram(outcome, income);
                }, throwable -> Toast.makeText(context, throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show());
    }
}
