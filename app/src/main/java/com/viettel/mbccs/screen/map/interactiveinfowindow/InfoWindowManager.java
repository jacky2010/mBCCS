/*
 * Copyright (c) 2016 Appolica Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.
 *
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under
 * the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.viettel.mbccs.screen.map.interactiveinfowindow;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.LatLng;
import com.viettel.mbccs.R;
import com.viettel.mbccs.screen.map.interactiveinfowindow.animation.SimpleAnimationListener;
import com.viettel.mbccs.screen.map.interactiveinfowindow.customview.TouchInterceptFrameLayout;


public class InfoWindowManager
        implements GoogleMap.OnCameraIdleListener,
        GoogleMap.OnCameraMoveStartedListener,
        GoogleMap.OnCameraMoveListener,
        GoogleMap.OnCameraMoveCanceledListener,
        GoogleMap.OnMapClickListener {

    public static final String FRAGMENT_TAG_INFO = "InfoWindow";

    private static final String TAG = "InfoWindowManager";

    public static final int DURATION_WINDOW_ANIMATION = 200;
    public static final int DURATION_CAMERA_ENSURE_VISIBLE_ANIMATION = 500;

    private GoogleMap googleMap;

    private FragmentManager fragmentManager;

    private InfoWindow currentWindow;
    private ViewGroup parent;
    private View currentContainer;

    private ContainerSpecification containerSpec;

    private FragmentContainerIdProvider idProvider;

    private GoogleMap.OnMapClickListener onMapClickListener;

    private GoogleMap.OnCameraIdleListener onCameraIdleListener;
    private GoogleMap.OnCameraMoveStartedListener onCameraMoveStartedListener;
    private GoogleMap.OnCameraMoveListener onCameraMoveListener;
    private GoogleMap.OnCameraMoveCanceledListener onCameraMoveCanceledListener;

    private Animation showAnimation;
    private Animation hideAnimation;

    private WindowShowListener windowShowListener;

    private boolean hideOnFling = false;

    public InfoWindowManager(@NonNull final FragmentManager fragmentManager) {

        this.fragmentManager = fragmentManager;
    }


    public void onParentViewCreated(
            @NonNull final TouchInterceptFrameLayout parent,
            @Nullable final Bundle savedInstanceState) {

        this.parent = parent;
        this.idProvider = new FragmentContainerIdProvider(savedInstanceState);
        this.containerSpec = generateDefaultContainerSpecs(parent.getContext());

        parent.setDetector(
                new GestureDetector(
                        parent.getContext(),
                        new GestureDetector.SimpleOnGestureListener() {

                            @Override
                            public boolean onScroll(
                                    MotionEvent e1, MotionEvent e2,
                                    float distanceX, float distanceY) {

                                if (isOpen()) {
                                    centerInfoWindow(currentWindow, currentContainer);
                                }

                                return true;
                            }

                            @Override
                            public boolean onFling(
                                    MotionEvent e1, MotionEvent e2,
                                    float velocityX, float velocityY) {

                                if (isOpen()) {
                                    if (hideOnFling) {
                                        hide(currentWindow);
                                    } else {
                                        centerInfoWindow(currentWindow, currentContainer);
                                    }
                                }

                                return true;
                            }

                            @Override
                            public boolean onDoubleTap(MotionEvent e) {

                                if (isOpen()) {
                                    centerInfoWindow(currentWindow, currentContainer);
                                }

                                return true;
                            }
                        }));

        currentContainer = parent.findViewById(idProvider.currentId);

        if (currentContainer == null) {
            currentContainer = createContainer(parent);

            parent.addView(currentContainer);
        }

        final Fragment oldFragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG_INFO);
        if (oldFragment != null) {
            fragmentManager.beginTransaction()
                    .remove(oldFragment)
                    .commitNow();
        }

    }

    private View createContainer(@NonNull final ViewGroup parent) {
        final LinearLayout container = new LinearLayout(parent.getContext());

        container.setLayoutParams(generateDefaultLayoutParams());
        container.setId(idProvider.getNewId());
        container.setVisibility(View.INVISIBLE);

        return container;
    }

    private FrameLayout.LayoutParams generateDefaultLayoutParams() {

        return generateLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private FrameLayout.LayoutParams generateLayoutParams(
            final int infoWindowWidth, final int infoWindowHeight) {

        return new FrameLayout.LayoutParams(infoWindowWidth, infoWindowHeight);
    }

    public void toggle(@NonNull final InfoWindow infoWindow) {
        toggle(infoWindow, true);
    }

    public void toggle(@NonNull final InfoWindow infoWindow, final boolean animated) {

        if (isOpen()) {
            // If the toggled window is tha same as the already opened one, close it.
            // Otherwise close the currently opened window and open the new one.
            if (infoWindow.equals(currentWindow)) {
                hide(infoWindow, animated);
            } else {
                show(infoWindow, animated);
            }

        } else {
            show(infoWindow, animated);
        }

    }

    public void show(@NonNull final InfoWindow infoWindow) {
        show(infoWindow, true);
    }

    public void show(@NonNull final InfoWindow window, final boolean animated) {
        // Check if already opened
        if (isOpen()) {

            internalHide(currentContainer, currentWindow);

            currentContainer = createContainer(parent);
            parent.addView(currentContainer);
        }

        setCurrentWindow(window);

        internalShow(window, currentContainer, animated);
    }

    private void internalShow(@NonNull final InfoWindow infoWindow,
                              @NonNull final View container,
                              final boolean animated) {

        addFragment(infoWindow.getWindowFragment(), container);
        prepareView(container, infoWindow);

        if (animated) {

            animateWindowOpen(infoWindow, container);

        } else {

            container.setVisibility(View.VISIBLE);

        }
    }

    private void prepareView(final View view, final InfoWindow infoWindow) {

        updateWithContainerSpec(view);

        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {

                centerInfoWindow(infoWindow, view);
                ensureVisible(view);

                view.getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
            }
        });
    }

    private void updateWithContainerSpec(final View view) {
        ViewCompat.setBackground(view, containerSpec.background);
    }

    private void animateWindowOpen(@NonNull final InfoWindow infoWindow,
                                   @NonNull final View container) {

        final SimpleAnimationListener animationListener = new SimpleAnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

                container.setVisibility(View.VISIBLE);
                propagateShowEvent(infoWindow, InfoWindow.State.SHOWING);

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                propagateShowEvent(infoWindow, InfoWindow.State.SHOWN);
                setCurrentWindow(infoWindow);

            }
        };

        if (showAnimation == null) {

            container.getViewTreeObserver().addOnPreDrawListener(
                    new ViewTreeObserver.OnPreDrawListener() {

                        @Override
                        public boolean onPreDraw() {
                            final int containerWidth = container.getWidth();
                            final int containerHeight = container.getHeight();

                            final float pivotX = container.getX() + containerWidth / 2;
                            final float pivotY = container.getY() + containerHeight;

                            final ScaleAnimation scaleAnimation = new ScaleAnimation(
                                    0f, 1f,
                                    0f, 1f,
                                    pivotX, pivotY);

                            scaleAnimation.setDuration(DURATION_WINDOW_ANIMATION);
                            scaleAnimation.setInterpolator(new DecelerateInterpolator());
                            scaleAnimation.setAnimationListener(animationListener);

                            container.startAnimation(scaleAnimation);

                            container.getViewTreeObserver().removeOnPreDrawListener(this);
                            return true;
                        }
                    });
        } else {
            showAnimation.setAnimationListener(animationListener);
            container.startAnimation(showAnimation);
        }
    }

    public void hide(@NonNull final InfoWindow infoWindow) {
        hide(infoWindow, true);
    }

    public void hide(@NonNull final InfoWindow infoWindow, final boolean animated) {
        internalHide(currentContainer, infoWindow, animated);
    }

    private void internalHide(@NonNull final View container, @NonNull final InfoWindow infoWindow) {
        internalHide(container, infoWindow, true);
    }

    private void internalHide(
            @NonNull final View container,
            @NonNull final InfoWindow toHideWindow,
            final boolean animated) {

        if (animated) {

            final Animation animation;

            if (hideAnimation == null) {

                final int containerWidth = container.getWidth();
                final int containerHeight = container.getHeight();

                final float pivotX = container.getX() + containerWidth / 2;
                final float pivotY = container.getY() + containerHeight;

                animation = new ScaleAnimation(
                        1f, 0f,
                        1f, 0f,
                        pivotX, pivotY);

                animation.setDuration(DURATION_WINDOW_ANIMATION);
                animation.setInterpolator(new DecelerateInterpolator());


            } else {
                animation = hideAnimation;
            }

            animation.setAnimationListener(new SimpleAnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {
                    toHideWindow.setWindowState(InfoWindow.State.HIDING);
                    propagateShowEvent(toHideWindow, InfoWindow.State.HIDING);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    removeWindow(toHideWindow, container);

                    if (container.getId() != InfoWindowManager.this.currentContainer.getId()) {
                        parent.removeView(container);
                    }

                    toHideWindow.setWindowState(InfoWindow.State.HIDDEN);
                    propagateShowEvent(toHideWindow, InfoWindow.State.HIDDEN);
                }
            });

            this.currentContainer.startAnimation(animation);

        } else {
            removeWindow(toHideWindow, container);
            propagateShowEvent(toHideWindow, InfoWindow.State.HIDDEN);

        }
    }

    private void propagateShowEvent(
            @NonNull final InfoWindow infoWindow,
            @NonNull final InfoWindow.State state) {

        if (windowShowListener != null) {
            switch (state) {
                case SHOWING:
                    windowShowListener.onWindowShowStarted(infoWindow);
                    break;
                case SHOWN:
                    windowShowListener.onWindowShown(infoWindow);
                    break;
                case HIDING:
                    windowShowListener.onWindowHideStarted(infoWindow);
                    break;
                case HIDDEN:
                    windowShowListener.onWindowHidden(infoWindow);
                    break;
            }
        }
    }

    private void centerInfoWindow(@NonNull final InfoWindow infoWindow,
                                  @NonNull final View container) {
        final InfoWindow.MarkerSpecification markerSpec = infoWindow.getMarkerSpec();
        final Projection projection = googleMap.getProjection();

        final Point windowScreenLocation = projection.toScreenLocation(infoWindow.getPosition());

        final int containerWidth = container.getWidth();
        final int containerHeight = container.getHeight();

        final int x;
        if (markerSpec.centerByX()) {
            x = windowScreenLocation.x - containerWidth / 2;
        } else {
            x = windowScreenLocation.x + markerSpec.getOffsetX();
        }

        final int y;
        if (markerSpec.centerByY()) {
            y = windowScreenLocation.y - containerHeight / 2;
        } else {
            y = windowScreenLocation.y - containerHeight - markerSpec.getOffsetY();
        }

        final int pivotX = containerWidth / 2;
        final int pivotY = containerHeight;

        container.setPivotX(pivotX);
        container.setPivotY(pivotY);

        container.setX(x);
        container.setY(y);
    }

    private boolean ensureVisible(@NonNull final View infoWindowContainer) {

        final int[] infoWindowLocation = new int[2];
        infoWindowContainer.getLocationOnScreen(infoWindowLocation);

        final boolean visible = true;
        final Rect infoWindowRect = new Rect();
        infoWindowContainer.getHitRect(infoWindowRect);

        final int[] parentPosition = new int[2];
        parent.getLocationOnScreen(parentPosition);

        final Rect parentRect = new Rect();
        parent.getGlobalVisibleRect(parentRect);

        infoWindowContainer.getGlobalVisibleRect(infoWindowRect);

        final int visibleWidth = infoWindowRect.width();
        final int actualWidth = infoWindowContainer.getWidth();

        final int visibleHeight = infoWindowRect.height();
        final int actualHeight = infoWindowContainer.getHeight();

        int scrollX = (visibleWidth - actualWidth);
        int scrollY = (visibleHeight - actualHeight);

        if (scrollX != 0) {
            if (infoWindowRect.left == parentRect.left) {
                scrollX = -Math.abs(scrollX);
            } else {
                scrollX = Math.abs(scrollX);
            }
        }

        if (scrollY != 0) {
            if (infoWindowRect.top < parentRect.top) {
                scrollY = Math.abs(scrollY);
            } else {
                scrollY = -Math.abs(scrollY);
            }
        }

        final CameraUpdate cameraUpdate = CameraUpdateFactory.scrollBy(scrollX, scrollY);
        googleMap.animateCamera(cameraUpdate, DURATION_CAMERA_ENSURE_VISIBLE_ANIMATION, null);

        return visible;
    }

    private void removeWindow(@NonNull final InfoWindow window, @NonNull final View container) {

        container.setVisibility(View.INVISIBLE);
        container.setScaleY(1f);
        container.setScaleX(1f);
        container.clearAnimation();

        removeWindowFragment(window.getWindowFragment());
    }

    private void addFragment(@NonNull final Fragment fragment, @NonNull final View container) {
        fragmentManager.beginTransaction()
                .replace(container.getId(), fragment, FRAGMENT_TAG_INFO)
                .commitNow();
    }

    private void removeWindowFragment(final Fragment windowFragment) {
        fragmentManager.beginTransaction()
                .remove(windowFragment)
                .commitNow();
    }

    public ContainerSpecification generateDefaultContainerSpecs(Context context) {
        final Drawable drawable =
                ContextCompat.getDrawable(context, R.drawable.infowindow_background);

        return new ContainerSpecification(drawable);
    }

    private boolean isOpen() {
        return currentContainer != null && currentContainer.getVisibility() == View.VISIBLE;
    }

    public void setWindowShowListener(WindowShowListener windowShowListener) {
        this.windowShowListener = windowShowListener;
    }

    private void setCurrentWindow(InfoWindow currentWindow) {
        this.currentWindow = currentWindow;
    }

    public void setContainerSpec(ContainerSpecification containerSpec) {
        this.containerSpec = containerSpec;
    }


    public ContainerSpecification getContainerSpec() {
        return containerSpec;
    }

    private class FragmentContainerIdProvider {
        private final static String BUNDLE_KEY_ID = "BundleKeyFragmentContainerIdProvider";
        private int currentId;

        public FragmentContainerIdProvider(@Nullable final Bundle savedInstanceState) {
            if (savedInstanceState != null) {
                currentId = savedInstanceState.getInt(BUNDLE_KEY_ID, R.id.infoWindowContainer1);
            } else {
                currentId = R.id.infoWindowContainer1;
            }
        }

        public int getCurrentId() {
            return currentId;
        }

        public int getNewId() {
            if (currentId == R.id.infoWindowContainer1) {
                currentId = R.id.infoWindowContainer2;
            } else {
                currentId = R.id.infoWindowContainer1;
            }

            return currentId;
        }

        public void onSaveInstanceState(@NonNull final Bundle outState) {
            outState.putInt(BUNDLE_KEY_ID, currentId);
        }
    }


    public void onSaveInstanceState(@NonNull final Bundle outState) {
        idProvider.onSaveInstanceState(outState);
    }

    public void onDestroy() {

        currentContainer = null;
        parent = null;

    }

    public void onMapReady(@NonNull final GoogleMap googleMap) {
        this.googleMap = googleMap;

        googleMap.setOnMapClickListener(this);

        googleMap.setOnCameraIdleListener(this);
        googleMap.setOnCameraMoveStartedListener(this);
        googleMap.setOnCameraMoveListener(this);
        googleMap.setOnCameraMoveCanceledListener(this);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        if (onMapClickListener != null) {
            onMapClickListener.onMapClick(latLng);
        }

        // CLICK HIDE POPUP INFO
        if (isOpen()) {
            //internalHide(currentContainer, currentWindow);
        }

    }

    @Override
    public void onCameraIdle() {
        if (onCameraIdleListener != null) {
            onCameraIdleListener.onCameraIdle();
        }
    }

    @Override
    public void onCameraMoveStarted(int i) {
        if (onCameraMoveStartedListener != null) {
            onCameraMoveStartedListener.onCameraMoveStarted(i);
        }
    }

    @Override
    public void onCameraMove() {
        if (onCameraMoveListener != null) {
            onCameraMoveListener.onCameraMove();
        }

        if (isOpen()) {
            centerInfoWindow(currentWindow, currentContainer);
        }
    }

    @Override
    public void onCameraMoveCanceled() {
        if (onCameraMoveCanceledListener != null) {
            onCameraMoveCanceledListener.onCameraMoveCanceled();
        }
    }

    public void setOnMapClickListener(GoogleMap.OnMapClickListener onMapClickListener) {

        this.onMapClickListener = onMapClickListener;
    }

    public void setOnCameraIdleListener(GoogleMap.OnCameraIdleListener onCameraIdleListener) {

        this.onCameraIdleListener = onCameraIdleListener;
    }

    public void setOnCameraMoveStartedListener(
            final GoogleMap.OnCameraMoveStartedListener onCameraMoveStartedListener) {

        this.onCameraMoveStartedListener = onCameraMoveStartedListener;
    }

    public void setOnCameraMoveListener(final GoogleMap.OnCameraMoveListener onCameraMoveListener) {

        this.onCameraMoveListener = onCameraMoveListener;
    }

    public void setOnCameraMoveCanceledListener(
            final GoogleMap.OnCameraMoveCanceledListener onCameraMoveCanceledListener) {

        this.onCameraMoveCanceledListener = onCameraMoveCanceledListener;
    }

    public void setShowAnimation(Animation showAnimation) {
        this.showAnimation = showAnimation;
    }

    public void setHideAnimation(Animation hideAnimation) {
        this.hideAnimation = hideAnimation;
    }

    public void setHideOnFling(final boolean hideOnFling) {
        this.hideOnFling = hideOnFling;
    }

    public interface WindowShowListener {
        void onWindowShowStarted(@NonNull final InfoWindow infoWindow);

        void onWindowShown(@NonNull final InfoWindow infoWindow);

        void onWindowHideStarted(@NonNull final InfoWindow infoWindow);

        void onWindowHidden(@NonNull final InfoWindow infoWindow);
    }

    public static class ContainerSpecification {
        private Drawable background;

        public ContainerSpecification(Drawable background) {
            this.background = background;
        }

        public Drawable getBackground() {
            return background;
        }

        public void setBackground(Drawable background) {
            this.background = background;
        }
    }
}
