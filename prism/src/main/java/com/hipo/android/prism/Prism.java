package com.hipo.android.prism;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Prism {

    private static final String QUESTION_MARK = "?";
    private static final Integer QUALITY_DEFAULT = 65;

    private static final String WIDTH_QUERY = "&width=";
    private static final String HEIGHT_QUERY = "&height=";
    private static final String CROP_X = "&crop_x=";
    private static final String CROP_Y = "&crop_y=";
    private static final String CROP_WIDTH = "&crop_width=";
    private static final String CROP_HEIGHT = "&crop_height=";
    private static final String QUALITY = "&quality=";
    private static final String CMD_KEY = "&cmd=";

    private static volatile Prism singleton = null;

    private String url;
    private Integer width;
    private Integer height;
    private Integer cropx;
    private Integer cropy;
    private Integer cropWidth;
    private Integer cropHeight;
    private Integer quality;

    @cmd
    private String cmdValue;

    @StringDef({
            cmd.FIT,
            cmd.CROP,
            cmd.RESIZE
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface cmd {
        String FIT = "resize_then_fit";
        String CROP = "resize_then_crop";
        String RESIZE = "resize";
    }

    public static Prism withUrl(String url) {
        if (singleton == null) {
            synchronized (Prism.class) {
                if (singleton == null) {
                    singleton = new Builder(url + QUESTION_MARK).build();
                }
            }
        }
        return singleton;
    }

    public Prism width(Integer width) {
        singleton.width = width;
        return singleton;
    }

    public Prism height(Integer height) {
        singleton.height = height;
        return singleton;
    }

    public Prism cropX(Integer cropx) {
        singleton.cropx = cropx;
        return singleton;
    }

    public Prism cropY(Integer cropy) {
        singleton.cropy = cropy;
        return singleton;
    }

    public Prism cropWidth(Integer cropWidth) {
        singleton.cropWidth = cropWidth;
        return singleton;
    }

    public Prism cropHeight(Integer cropHeight) {
        singleton.cropHeight = cropHeight;
        return singleton;
    }

    public Prism quality(Integer quality){
        singleton.quality = quality;
        return singleton;
    }

    public Prism cmd(@cmd String cmdValue){
        singleton.cmdValue = cmdValue;
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

        if (cropy != null){
            url = url + CROP_Y + cropy;
        }

        if (cropWidth != null){
            url = url + CROP_WIDTH + cropWidth;
        }

        if (cropHeight != null){
            url = url + CROP_HEIGHT + cropHeight;
        }

        if (quality != null){
            url = url + QUALITY + quality;
        } else {
            url = url + QUALITY + QUALITY_DEFAULT;
        }

        if (cmdValue.equals(cmd.CROP)){
            url = url + CMD_KEY + cmd.CROP;
        } else if (cmdValue.equals(cmd.FIT)){
            url = url + CMD_KEY + cmd.FIT;
        } else if (cmdValue.equals(cmd.RESIZE)){
            url = url + CMD_KEY + cmd.RESIZE;
        }

        return url;
    }


}
