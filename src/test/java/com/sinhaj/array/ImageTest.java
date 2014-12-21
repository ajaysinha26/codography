package com.sinhaj.array;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: ajaysinha
 * Date: 9/2/13
 * Time: 8:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class ImageTest {

    @Test
    public void testImagesAreEqual() throws Exception {
        char [][]matrix1 = {
                {'A', 'J', 'A', 'Y'},
                {'S', 'I', 'N', 'H'},
                {'I', 'N', 'T', 'E'},
                {'I', 'D', 'E', 'A'}
            };
        Image image1 = new Image(matrix1);
        char [][]matrix2 = {
                {'A', 'J', 'A', 'Y'},
                {'S', 'I', 'N', 'H'},
                {'I', 'N', 'T', 'E'},
                {'I', 'D', 'E', 'A'}
            };
        Image image2 = new Image(matrix2);

        assertEquals(true, image1.equals(image2));
    }

    @Test
    public void testImagesAreNotEqual() throws Exception {
        char [][]matrix1 = {
                {'A', 'J', 'A', 'Y'},
                {'S', 'I', 'N', 'A'},
                {'I', 'N', 'T', 'E'},
                {'I', 'D', 'E', 'A'}
            };
        Image image1 = new Image(matrix1);
        char [][]matrix2 = {
                {'A', 'J', 'A', 'Y'},
                {'S', 'I', 'N', 'H'},
                {'I', 'N', 'T', 'E'},
                {'I', 'D', 'E', 'A'}
            };
        Image image2 = new Image(matrix2);

        assertEquals(false, image1.equals(image2));
    }

    @Test
    public void testImageIsRotated() throws Exception {
        char [][]matrix1 = {
                {'A', 'J', 'A', 'Y'},
                {'S', 'I', 'N', 'H'},
                {'I', 'N', 'T', 'E'},
                {'I', 'D', 'E', 'A'}
            };
        Image image1 = new Image(matrix1);
        image1.rotate();

        char [][]matrix2 = {
                {'I', 'I', 'S', 'A'},
                {'D', 'N', 'I', 'J'},
                {'E', 'T', 'N', 'A'},
                {'A', 'E', 'H', 'Y'}
            };
        Image image2 = new Image(matrix2);

        assertEquals(true, image1.equals(image2));
    }
}
