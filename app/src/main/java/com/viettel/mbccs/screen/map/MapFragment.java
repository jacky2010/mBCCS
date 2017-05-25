package com.viettel.mbccs.screen.map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.google.android.gms.maps.SupportMapFragment;

public class MapFragment extends SupportMapFragment {

    private View mRoot;
    private OnListenerChangeLayout mOnListenerChangeLayout;
    private boolean isChangeLayout;
    private MapWrapperLayout mMapWrapperLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup view,
                             Bundle savedInstance) {
        if (mRoot != null) {
            ViewGroup parent = (ViewGroup) mRoot.getParent();
            if (parent != null) {
                parent.removeAllViews();
            }
        }
        mRoot = super.onCreateView(inflater, view, savedInstance);
        mMapWrapperLayout = new MapWrapperLayout(getActivity());
        mMapWrapperLayout.setBackgroundResource(android.R.color.transparent);
        mMapWrapperLayout.addView(mRoot, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        return mMapWrapperLayout;
    }

    @Nullable
    @Override
    public View getView() {
        return mRoot;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mRoot = view;
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        mRoot.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        if (mOnListenerChangeLayout != null && !isChangeLayout) {
                            mOnListenerChangeLayout
                                    .onChangeLayout(mRoot.getWidth(), mRoot.getHeight());
                            isChangeLayout = true;
                        }
                    }
                });
    }

    public void setOnListenerChangeLayout(OnListenerChangeLayout mOnListenerChangeLayout) {
        this.mOnListenerChangeLayout = mOnListenerChangeLayout;
    }

    public void setOnListenerUpdateMarker(MapWrapperLayout
                                                  .OnListenerUpdateMarker mOnListenerUpdateMarker) {
        if (mMapWrapperLayout != null) {
            mMapWrapperLayout.setOnListenerUpdateMarker(mOnListenerUpdateMarker);
        }
    }

    public interface OnListenerChangeLayout {
        void onChangeLayout(int w, int h);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}