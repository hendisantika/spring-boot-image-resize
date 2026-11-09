package id.my.hendisantika.imageresizer;

import id.my.hendisantika.imageresizer.service.ImageResizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidParameterException;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-image-resize
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/11/24
 * Time: 06.42
 * To change this template use File | Settings | File Templates.
 */
class ImageResizerTest {

    private static BufferedImage sampleImage(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.ORANGE);
        g.fillRect(0, 0, width, height);
        g.dispose();
        return image;
    }

    @Test
    void resizeImageToThumbnail() throws IOException {
        byte[] bytes = new ImageResizer().resizeImage(sampleImage(800, 600), "thumbnail");

        Assertions.assertNotNull(bytes);
        Assertions.assertTrue(bytes.length > 0);
        BufferedImage result = ImageIO.read(new ByteArrayInputStream(bytes));
        Assertions.assertEquals(200, result.getWidth());
        Assertions.assertEquals(200, result.getHeight());
    }

    @Test
    void resizeImageToDetail() throws IOException {
        byte[] bytes = new ImageResizer().resizeImage(sampleImage(1024, 768), "detail");

        BufferedImage result = ImageIO.read(new ByteArrayInputStream(bytes));
        Assertions.assertEquals(400, result.getWidth());
        Assertions.assertEquals(400, result.getHeight());
    }

    @Test
    void resizeImageWithUnknownTypeThrows() {
        Assertions.assertThrows(InvalidParameterException.class,
                () -> new ImageResizer().resizeImage(sampleImage(100, 100), "unknown"));
    }
}
