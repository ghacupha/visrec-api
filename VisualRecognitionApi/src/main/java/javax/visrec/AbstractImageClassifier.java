package javax.visrec;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import javax.visrec.ml.classification.Classifier;
import javax.visrec.util.Builder;

/**
 * Skeleton abstract class to make it easier to implement image classifier.
 * It provides implementation of Classifier interface for images, along with
 * image factory for specific type of images.
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 * @param <IMAGE_CLASS>
 * @param <MODEL_CLASS>
 *
 * @deprecated Kevin: We should rethink the necessary use of this class in the API. I think we should move this one to the RI.
 */
@Deprecated(forRemoval = true)
public abstract class AbstractImageClassifier<IMAGE_CLASS, MODEL_CLASS> implements Classifier<IMAGE_CLASS>, Builder<Classifier> { // could also implement binary classifier

    private ImageFactory<IMAGE_CLASS> imageFactory; // image factory impl for the specified image class
    private MODEL_CLASS model; // the model could be injected from machine learning container?

    private float threshold; // this should ba a part of every classifier

    protected AbstractImageClassifier(final Class<IMAGE_CLASS> cls) {
        // FIXME zoran: We should avoid using signleton here
        // FIXME Kevin: We'll fix this with the new builder approach.
//        final Optional<ImageFactory<IMAGE_CLASS>> optionalImageFactory = ImageFactoryProvider.getInstance().findImageFactory(cls);
//        if (!optionalImageFactory.isPresent()) {
//            throw new IllegalArgumentException(String.format("Could not find ImageFactory by '%s'", cls.getName()));
//        }
//        imageFactory = optionalImageFactory.get();
    }

    public ImageFactory<IMAGE_CLASS> getImageFactory() {
        return imageFactory;
    }

    public Map<String, Float> classify(File file) throws IOException {
        IMAGE_CLASS image = imageFactory.getImage(file);
        return classify(image);
    }

    public Map<String, Float> classify(InputStream inStream) throws IOException {
        IMAGE_CLASS image = imageFactory.getImage(inStream);
        return classify(image);
    }

    public MODEL_CLASS getModel() {
        return model;
    }

    protected void setModel(MODEL_CLASS model) {
        this.model = model;
    }

    public float getThreshold() {
        return threshold;
    }

    public void setThreshold(float threshold) {
        this.threshold = threshold;
    }
}
