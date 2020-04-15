package com.temofey.k.android.loftmoney.activities.main;

import android.os.Bundle;
import android.view.ActionMode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.temofey.k.android.loftmoney.R;
import com.temofey.k.android.loftmoney.activities.main.balance.BalanceFragment;
import com.temofey.k.android.loftmoney.activities.main.budget.BudgetFragment;
import com.temofey.k.android.loftmoney.data.api.GetItemsRequest;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabs);
        toolbar = findViewById(R.id.toolbar);
        ViewPager2 viewPager = findViewById(R.id.viewpager);

        BudgetPagerStateAdapter budgetPagerStateAdapter = new BudgetPagerStateAdapter(this);
        viewPager.setAdapter(budgetPagerStateAdapter);
        String[] pagesTitles = getResources().getStringArray(R.array.main_tabs);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(pagesTitles[position])
        ).attach();
    }

    class BudgetPagerStateAdapter extends FragmentStateAdapter {
        final static int PAGE_OUTCOMES = 0;
        final static int PAGE_INCOMES = 1;

        private final static int NUM_PAGES = 3;

        BudgetPagerStateAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {

            switch (position) {
                case PAGE_OUTCOMES:
                return BudgetFragment.newInstance(R.color.dark_sky_blue, GetItemsRequest.EXPENSE);
                case PAGE_INCOMES:
                    return BudgetFragment.newInstance(R.color.apple_green, GetItemsRequest.INCOME);
                default:
                    return BalanceFragment.newInstance();
            }

        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }


    @Override
    public void onActionModeStarted(ActionMode mode) {
        super.onActionModeStarted(mode);
        tabLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_grey_blue));
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_grey_blue));
    }

    @Override
    public void onActionModeFinished(ActionMode mode) {
        super.onActionModeFinished(mode);
        tabLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
    }
}
