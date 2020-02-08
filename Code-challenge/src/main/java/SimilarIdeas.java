import java.io.IOException;
import java.util.*;

import org.apache.commons.math3.linear.*;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.*;
import org.apache.lucene.store.*;
import org.apache.lucene.util.*;

public class SimilarIdeas {

    public static final String CONTENT = "Content";

    private final Set<String> terms = new HashSet<>();
    private final RealVector v1;
    private final RealVector v2;

    SimilarIdeas(String s1, String s2) throws IOException {
        Directory directory = createIndex(s1, s2);
        IndexReader reader = DirectoryReader.open(directory);
        Map<String, Integer> f1 = getTermFrequencies(reader, 0);
        Map<String, Integer> f2 = getTermFrequencies(reader, 1);
        reader.close();
        v1 = toRealVector(f1);
        v2 = toRealVector(f2);
    }


    Directory createIndex(String s1, String s2) throws IOException {
        Directory directory = new RAMDirectory();
        Analyzer analyzer = new SimpleAnalyzer(Version.LUCENE_CURRENT);
        IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_CURRENT,
                analyzer);
        IndexWriter writer = new IndexWriter(directory, iwc);
        addDocument(writer, s1);
        addDocument(writer, s2);
        writer.close();
        return directory;
    }

    /* Indexed, tokenized, stored. */
    public static final FieldType TYPE_STORED = new FieldType();

    static {
        TYPE_STORED.setIndexed(true);
        TYPE_STORED.setTokenized(true);
        TYPE_STORED.setStored(true);
        TYPE_STORED.setStoreTermVectors(true);
        TYPE_STORED.setStoreTermVectorPositions(true);
        TYPE_STORED.freeze();
    }

    void addDocument(IndexWriter writer, String content) throws IOException {
        Document doc = new Document();
        Field field = new Field(CONTENT, content, TYPE_STORED);
        doc.add(field);
        writer.addDocument(doc);
    }

    double getCosineSimilarity() {
        return (v1.dotProduct(v2)) / (v1.getNorm() * v2.getNorm());
    }

    public static double getCosineSimilarity(String s1, String s2)
            throws IOException {
        return new SimilarIdeas(s1, s2).getCosineSimilarity();
    }

    Map<String, Integer> getTermFrequencies(IndexReader reader, int docId)
            throws IOException {
        Terms vector = reader.getTermVector(docId, CONTENT);
        TermsEnum termsEnum = null;
        termsEnum = vector.iterator(termsEnum);
        Map<String, Integer> frequencies = new HashMap<>();
        BytesRef text = null;
        while ((text = termsEnum.next()) != null) {
            String term = text.utf8ToString();
            int freq = (int) termsEnum.totalTermFreq();
            frequencies.put(term, freq);
            terms.add(term);
        }
        return frequencies;
    }

    RealVector toRealVector(Map<String, Integer> map) {
        RealVector vector = new ArrayRealVector(terms.size());
        int i = 0;
        for (String term : terms) {
            int value = map.containsKey(term) ? map.get(term) : 0;
            vector.setEntry(i++, value);
        }
        return (RealVector) vector.mapDivide(vector.getL1Norm());
    }

 /*   public static void main(String[] args) throws IOException {
        String[] ideas = {
                " flying drones holding capsules put those capsules over a fire to extinguish it by deoxygenation",
                " waterproof carpet  that absorbs water",
                " fire extinguisher with sensory chips placed on the roof it detects fire and spray automatically carbon dioxide to stop it",
                " fireproof furniture furniture made of a substance resistant to fire",
                " Flying Drones holding water bags  that track the fire location and poor water to stop it",
                " Rescue Indication floor that lights up to show the exit path",
                " waterproof floor that absorbs water",
                " Sensory walls that raises an alarm for evacuation  stimulates downpour of water and absorbs smoke",
                " rescue elivator that normally functions as a balcony and is refunctionalized into an elevator that takes people to the ground safely",
                " mini rescue drones that light up and guide people through exit path",
                " rescue parachutes attached to the facade of the building that helps poeple escape ",
                " evacuation system that identifies emergency cases in building for example a fire trigger an alert  shows how to evacuate the building indicates the exit paths and the precautions to be taken",
                " air bag suits  that saves poeple ",
                " carbon monoxide sensitive cells supplying each light switch with carbon monoxide sensitive cells  that cuts electricity if there is a gas leak",
                " carbon monoxide detector in each appartement  that detects and absorbs carbon monoxide ",
                " emergency application  via a computer in each appartement  that contains information about the escape roots   first aid police and ambulance phone number etc. in different languages ",
                " fireproof suits  that are supplied with oxygen bags ",
                " fireproof walls  walls of the building are painted with fireproof substance ",
                " rescue closets that normally functions as a closet  it turns into an escape elevator that supplies people with oxygen ",
                " rescue closets in each apartment  that can be used as an escape elevator  it supplies people with oxygen ",
                " heat wave mitigation for many people heat is dangerous. therefore a buildng should intelligently regulate the inside temperature depending on time of day  number and location of people in the building…",
                " threat monitoring constant feed of information regarding e.g.  air quality warnings  heat waves  dangerous  traffic incidents or crimes in the vicinity. delivered to people  e.g. by infodisplays smartphones or other mean",
                " Water Triggers  Avoiding the fire propagation ",
                " intelligent system early detection of threats and rapid evacuation",
                " thermal insulation doors close empty areas to contain fire",
                " system of detecting scale in the elevator that blocks it from working if the weight is higher than the weight threshold",
                " robots with weapons like robocap  neutralizes terrorist with weapons",
                " steel doors solid doors that can be closed automatically and rapidly",
                " Absorption device A sensitive gas leakage system that absorbs the leaking gas and filters the air ",
                " an intelligent system equipped with cameras and sensors placed all over the building detect and evaluate emergencies  then propose solutions (call the police/firemen  trigger an alarm...) ",
                " big capsules with airbags and parachutes people enter into the capsules and jump out of the building",
                " Fireproof clothing hangs with fire extinguishers Wear fireproof clothing",
                " Underground solid basements and toboggans Evacuate the building rapidly using toboggans to the basements",
                " Evacuation alert  Developing an effective security system to which phones and laptops are connected. It shows how to evacuate the building and the precautions to be taken ",
                " Sensory chips/walls raises a loud alarm for evacuation or stimulates immediate down pour of water for the fire ",
                " Stairs in the back of a building It helps as an escape route",
                " self-destructing material self-destructs making the hammer redundant Emergency Hammer with integrated emergency message gps-localization display with emergency informations like first-aid-instructions",
                " Position information display of evacuation routes display of visible and invisible dangers (e.g. radiation). sending and receiving. thermal image",
                " smart fire extinguisher with integrated fire detector Shows escape routes and absorbes smoke",
                " intelligent rescue system that identifies accidents in buildings and assumes emergency navigation. Transferable to traffic systems major events etc. ",
                " Evacuation Application  that analyses dangerous situations and forwards informations to smartphones via an App. Individualized emergency escape routes by exact determination in the object and knowledge about the danger source",
                " solid exterior space that normally functions as a balcony and is refunctionalized into a panic-room and is grounded using ropes or helicopters",
                " mobile rescue-drone that docks to buildings and serves as an escape route",
                " Pipe/Airlift safe passage between building an helicopter",
                " formation of a rescue cloud rescue from great heights by changing the information and formation of specific particles in the atmosphere ",
                " retractable building the building completely retracts into the ground ",
                " Safety-Zones on different levels of the building Exit/Communication to the outside ",
                " Flying Drones  that place rescue robots on higher levels of high-rise buildings",
                " flying robots/drones that generate light and send information to the rescue teams",
                " emergency TV  informations for hotel guests via a TV/Computer in the room Individual using the language of the guests",
                " Capsule puts itself over the fire Extinguishing of a fire by deoxygenation ",
                " rescue parachutes  parachutes in the upper levels of high-rise buildings for escape route",
                " The room is sealed airtight  that can be used as an escape route and supplies people with oxygen no smoke can get in . A ventilation system supplies oxygen",
                " tunnel system is unfolded Leads people through the hotel. Save navigation to the exit",
                " Navigation of the fire department to the seat of fire protective system that identifies the seat of fire and detects smoke development",
                " rescue clouds Clouds then hover to the ground ",
                " emergency radio  Instructions about necessary behavior. Calming of the guests",
                " magnet panels are in the shoes and in the floor  The shoes (and therefore the persons) are guided along the rescure-route automatically ",
                " rescue-robots Have their own power supply  that are in the different floors of the building and are activated",
                " landscape architecture that changes into an air bed secures people jumping out of a window ",
                " net attached to the facade of a building that works as a slide and therefore as an escape route",
                " changeable building structure that changes its structure to a party zone",
                " Rescue Indication facade The facade indicates in which floor",
                " Window with a heat-sensitive material that lights-up ",
                " The building consists of a variable structure the structure opens  encloses the person and descents to the ground"};

        for (int i = 0 ; i < ideas.length ; i++){
            for (int j = i+1  ; j < ideas.length ; j++){
                SimilarIdeas s = new SimilarIdeas(ideas[i],ideas[j]);
                double d =  s.getCosineSimilarity() ;
                if(d >= 0.1){

                    System.out.println(ideas[i] + "," + ideas[j] + "," + d );

                }
            }
        }
    }*/
}