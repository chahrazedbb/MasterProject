import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

/**
 * Created by agibsonccc on 10/9/14.
 *
 * Neural net that processes text into wordvectors. See below url for an in-depth explanation.
 * https://deeplearning4j.org/word2vec.html
 */
public class word2vecClass {

    private static Logger log = LoggerFactory.getLogger(word2vecClass.class);
    String filePath ;
    Word2Vec vector ;

    public word2vecClass() throws IOException {
        filePath = new ClassPathResource("raw_sentences.txt").getFile().getAbsolutePath();
        SentenceIterator iter = new BasicLineIterator(filePath);
        TokenizerFactory t ;
        t = new DefaultTokenizerFactory();
        t.setTokenPreProcessor(new CommonPreprocessor());

         vector = new Word2Vec.Builder()
                .minWordFrequency(5)
                .iterations(1)
                .layerSize(100)
                .seed(42)
                .windowSize(5)
                .iterate(iter)
                .tokenizerFactory(t)
                .build();
         vector.fit();


     //   File gModel = new File("C:/Users/PC/Desktop/GoogleNews-vectors-negative300.bin");
  //      filePath = new ClassPathResource("text.txt").getFile().getAbsolutePath();
      //  vector = WordVectorSerializer.loadStaticModel(gModel);
      //  = WordVectorSerializer.loadGoogleModel(gModel,true);
       // vector = WordVectorSerializer.readWord2VecModel(gModel);
      //  filePath = new ClassPathResource("NewsData/RawNewsToGenerateWordVector.txt").getFile().getAbsolutePath();
/*
        SentenceIterator iterator = new BasicLineIterator(filePath);
        TokenizerFactory tokenizerFactory = new DefaultTokenizerFactory();
        tokenizerFactory.setTokenPreProcessor(new CommonPreprocessor());

        vector.setTokenizerFactory(tokenizerFactory);
        vector.setSentenceIterator(iterator);


        log.info("Word2vec uptraining...");

        vector.fit();

      /*  SentenceIterator iter = new BasicLineIterator(filePath);
        TokenizerFactory t ;
        t = new DefaultTokenizerFactory();
        t.setTokenPreProcessor(new CommonPreprocessor());

        vector = new Word2Vec.Builder()
                .minWordFrequency(5)
                .iterations(1)
                .layerSize(100)
                .seed(42)
                .windowSize(5)
                .iterate(iter)
                .tokenizerFactory(t)
                .build();
        vector.fit();

*/


    }

    public  boolean similarity(String s1,String s2){
        boolean sim = false ;
        double cosSim = vector.similarity(s1,s2);

        System.out.println(s1 + " , " + s2 + "  word2vec:  " + cosSim);
        if(cosSim >= 0.7){
            sim = true;
        }
        return sim ;
     }
}
