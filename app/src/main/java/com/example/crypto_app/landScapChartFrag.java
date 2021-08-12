package com.example.crypto_app;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.crypto_app.databinding.FragmentLandScapChartBinding;
import com.example.crypto_app.model.cryptolistmodel.DataItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class landScapChartFrag extends Fragment {
    BottomNavigationView bottomNavigationView;
    FragmentLandScapChartBinding fragmentLandScapChartBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        fragmentLandScapChartBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_land_scap_chart, container, false);

        // get Data from Bundle
        DataItem dataItem = getArguments().getParcelable("model");


        HideBottomNavigationView();
        setupchart(dataItem);
        // Inflate the layout for this fragment
        return fragmentLandScapChartBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        bottomNavigationView.setVisibility(View.VISIBLE);
    }


    private void HideBottomNavigationView() {
        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.GONE);
    }

    // setup Chart (WebView) for first Time
    private void setupchart(DataItem dataItem) {
        fragmentLandScapChartBinding.chartWebview.getSettings().setJavaScriptEnabled(true);
        fragmentLandScapChartBinding.chartWebview.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        fragmentLandScapChartBinding.chartWebview.loadUrl("https://s.tradingview.com/widgetembed/?frameElementId=tradingview_76d87&symbol=" + dataItem.getSymbol() + "USD&interval=D&hidesidetoolbar=1&symboledit=1&saveimage=1&toolbarbg=F1F3F6&studies=[]&hideideas=1&theme=Dark&style=1&timezone=Etc%2FUTC&studies_overrides={}&overrides={}&enabled_features=[]&disabled_features=[]&locale=en&utm_source=coinmarketcap.com&utm_medium=widget&utm_campaign=chart&utm_term=BTCUSDT");

    }
}