import org.datavec.api.util.ClassPathResource;
import org.deeplearning4j.models.paragraphvectors.ParagraphVectors;
import org.deeplearning4j.models.word2vec.VocabWord;
import org.deeplearning4j.models.word2vec.wordstore.inmemory.AbstractCache;
import org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.exception.ND4JIllegalStateException;
import org.nd4j.linalg.ops.transforms.Transforms;
import java.io.File;
import java.io.IOException;


public class doc2vec {

    ParagraphVectors vec;
    ParagraphVectors pv;

    public doc2vec(String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource(fileName);
        File file = resource.getFile();
        SentenceIterator iter = new BasicLineIterator(file);
        AbstractCache<VocabWord> cache = new AbstractCache<>();
        TokenizerFactory t = new DefaultTokenizerFactory();
        t.setTokenPreProcessor(new CommonPreprocessor());

        pv = new ParagraphVectors.Builder()
                .minWordFrequency(1)
                .iterations(5)
                .epochs(1)
                .layerSize(100)
                .learningRate(0.025)
                .windowSize(5)
                .iterate(iter)
                .trainWordVectors(false)
                .vocabCache(cache)
                .tokenizerFactory(t)
                .sampling(0)
                .build();
        vec = pv;
        vec.fit();
    }

    public boolean similar(String s1, String s2) {

try {


        INDArray inferredVectorA = vec.inferVector(s1);
        INDArray inferredVectorA2 = vec.inferVector(s2);
    double sim = Transforms.cosineSim(inferredVectorA, inferredVectorA2);
    System.out.println("doc2vec : " + s1 + " ; " + s2 + " : " + sim);
    if (sim > 0.7) {
        return true;
    } else {
        return false;
    }
}

catch ( ND4JIllegalStateException e) {
    return false ;

        }

        }
    }

   /* public static void main(String[] args) throws Exception {

    }*/
