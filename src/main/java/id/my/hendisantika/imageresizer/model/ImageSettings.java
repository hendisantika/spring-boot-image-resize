package id.my.hendisantika.imageresizer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-image-resize
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/11/24
 * Time: 06.08
 * To change this template use File | Settings | File Templates.
 */
@Data
@AllArgsConstructor
public class ImageSettings {
    private int width;
    private int height;
    private int quality;
    private String fillColor;
    private String sourceName;

    public ImageSettings(int width, int height, int quality) {
        this.width = width;
        this.height = height;
        this.quality = quality;
    }

    public ImageSettings() {
        this.width = 250;
        this.height = 250;
        this.quality = 90;
        this.fillColor = "#ffffff";
    }

    protected enum ScaleType {CROP, FILL, SKEW}

    protected enum ImageType {JPG, PNG}
}
