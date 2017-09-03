package ferjogames.coralsystemdeck.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Fer on 27/08/2017.
 */

public class DensityFileResolver {
    public static boolean HIGHER_DENSITIES_FIRST = false;

    private static final List<Density> DENSITIES = Arrays.asList(
            new Density("ldpi", 0.75f),
            new Density("mdpi", 1),
            new Density("hdpi", 1.5f),
            new Density("xhdpi", 2),
            new Density("xxhdpi", 3),
            new Density("xxxhdpi", 4)
    );

    public static String resolve(String file) {
        float densityValue = Gdx.graphics.getDensity();
        Density lowerDensity = null;
        ListIterator<Density> densityIterator = iterator(DENSITIES);
        while (hasMoreElements(densityIterator)) {
            Density density = nextElement(densityIterator);
            if (Gdx.files.internal(buildAssetPath(density, file)).exists()) {
                if (density.bucketValue <= densityValue) {
                    lowerDensity = density; // Greater of the minors
                }
                if (density.bucketValue >= densityValue) {
                    System.out.println(density.bucketName);
                    return buildAssetPath(density, file);  // Lowest of the majors
                }
            }
        }

        if (lowerDensity != null) {
            System.out.println(lowerDensity.bucketName);
            return buildAssetPath(lowerDensity, file);
        } else
            throw new GdxRuntimeException("File not found: " + file);
    }

    private static Density nextElement(ListIterator<Density> iterator) {
        if (HIGHER_DENSITIES_FIRST)
            return iterator.previous();
        return iterator.next();
    }

    private static boolean hasMoreElements(ListIterator iterator) {
        if (HIGHER_DENSITIES_FIRST)
            return iterator.hasPrevious();
        return iterator.hasNext();
    }

    private static ListIterator<Density> iterator(List<Density> densities) {
        if (HIGHER_DENSITIES_FIRST)
            return densities.listIterator(densities.size());
        return densities.listIterator();
    }

    private static String buildAssetPath(Density density, String filename) {
        return density.bucketName + '/' + filename;
    }

    private static class Density {
        private String bucketName;
        private float bucketValue;

        private Density(String bucketName, float bucketValue) {
            this.bucketName = bucketName;
            this.bucketValue = bucketValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Density density = (Density) o;

            return Float.compare(density.bucketValue, bucketValue) == 0 && bucketName.equals(density.bucketName);
        }

        @Override
        public int hashCode() {
            int result = bucketName.hashCode();
            result = 31 * result + (bucketValue != +0.0f ? Float.floatToIntBits(bucketValue) : 0);
            return result;
        }
    }
}
