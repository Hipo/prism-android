package com.hipo.android.prism;

import android.support.annotation.StringDef;
import android.text.TextUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Prism {

    @StringDef({
            CMD.FIT,
            CMD.CROP,
            CMD.RESIZE
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface CMD {
        String FIT = "resize_then_fit";
        String CROP = "resize_then_crop";
        String RESIZE = "resize";
    }

    @StringDef({
            OUT.PNG,
            OUT.JPG
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface OUT {
        String PNG = "png";
        String JPG = "jpg";
    }

    private static final String QUESTION_MARK = "?";
    private static final Integer QUALITY_DEFAULT = 65;
    private static final Integer ZERO = 0;
    private static final Integer ONE = 1;

    private static final String WIDTH_QUERY = "&width=";
    private static final String HEIGHT_QUERY = "&height=";
    private static final String CROP_X = "&crop_x=";
    private static final String CROP_Y = "&crop_y=";
    private static final String CROP_WIDTH = "&crop_width=";
    private static final String CROP_HEIGHT = "&crop_height=";
    private static final String QUALITY = "&quality=";
    private static final String CMD_KEY = "&CMD=";
    private static final String OUT_KEY = "&OUT=";
    private static final String FRAME_BG_COLOR = "&frame_bg_color=";
    private static final String NO_REDIRECT = "&no_redirect=";
    private static final String PREMULTIPLIED = "&premultiplied="; // about tranparency
    private static final String PRESERVE_RATIO = "&preserve_ratio=";

    private String url;
    private Integer width;
    private Integer height;
    private Integer cropx;
    private Integer cropy;
    private Integer cropWidth;
    private Integer cropHeight;
    private Integer quality;
    private String backgroundColor;
    private Boolean noRedirect;
    private Boolean preMultiplied;
    private Boolean preserveRatio;

    @CMD
    private String cmdValue;

    @OUT
    private String outValue;


    private static volatile Prism singleton = null;

    public static Prism withUrl(String url) {
        if (singleton == null) {
            synchronized (Prism.class) {
                if (singleton == null) {
                    singleton = new Builder(url).build();
                    singleton.url += QUESTION_MARK;
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

    public Prism cmd(@CMD String cmdValue){
        singleton.cmdValue = cmdValue;
        return singleton;
    }

    public Prism out(@OUT String outValue){
        singleton.outValue = outValue;
        return singleton;
    }

    public Prism backgroundColor(String backgroundColor){
        singleton.backgroundColor = backgroundColor;
        return singleton;
    }

    public Prism noRedirect(Boolean noRedirect){
        singleton.noRedirect = noRedirect;
        return singleton;
    }

    public Prism preMultiplied(Boolean preMultiplied){
        singleton.preMultiplied = preMultiplied;
        return singleton;
    }

    public Prism preserveRatio(Boolean preserveRatio){
        singleton.preserveRatio = preserveRatio;
        return singleton;
    }

    Prism(String url) {
        this.url = url;
    }

    public static class Builder {
        private final String url;

        /** Start building a new {@link Prism} instance. */
        public Builder(String url) {
            if (TextUtils.isEmpty(url)) {
                throw new IllegalArgumentException("Url must not be null or empty.");
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

        if (width == null && height == null){
            throw new IllegalArgumentException("Width or height is required.");
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

        if (cmdValue != null) {
            if (cmdValue.equals(CMD.CROP)) {
                url = url + CMD_KEY + CMD.CROP;
            } else if (cmdValue.equals(CMD.FIT)) {
                url = url + CMD_KEY + CMD.FIT;
            } else if (cmdValue.equals(CMD.RESIZE)) {
                url = url + CMD_KEY + CMD.RESIZE;
            }
        }

        if (outValue != null) {
            if (outValue.equals(OUT.JPG)) {
                url = url + OUT_KEY + OUT.JPG;
            } else if (outValue.equals(OUT.PNG)) {
                url = url + OUT_KEY + OUT.PNG;
            }
        }

        if (backgroundColor != null){
            url = url + FRAME_BG_COLOR + backgroundColor;
        }

        if (noRedirect != null && noRedirect) {
            url = url + NO_REDIRECT + ONE;
        } else {
            url = url + NO_REDIRECT + ZERO;
        }

        if (preMultiplied != null && preMultiplied){
            url = url + PREMULTIPLIED + ONE;
        } else {
            url = url + PREMULTIPLIED + ZERO;
        }

        if (preserveRatio != null && !preserveRatio){
            url = url + PRESERVE_RATIO + ZERO;
        } else {
            url = url + PRESERVE_RATIO + ONE;
        }

        return url;
    }


}
