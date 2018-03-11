package cross;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.duong.mylibrary.R;
import com.facebook.ads.NativeAd;

import inter.OnErrorLoadAd;

/**
 * Created by D on 2/23/2018.
 */

public class CrossNativeAdFragment extends Fragment {
    private NativeAd nativeAd;
    private LinearLayout nativeAdContainer;
    private LinearLayout adView;
    private String idAd;
    private View rootView;


    public void setIdAd(String idAd) {
        this.idAd = idAd;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.cross_nativead_fragment, container, false);
        return rootView;
    }

    LayoutInflater inflater;
    CrossAd.VNCross vnCross;

    private void showNativeAd() {
        vnCross = CrossAd.getACrossAd(getActivity());
        ((TextView) rootView.findViewById(R.id.cross_des)).setText(vnCross.des_tag);
        ((TextView) rootView.findViewById(R.id.cross_title)).setText(vnCross.title_tag);
        Glide.with(getActivity()).load(vnCross.link_icon_tag).into((ImageView) rootView.findViewById(R.id.cross_icon));
        rootView.findViewById(R.id.cross_ad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + vnCross.id_app_android_tag)));
                } catch (ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + vnCross.id_app_android_tag)));
                }
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showNativeAd();
    }

    private OnErrorLoadAd onErrorLoadAd;

    public void setOnErrorLoadAd(OnErrorLoadAd onErrorLoadAd) {
        this.onErrorLoadAd = onErrorLoadAd;

    }


}
