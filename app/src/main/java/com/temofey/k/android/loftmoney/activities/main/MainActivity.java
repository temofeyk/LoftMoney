package com.temofey.k.android.loftmoney.activities.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.temofey.k.android.loftmoney.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tabs);
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

        private final static int NUM_PAGES = 2;

        BudgetPagerStateAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return new BudgetFragment(position);
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }
}
