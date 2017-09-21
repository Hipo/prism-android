package com.hipo.android.prism;

/**
 * Created by cagla on 21/09/2017.
 */

public class Prism {

    private static final String WIDTH_QUERY = "&width=";
    private static final String HEIGHT_QUERY = "&height=";
    private static final String CROP_X = "&crop_x=";
    private static final String CROP_Y = "&crop_y=";
    private static final String CROP_WIDTH = "&crop_width=";
    private static final String CROP_HEIGHT = "&crop_height=";

    private static volatile Prism singleton = null;

    private String url;
    private Double width;
    private Double height;
    private Double cropx;
    private Double cropy;
    private Double cropWidth;
    private Double cropHeight;

    public static Prism withUrl(String url) {
        if (singleton == null) {
            synchronized (Prism.class) {
                if (singleton == null) {
                    singleton = new Builder(url).build();
                }
            }
        }
        return singleton;
    }

    public Prism width(double width) {
        singleton.width = width;
        return singleton;
    }

    public Prism height(double height) {
        singleton.height = height;
        return singleton;
    }

    public Prism cropX(double cropx) {
        singleton.cropx = cropx;
        return singleton;
    }

    public Prism cropY(double cropy) {
        singleton.cropy = cropy;
        return singleton;
    }

    public Prism cropWidth(double cropWidth) {
        singleton.cropWidth = cropWidth;
        return singleton;
    }

    public Prism cropHeight(double cropHeight) {
        singleton.cropHeight = cropHeight;
        return singleton;
    }

    Prism(String url) {
        this.url = url;
    }

    public static class Builder {
        private final String url;

        /** Start building a new {@link Prism} instance. */
        public Builder(String url) {
            if (url == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            this.url = url;
        }

        /** Create the {@link Prism} instance. */
        public Prism build() {
            String url = this.url;
            return new Prism(url);
        }
    }

    public String getUrl(){

        if (width != null){
            url = url + WIDTH_QUERY + width;
        }

        if (height != null){
            url = url + HEIGHT_QUERY + height;
        }

        if (cropx != null){
            url = url + CROP_X + cropx;
        }

        if (width != null){
            url = url + CROP_Y + cropy;
        }

        if (width != null){
            url = url + CROP_WIDTH + cropWidth;
        }

        if (width != null){
            url = url + CROP_HEIGHT + cropHeight;
        }

        return url;
    }


}
