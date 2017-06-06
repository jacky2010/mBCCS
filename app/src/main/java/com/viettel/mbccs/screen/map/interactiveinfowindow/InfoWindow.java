package com.viettel.mbccs.screen.map.interactiveinfowindow;

import android.support.v4.app.Fragment;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.io.Serializable;

public class InfoWindow {
    private LatLng position;
    private MarkerSpecification markerSpec;

    private Fragment windowFragment;

    private State windowState = State.HIDDEN;

    public InfoWindow(
            Marker marker,
            MarkerSpecification markerSpec,
            Fragment fragment) {

        this(marker.getPosition(), markerSpec, fragment);
    }

    public InfoWindow(
            LatLng position,
            MarkerSpecification markerSpec,
            Fragment fragment) {

        this.position = position;
        this.markerSpec = markerSpec;
        this.windowFragment = fragment;
    }

    public LatLng getPosition() { return position; }

    public void setPosition(LatLng position) {
        this.position = position;
    }

    public MarkerSpecification getMarkerSpec() {
        return markerSpec;
    }

    public void setMarkerSpec(MarkerSpecification markerSpec) {
        this.markerSpec = markerSpec;
    }

    public Fragment getWindowFragment() {
        return windowFragment;
    }

    public State getWindowState() {
        return windowState;
    }

    public void setWindowState(State windowState) {
        this.windowState = windowState;
    }

    public enum State {
        SHOWING, SHOWN, HIDING, HIDDEN
    }

    public static class MarkerSpecification implements Serializable {
        private int offsetX;
        private int offsetY;

        private boolean centerByX = true;
        private boolean centerByY = false;

        public MarkerSpecification(int offsetX, int offsetY) {
            this.offsetX = offsetX;
            this.offsetY = offsetY;
        }

        public int getOffsetX() {
            return offsetX;
        }

        public void setOffsetX(int offsetX) {
            this.offsetX = offsetX;
        }

        public int getOffsetY() {
            return offsetY;
        }

        public void setOffsetY(int offsetY) {
            this.offsetY = offsetY;
        }

        public boolean centerByX() {
            return centerByX;
        }


        public void setCenterByX(boolean centerByX) {
            this.centerByX = centerByX;
        }

        public boolean centerByY() {
            return centerByY;
        }

        public void setCenterByY(boolean centerByY) {
            this.centerByY = centerByY;
        }

        @Override
        public boolean equals(Object o) {

            if (o instanceof MarkerSpecification) {
                final MarkerSpecification markerSpecification = (MarkerSpecification) o;

                final boolean offsetCheck = markerSpecification.getOffsetY() == offsetY;

                return offsetCheck;
            }

            return super.equals(o);
        }
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof InfoWindow) {
            final InfoWindow queryWindow = (InfoWindow) o;

            final boolean markerCheck = queryWindow.getPosition().equals(position);
            final boolean specCheck = queryWindow.getMarkerSpec().equals(markerSpec);
            final boolean fragmentCheck = queryWindow.getWindowFragment() == windowFragment;

            return markerCheck && specCheck && fragmentCheck;
        }

        return super.equals(o);
    }

}
