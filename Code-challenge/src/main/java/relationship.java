import de.linguatools.disco.CorruptConfigFileException;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.*;
import edu.sussex.nlp.jws.JWS;
import uk.ac.shef.wit.simmetrics.similaritymetrics.AbstractStringMetric;
import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class relationship {

    static UseDISCO disco = new UseDISCO();
    public doc2vec doc1 = new doc2vec("sentences.txt");
  //  public doc2vec doc2 = new doc2vec("raw_sentences.txt");

    //********************dandelion****************************

    public dandelion dan = new dandelion();

    public relationship() throws IOException {
    }

    // ****************levenshtein *************************************************************************************

    public   float Levenshtein(String g1,String g2){
        float res = 0;
        Set<AbstractStringMetric> metrics = new HashSet<AbstractStringMetric>();
        metrics.add( new Levenshtein());
        for (AbstractStringMetric metric : metrics) {
            res = metric.getSimilarity(g1, g2);
            System.out.println("Levenshtein = " + res);
        }
        return res;
    }

    //*****************wordnet******************************************************************************************

    private  String wordnetDir ="C:/Program Files (x86)/WordNet";
    private  JWS ws = new JWS(wordnetDir,"2.1");

    public   ArrayList<String> testVerb(String word) {

        IDictionary dict =  ws.getDictionary();
        ArrayList<String> al = new ArrayList<String>();
        //removing duplicates
        IIndexWord idxWord = dict.getIndexWord(word, POS.VERB);
        try {
            int x = idxWord.getTagSenseCount();
            for (int j = 0; j < x; j++) {
                IWordID wordID = idxWord.getWordIDs().get(j);
                IWord word1 = dict.getWord(wordID);
                // Adding Related Words to List of Realted Words
                ISynset synset = word1.getSynset();
                for (IWord w : synset.getWords()) {
                    System.out.println(w.getLemma());
                    al.add(w.getLemma());
                    // output.add(w.getLemma());
                }
            }
            Set<String> hs = new HashSet<>();
            hs.addAll(al);
            al.clear();
            al.addAll(hs);
        } catch (Exception ex) {
            System.out.println("No synonym found!");
        }
        return al ;
    }

    public   ArrayList<String> testNoun(String word) {
        IDictionary dict =  ws.getDictionary();
        ArrayList<String> al = new ArrayList<String>();
        //removing duplicates
        IIndexWord idxWord = dict.getIndexWord(word, POS.NOUN);
        try {
            int x = idxWord.getTagSenseCount();
            for (int j = 0; j < x; j++) {
                IWordID wordID = idxWord.getWordIDs().get(j);
                IWord word1 = dict.getWord(wordID);
                // Adding Related Words to List of Realted Words
                ISynset synset = word1.getSynset();
                for (IWord w : synset.getWords()) {
                    System.out.println(w.getLemma());
                    al.add(w.getLemma());
                     //output.add(w.getLemma());
                }
            }
            Set<String> hs = new HashSet<>();
            hs.addAll(al);
            al.clear();
            al.addAll(hs);
        } catch (Exception ex) {
           System.out.println("No synonym found!");
        }
        return  al ;
    }

    public   boolean IsSynonymNoun(String word1,String word2){
        boolean synonum = false ;

        ArrayList<String> array1 =  testNoun(word1) ;
        System.out.println("----------------------");
        ArrayList<String> array2 =  testNoun(word2) ;

        for (int i = 0 ; i < array1.size() ; i++){
            if (array2.contains(array1.get(i)))
            {
                System.out.println(" noun sysnonym found");
                synonum = true ;
                break;
            }
        }
        System.out.println(synonum);

        return synonum ;
    }

    public   boolean IsSynonymVerb(String word1,String word2){
        boolean synonum = false ;

        ArrayList<String> array1 =  testVerb(word1) ;
        ArrayList<String> array2 =  testVerb(word2) ;

        for (int i = 0 ; i < array1.size() ; i++){
            if (array2.contains(array1.get(i)))
            {
            //    System.out.println(" verb sysnonym found");
                synonum = true ;
                break;
            }
        }

        return synonum ;
    }

    //*****************subclass******************************************************************************************

    public   boolean subclass(String s1,String s2) throws IOException {
      query2 q = new query2() ;
        String entity1= null;
        String entity2= null;
        entity1=q.QueryDBpedia(q.BrowsWord(s1));
        entity2=q.QueryDBpedia(q.BrowsWord(s2));
        boolean b  = q.checkSub(entity1, entity2) ;
        if(b==true){
            System.out.println("subclass");
        }
        return  b;
    }
    //*****************superclass******************************************************************************************

    public   boolean superclass(String s1,String s2) throws IOException {
        query2 q = new query2() ;
        String entity1= null;
        String entity2= null;
        entity1=q.QueryDBpedia(q.BrowsWord(s1));
        entity2=q.QueryDBpedia(q.BrowsWord(s2));

        boolean b  = q.checkSuper(entity1, entity2) ;
        if(b==true){
            System.out.println("superclass");
        }
        return  b;
    }
    //*****************word2vec*********************************************************************************************

  /*  public   boolean word2vect(String s1,String s2) throws IOException {
        word2vecClass w = new word2vecClass() ;
        return (w.similarity(s1,s2));
    }
*/
    //*****************comparaison******************************************************************************************

 /*   public  String compare(String s1 , String s2) throws IOException {
        String relation = "dissimilar" ;
        if (s1.equals(s2)){
            relation = "same" ;
        }else if (Levenshtein(s1,s2)>=0.75){
            relation = "similar" ;
        }else if(IsSynonymVerb(s1,s2)||IsSynonymNoun(s1,s2)){
            relation = "synonyms";
        }else if (subclass(s1,s2)){
            relation = "subclass" ;
        }else if (superclass(s1,s2)){
            relation = "superclass" ;
        }else if (word2vect(s1,s2)){
            relation = "similar";
        }
        return relation ;
    }*/
    //*****************similarity**********************************************************************************************
    public boolean similar(String s1,String s2) throws IOException, CorruptConfigFileException {
        boolean similar = false ;

        //SimilarIdeas s = new SimilarIdeas(s1,s2);
      //  System.out.println(s.getCosineSimilarity());

        if (dan.sim(s1,s2)>=0.7){
            similar = true ;
        } else if(disco.similarity(s1,s2)>=0.7){
            similar = true ;
        }
       /*else if (word2vect(s1,s2)){
            similar = true;
        }*/else if(doc1.similar(s1,s2)){
           similar = true ;
        }/*else if(doc2.similar(s1,s2)){
           similar = true ;
        }*/else if (Levenshtein(s1,s2)>=0.75){
            similar = true ;
       // } else if(s.getCosineSimilarity()>=0.7){
       //     similar = true ;
        }
        return similar ;
    }

    //**********************************************************************************************************************
  /*  public static  void  func(){
        File gModel = new File("/Developer/Vector Models/GoogleNews-vectors-negative300.bin.gz");
        Word2Vec vec = WordVectorSerializer.readWord2VecModel(gModel);
    }*/
   /*public static void main(String[] args) throws IOException, CorruptConfigFileException {
       relationship r = new relationship();

      // System.out.println(r.superclass("window", "architectural element"));
       //  System.out.println(compare("robots","mobile rescue drone"));
       // System.out.println(compare("window","shows"));
      //  System.out.println(r.similar("indicates the exit path","shows the exit path"));

       // System.out.println(Levenshtein("The building consists of a variable structure","the building consists"));



    }*/

    public void similar2(String s1,String s2) throws IOException, CorruptConfigFileException {
        System.out.println(dan.sim(s1,s2));
        System.out.println(disco.similarity(s1,s2));
        System.out.println(doc1.similar(s1,s2));
        System.out.println(Levenshtein(s1,s2));
        System.out.println(IsSynonymNoun(s1,s2));
       // System.out.println(subclass(s1,s2));
      //  System.out.println(superclass(s1,s2));
    }

}
