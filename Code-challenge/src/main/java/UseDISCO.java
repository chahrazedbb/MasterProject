

import de.linguatools.disco.CorruptConfigFileException;
import de.linguatools.disco.DISCO;
import de.linguatools.disco.TextSimilarity;
import java.io.IOException;

public class UseDISCO {


    public static float similarity(String S1, String S2)
            throws IOException, CorruptConfigFileException{
        DISCO disco  = DISCO.load("C:\\Users\\PC\\IdeaProjects\\" +
                "challenge\\src\\main\\resources\\" +
                "enwiki-20130403-word2vec-lm-mwl-lc-sim.denseMatrix") ;
        TextSimilarity   disco1 = new TextSimilarity();
        float s1 = disco1.textSimilarity(S1, S2, disco, DISCO.SimilarityMeasure.COSINE);
        System.out.println("disco = "+s1);
        return s1;
    }

    }
