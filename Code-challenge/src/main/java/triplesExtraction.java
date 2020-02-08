import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.naturalli.NaturalLogicAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.PropertiesUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

/**
 *
 * @author Chahrazed
 */

public class triplesExtraction {

    public static ArrayList<String[]> Annotation_Extraction(String g)
            throws Exception {

        ArrayList<String[]> result = new ArrayList<String[]>();

        Properties props = PropertiesUtils.asProperties("annotators", "tokenize,ssplit,pos,lemma,parse,natlog,openie");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        String text;

        if (g.length() > 0) {
            text = g;
            Annotation doc = new Annotation(text);
            pipeline.annotate(doc);

            for (CoreMap sentence :
                    doc.get(CoreAnnotations.SentencesAnnotation.class)) {

               Collection<RelationTriple> triples =
                 sentence.get(NaturalLogicAnnotations.RelationTriplesAnnotation.class);
                int i = 0 ;
                String s  = "";
                String[] row  = new String[2];
                if (triples.size() == 0) {
                    row = new String[2];
                    row[0] = "one word" ;
                    row[1] = sentence.get(CoreAnnotations.TextAnnotation.class);
                }
                for (RelationTriple triple : triples) {
                    if(s.length() < (triple.subjectLemmaGloss()+ " " +  triple.relationGloss() + " " + triple.objectLemmaGloss()).length())
                    {
                        row = new String[5];
                        row[0] = "triple" ;
                        row[1] = sentence.get(CoreAnnotations.TextAnnotation.class);
                        row[2] = triple.subjectLemmaGloss();
                        row[3] = triple.relationGloss();
                        row[4] = triple.objectLemmaGloss();

                        System.out.println("subject : " + triple.subjectLemmaGloss()+
                                " relation : " +  triple.relationGloss() +
                                " object : " + triple.objectLemmaGloss());

                    }
                    s = triple.subjectLemmaGloss()+ " " +  triple.relationGloss() + " " + triple.objectLemmaGloss() ;

                    if ((triple.confidence == 1)) {
                    }
                }
                int compteur = 0 ;
                for (CoreLabel token: doc.get(CoreAnnotations.TokensAnnotation.class)) {
                    String word = token.get(CoreAnnotations.TextAnnotation.class);
                    String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                    if(pos.startsWith("V")||pos.startsWith("N")){
                        compteur = compteur + 1 ;
                    }
                }
                 if(compteur >= 6){
                    row = new String[2];
                    row[0] = "sentence" ;
                    row[1] = sentence.get(CoreAnnotations.TextAnnotation.class);
                }
                result.add(row);
            }
        }
        return result;
    }
/*
    public static void main(String[] args) throws Exception , ND4JIllegalStateException {

      /*  String[] what = {
                "flying drones holding capsules",
                "waterproof carpet",
                "fire extinguisher with sensory chips",
                "fireproof furniture",
                "Flying Drones",
                "Rescue Indication floor",
                "waterproof floor",
                "Sensory walls",
                "rescue elevator",
                "mini rescue drones",
                "rescue parachutes",
                "evacuation system",
                "air bag suits",
                "carbon monoxide sensitive cells",
                "carbon monoxide detector",
                "emergency application",
                "fireproof suits",
                "fireproof walls",
                "rescue closets",
                "rescue closets in each apartment",
                "heat wave mitigation",
                "threat monitoring",
                "Water Triggers",
                "intelligent system",
                "thermal insulation doors",
                "system of detecting scale in the elevator",
                "robots with weapons",
                "steel doors",
                "Absorption device",
                "an intelligent system equipped with cameras and sensors placed all over the building",
                "big capsules with airbags and parachutes",
                "Fireproof clothing hangs with fire extinguishers",
                "Underground solid basements and toboggans",
                "Evacuation alert",
                "Sensory chips / walls",
                "Stairs in the back of a building",
                "pillows with sensory chips",
                "smart shoes ",
                "smart watch.",
                "emergency TV or radio",
                "oxygen masks placed in the ceiling of each room",
                "stairs that change its structure",
                "retractable stairs",
                "rescue-robots",
                "rescue pipelines",
                "The building has a variable structure",
                "The building has a variable structure",
                "clothes that can be turned into airbags",
                "a table that changes its shape into a solid box",
                "protection helmet",
                 "The building consists of a variable structure",
                 "Window with a heat-sensitive material",
                 "Rescue Indication facade",
                 "changeable building structure",
                 "net attached to the facade of a building",
                 "landscape architecture that changes into an air bed",
                 "rescue-robots, Have their own power supply",
                 "magnet panels are in the shoes and in the floor",
                 "emergency radio",
                 "rescue clouds",
                 "Navigation of the fire department to the seat of fire",
                 "tunnel system is unfolded",
                 "The room is sealed airtight",
                 "rescue parachutes",
                 "Capsule puts itself over the fire",
                 "emergency TV",
                 "flying robots/drones",
                 "Flying Drones",
                 "Safety-Zones on different levels of the building",
                 "retractable building",
                 "formation of a rescue cloud",
                 "Pipe/Airlift",
                 "mobile rescue-drone",
                 "solid exterior space",
                 "Evacuation Application",
                 "intelligent rescue system",
                 "smart fire extinguisher with integrated fire detector",
                 "Position information",
                 "self-destructing material self-destructs, making the hammer redundant",
                 "Window composed of heat-sensitive material",
                 "window",
                 "facade composed of heat-sensitive material",
                 "window",
                 "Window composed of heat-sensitive material",
                 "Smoke",
                 "Window composed of heat-sensitive material",
                 "The building consists of a variable structure",
                 "Changeable building structure",
                 "Changeable building structure",
                 "Flying Drones",
                 "Non-flammable suit",
                 "Window made of heat-sensitive material",
                 "Window made of heat-sensitive material",
                 "Architecture element made of TCO",
                 "Window composed of heat-sensitive material",
                 "Architecture element made of TCO",
                 "Architecture element made of plants",
                 "Architecture element made of plants",
                 "Architecture element cover-up of TCO",
                 "The building consists of a variable structure",
                 "The building consists of a variable structure",
                 "Architecture element cover-up with inflammable material",
                 "The building consists of a variable structure",
                 "The room consists of a variable structure",
                 "Refrigerator made of TCO",
                 "The room consists of a variable structure",
                 "The room consists of a variable structure",
                 "Refrigerator made of inflammable material",
                 "The room consists of a variable structure",
                 "Refrigerator cover-up of inflammable material",
                 "The room consists of inflammable material",
                 "The room consists of a inflammable material",
                 "Window",
                 "Structure element",
                 "Structure element",
                 "Window",
                 "Window",
                 "Window",
                 "Facade",
                 "Facade",
                 "Intelligent rescue system",
                 "Mobile app",
                 "Intelligent rescue system",
                 "Window",
                 "Flying robots",
                 "Mobile rescue-drone",
                 "Mobile rescue-drone",
                 "changeable building structure",
                 "Mobile rescue-drone"
        };

        String[] how = {
                "put those capsules over a fire to extinguish it by deoxygenation",
                "that absorbs water",
                "placed on the roof, it detects fire and spray automatically carbon dioxide to stop it",
                "furniture made of a substance resistant to fire",
                "holding water bags  that track the fire location and poor water to stop it",
                "that lights up to show the exit path",
                "that absorbs water",
                "that raises an alarm for evacuation and stimulates downpour of water and absorbs smoke",
                "that normally functions as a balcony and is re-functionalized into an elevator that takes people to the ground safely",
                "that light up and guide people through exit path",
                "attached to the facade of the building that helps poeple escape",
                "that identifies emergency cases in building for example a fire, trigger an alert,  shows how to evacuate the building, indicates the exit paths and the precautions to be taken",
                "that saves poeple",
                "supplying each light switch with carbon monoxide sensitive cells,  that cuts electricity if there is a gas leak",
                "in each apartment,  that detects and absorbs carbon monoxide",
                "via a computer in each apartment,  that contains information about the escape roots,   first aid, police and ambulance phone number etc. in different languages",
                "that are supplied with oxygen bags",
                "walls of the building are painted with fireproof substance",
                "that normally functions as a closet,  it turns into an escape elevator that supplies people with oxygen",
                "that can be used as an escape elevator,  it supplies people with oxygen",
                "for many people heat is dangerous. therefore a buildng should intelligently regulate the inside temperature depending on time of day , number and location of people in the building",
                "constant feed of information regarding e.g.  air quality warnings,  heat waves  dangerous,  traffic incidents or crimes in the vicinity. delivered to people  e.g. by infodisplays smartphones or other mean",
                "Avoiding the fire propagation",
                "early detection of threats and rapid evacuation",
                "close empty areas to contain fire",
                "that blocks it from working if the weight is higher than the weight threshold",
                "like robocap,  neutralizes terrorist with weapons",
                "solid doors that can be closed automatically and rapidly",
                "A sensitive gas leakage system that absorbs the leaking gas and filters the air",
                "detect and evaluate emergencies,  then propose solutions (call the police/firemen,  trigger an alarm...)",
                "people enter into the capsules and jump out of the building",
                "Wear fireproof clothing",
                "Evacuate the building rapidly using toboggans to the basements",
                "Developing an effective security system to which phones and laptops are connected. It shows how to evacuate the building and the precautions to be taken ",
                "raises a loud alarm for evacuation or stimulates immediate downpour of water for the fire ",
                "It helps as an escape route",
                "that can shack up anyone who is asleep",
                "those shoes can track and guide the person through the escape path to evacuate the building using a laser",
                "that predicts danger situations, launches an alarm, and tracks the escape path to evacuate the building safely.",
                " that gives instructions for evacuation, guides people through exit doors and calms them down",
                "they are dropped down automatically",
                "it turns into unbreakable solid tunnel to help people slide into it safely",
                "placed under windows and balconies, they are used to escape from the building",
                "with their own power supply, that are in the different floors of the building and are activated, they manage the evacuation plan and guide people through the exit path",
                "hanging on building facade, they serve as as an escape route",
                "the variable structure helps it completely descents to the ground, it is supplied with Ventilation system and heat control system",
                "the building completely descents to the ground",
                "they cover the person body",
                "the box is made of steel",
                "that is transformed into airbags. used to protect the head from serious injuries",
                 "the structure opens, encloses the person and descents to the ground",
                 "that lights-up",
                 "The facade indicates in which floor",
                 "that changes its structure to a party zone",
                 "that works as a slide and therefore as an escape route",
                 "secures people jumping out of a window",
                 "that are in the different floors of the building and are activated",
                 "The shoes (and therefore the persons) are guided along the rescure-route automatically",
                 "Instructions about necessary behavior. Calming of the guests",
                 "Clouds then hover to the ground",
                 "protective system that identifies the seat of fire and detects smoke development",
                 "Leads people through the hotel. Save navigation to the exit",
                 "that can be used as an escape route and supplies people with oxygen no smoke can get in . A ventilation system supplies oxygen",
                 "parachutes in the upper levels of high-rise buildings for escape route",
                 "Extinguishing of a fire by deoxygenation",
                 "informations for hotel guests via a TV/Computer in the room, Individual using the language of the guests",
                 "that generate light and send information to the rescue teams",
                 "that place rescue robots on higher levels of high-rise buildings",
                 "Exit/Communication to the outside",
                 "the building completely retracts into the ground",
                 "rescue from great heights by changing the information and formation of specific particles in the atmosphere",
                 "safe passage between building an helicopter",
                 "that docks to buildings and serves as an escape route",
                 "that normally functions as a balcony and is refunctionalized into a panic-room and is grounded using ropes or helicopters",
                 "that analyses dangerous situations and forwards informations to smartphones via an App. Individualized emergency escape routes by exact determination in the object and knowledge about the danger source",
                 "that identifies accidents in buildings and assumes emergency navigation. Transferable to traffic systems, major events etc.",
                 "Shows escape routes and absorbes smoke",
                 "display of evacuation routes, display of visible and invisible dangers (e.g. radiation). sending and receiving. thermal image",
                 "Emergency Hammer with integrated emergency message, gps-localization, display with emergency informations like first-aid-instructions",
                 "indicates the exit path",
                 "shows the exit path",
                 "shows person position",
                 "paints the exit path",
                 "indicates the escape route",
                 "shows the exit path",
                 "sends information to fire department",
                 "indicates the escape route",
                 "shows the exit path",
                 "transforms to flying jet",
                 "shows the exit path",
                 "that activates and cover person body",
                 "shows the exit path",
                 "sends position info",
                 "shows the exit path",
                 "indicates the position",
                 "prints exit path on wall",
                 "shows the exit path",
                 "sends position information through the smell",
                 "shows the exit path",
                 "transforms in to flying bed",
                 "covers person body",
                 "packs human",
                 "turns into a tunnel",
                 "capsules human",
                 "packs human",
                 "covers person",
                 "turns into a tunnel",
                 "packs human",
                 "packs human",
                 "packs human",
                 "packs human",
                 "absorbs fire",
                 "indicates the escape root",
                 "shows the exit path",
                 "transforms to a cloud",
                 "generates lights",
                 "sends information about the position",
                 "indicates the exit path",
                 "shows the exit path",
                 "sends information about the position",
                 "shows the exit path",
                 "indicates the escape root",
                 "paints exit path on wall",
                 "transforms to cloud",
                 "move person to outside",
                 "guide person to exit path",
                 "docks the wall into escape root",
                 "docks into escape root",
                 "move persons outside"
        };

    /*    ArrayList<String[][]> ideas = new ArrayList<>();
        for (int i = 0 ; i < what.length ; i++){
            int x = 1 ;
            String[][] id = new String[6][2];
            id[0][0] = what[i] + ";;;" + how[i];
            for (int j = i+1  ; j < what.length ; j++){
                SimilarIdeas s = new SimilarIdeas(what[i]+ ", " + how[i],what[j]+ ", " + how[j]);
                double d =  s.getCosineSimilarity() ;
                    if (id[x][0] == null) {
                        id[x][0] =  what[j] + ";;;" + how[j];
                        id[x][1] = String.valueOf(d) ;
                        if (x < 5){
                            x = x + 1 ;
                        }else {
                           // Arrays.sort(id);
                            for (int k = 1 ; k < 6 ; k++){
                                for (int n = 2 ; n < 6 ; n++){
                                    double max = Double.valueOf(id[k][1]);
                                    if (max < Double.valueOf(id[n][1])){
                                        max =  Double.valueOf(id[n][1]) ;
                                        id[n][1] = id[k][1] ;
                                        id[k][1] = String.valueOf(max) ;
                                    }
                                }
                            }
                        }
                    }else {
                        for (int k = 1 ; k < 6 ; k++){
                            if (Double.valueOf(id[k][1]) <= d){
                                double max = Double.valueOf(id[k][1]);
                                String ide =  id[k][0] ;
                                id[k][0] = what[j] + ";;;" + how[j];
                                id[k][1] = String.valueOf(d) ;
                                for(int t = k+1 ; t < 6 ; t++){
                                     double max2 = Double.valueOf(id[t][1]);
                                     String ide2 =  id[t][0] ;
                                     id[t][0] =  ide;
                                     id[t][1] = String.valueOf(max) ;
                                     max = max2 ;
                                     ide = ide2 ;
                                }
                                break ;
                            }
                        }
                    }
            }*/
    /*  ArrayList<double[][]> ideas = new ArrayList<>();
        for (int i = 0 ; i < what.length ; i++){
            int x = 0 ;
            double[][] id = new double[what.length][2];
            id[x][0] = i;
            for (int j = i+1  ; j < what.length ; j++){
                x = x + 1;
                SimilarIdeas s = new SimilarIdeas(what[i]+ ", " + how[i],what[j]+ ", " + how[j]);
                double d =  s.getCosineSimilarity() ;
                id[x][0] = j;
                id[x][1] = d;
                System.out.println(id[x][1]);
            }
            ideas.add(id);
            //System.out.println("-------------------------------------------------------------------------------");
        }


        for (int i = 0 ; i < ideas.size() ; i++){

            java.util.Arrays.sort(ideas.get(i), new java.util.Comparator<double[]>() {
                public int compare(double[] a, double[] b) {
                    return Double.compare(a[1], b[1]);
                }
            });

        }


        rules R = new rules();

        for (int i = 0 ; i < 1  ; i++){
            System.out.println("______________________________________________________________________________________");
            System.out.println("iteration i : " + i);
            System.out.println("______________________________________________________________________________________");

            for (int j = ideas.get(i).length-1 ; j >  ideas.get(i).length - 6; j--){
                System.out.println( what[(int) ideas.get(i)[j][0]] + ";;;"+ how[(int) ideas.get(i)[j][0]]);
            }

            for (int j = ideas.get(i).length-1 ; j >  ideas.get(i).length - 6; j--){
                System.out.println("----------------------------------------------------------------------------------------");
                System.out.println("iteration j : " + j  );
                System.out.println("----------------------------------------------------------------------------------------");

                int idea1 = (int) ideas.get(i)[0][0];
                int idea2 = (int) ideas.get(i)[j][0];

                ArrayList<String[]> triplesWhat1 = new ArrayList<String[]>();
                ArrayList<String[]> triplesWhat2 = new ArrayList<String[]>() ;


                ArrayList<String[]> triplesHow1 = new ArrayList<String[]>();
                ArrayList<String[]> triplesHow2 = new ArrayList<String[]>();


                triplesWhat1 = Annotation_Extraction(what[idea1]);
                triplesWhat2 = Annotation_Extraction(what[idea2]);

                triplesHow1 = Annotation_Extraction(how[idea1]);
                triplesHow2 = Annotation_Extraction(how[idea2]);

                String rel = R.whatRules(triplesWhat1, triplesWhat2, triplesHow1, triplesHow2) ;
                // System.out.println("triples : " + triplesWhat1 + "...."+ triplesHow1 + "\n triples : " + triplesWhat2 + "...." + triplesHow2);
                System.out.println("relationship : " + rel);
                System.out.println(what[idea1] + ";;;;" + how[idea1] + ";;;" + what[idea2] +
                        ";;;;" + how[idea2] + ";;;" + rel);
                System.out.println("______________________________________________________________________________________");

            }
        }
    }*/

      /*     String[] what = {" self-destructing material self-destructs, making the hammer redundant ",
                " Position information",
                "smart fire extinguisher with integrated fire detector",
                "intelligent rescue system",
                "Evacuation Application ",
                "solid exterior space",
                "mobile rescue-drone",
                "Pipe/Airlift",
                "formation of a rescue cloud",
                "retractable building",
                " Safety-Zones on different levels of the building",
                "Flying Drones	",
                "flying robots/drones",
                "emergency TV	",
                " Capsule puts itself over the fire",
                "rescue parachutes ",
                "The room is sealed airtight ",
                "tunnel system is unfolded",
                "Navigation of the fire department to the seat of fire",
                "rescue clouds",
                "emergency radio ",
                "magnet panels are in the shoes and in the floor ",
                "rescue-robots, Have their own power supply",
                "landscape architecture that changes into an air bed",
                "net attached to the facade of a building",
                "changeable building structure",
                "Rescue Indication facade",
                "Window with a heat-sensitive material",
                "The building consists of a variable structure",
                "heat wave mitigation",
                "threat monitoring",
                "intelligent system",
                "thermal insulation doors",
                " system of detecting scale in the elevator",
                "robots with weapons, like robocap",
                "steel doors",
                "Absorption device",
                "an intelligent system equipped with cameras and sensors placed all over the building",
                "big capsules with airbags and parachutes",
                "Fireproof clothing hangs with fire extinguishers",
                "Underground solid basements and toboggans",
                "Evacuation alert",
                "Sensory chips/walls",
                "Stairs in the back of a building"
                };

        String[] when = {
                "In case of an emergency",
                " in case of a catastrophe",
                "in case of a fire ",
                "people that need to be rescued",
                "In case of an emergency",
                "In case of an emergency",
                "In case of an emergency",
                "In case of an emergency",
                "in case of an emergency ",
                "in case of a terrorist warning	",
                " In case of an emergency	",
                " In case of an emergency	",
                " In case of an emergency",
                " In case of an emergency",
                "in case of a fire ",
                "in case of a fire ",
                "so that in case of a fire",
                " in case of a fire",
                "the seat of fire and detects smoke",
                "People jump out of the window",
                "in case of emergency",
                "in case of emergency",
                "in case of emergency",
                "in an emergency	",
                "in case of an emergency	",
                "in case of an emergency, for example during a terrorist warning ",
                "people that need to be rescued",
                "if a person is in the room during a fire",
                "In case of an emergency ",
                "constantly",
                "always",
                "all the time",
                "in case of a fire ",
                "all the time ",
                "in case of a terrorist attack",
                "in case of a terrorist attack",
                "in case of gas leakage",
                "all the time",
                "in case of an emergency",
                "In case of a fire",
                "In case of an earthquake",
                "Once a fire, no matter how big or small, starts",
                "In case of an emergency",
                "In case of an emergency"};

        String[] how = {
                " Emergency Hammer with integrated emergency message, gps-localization, display with emergency informations like first-aid-instructions",
                " display of evacuation routes, display of visible and invisible dangers (e.g. radiation). sending and receiving. thermal image",
                "Shows escape routes and absorbes smoke",
                " that identifies accidents in buildings and assumes emergency navigation. Transferable to traffic systems, major events etc ",
                "that analyses dangerous situations and forwards informations to smartphones via an App. Individualized emergency escape routes by exact determination in the object and knowledge about the danger source",
                "that normally functions as a balcony and is refunctionalized into a panic-room and is grounded using ropes or helicopters",
                "that docks to buildings and serves as an escape route",
                " safe passage between building an helicopter",
                " rescue from great heights by changing the information and formation of specific particles in the atmosphere ",
                "the building completely retracts into the ground ",
                " Exit/Communication to the outside ",
                " that place rescue robots on higher levels of high-rise buildings",
                " that generate light and send information to the rescue teams",
                " informations for hotel guests via a TV/Computer in the room, Individual using the language of the guests",
                " Extinguishing of a fire by deoxygenation ",
                " parachutes in the upper levels of high-rise buildings for escape route",
                " that can be used as an escape route and supplies people with oxygen no smoke can get in . A ventilation system supplies oxygen",
                "Leads people through the hotel. Save navigation to the exit",
                " protective system that identifies the seat of fire and detects smoke development",
                "Clouds then hover to the ground	",
                "Instructions about necessary behavior. Calming of the guests",
                "The shoes (and therefore the persons) are guided along the rescure-route automatically ",
                "that are in the different floors of the building and are activated",
                "secures people jumping out of a window	",
                "that works as a slide and therefore as an escape route",
                "that changes its structure to a party zone",
                "The facade indicates in which floor",
                "that lights-up ",
                "the structure opens, encloses the person and descents to the ground",
                "for many people heat is dangerous. therefore a buildng should intelligently regulate the inside temperature depending on time of day, number and location of people in the building,...",
                "constant feed of information regarding e.g., air quality warnings, heat waves, dangerous traffic incidents or crimes in the vicinity. delivered to people, e.g., by infodisplays, smartphones or other means,",
                "early detection of threats and rapid evacuation",
                "close empty areas to contain fire",
                "that blocks it from working if the weight is higher than the weight threshold",
                "neutralizes terrorist with weapons",
                "solid doors that can be closed automatically and rapidly",
                "A sensitive gas leakage system that absorbs the leaking gas and filters the air",
                "detect and evaluate emergencies, then propose solutions (call the police/firemen, trigger an alarm...)",
                "people enter into the capsules and jump out of the building",
                "Wear fireproof clothing",
                "Evacuate the building rapidly using toboggans to the basements",
                "Developing an effective security system to which phones and laptops are connected. It shows how to evacuate the building and the precautions to be taken",
                "raises a loud alarm for evacuation or stimulates immediate down pour of water for the fire",
                "It helps as an escape route"
        };*/

       String[] what ={"Window composed of heat-sensitive material" ,
                "window",
                "window composed of heat-sensitive material",
                "facade",
                "Window composed of heat-sensitive material" ,
                "Smoke",
                "Window composed of heat-sensitive material",
                "Smoke",
                "The building consists of a variable structure",
                "Changeable building structure",
                "The building consists of a variable structure",
                "Changeable building structure",
                "The building consists of a variable structure",
                "Flying Drones",
                "The building consists of a variable structure",
                "Non flammable suit",
                "Window composed of heat-sensitive material",
                "Window made of heat-sensitive material",
                "Window composed of heat-sensitive material ",
                "Window made of heat-sensitive material",
                "Window composed of heat-sensitive material",
                "Architecture element made of TCO ",
                "Window composed of heat-sensitive material ",
                "Architecture element made of TCO ",
                "Window composed of heat-sensitive material",
                "Architecture element made of plants",
                "Window composed of heat-sensitive material",
                "Architecture element made of plants ",
                "The building consists of a variable structure",
                "Architecture element cover-up of TCO",
                "The building consists of a variable structure",
                "Architecture element cover-up of TCO",
                "The building consists of a variable structure",
                "Architecture element cover-up with inflammable material",
                "The building consists of a variable structure",
                "Architecture element cover-up with inflammable material",
                "The room consists of a variable structure",
                "Refrigerator made of TCO",
                "The room consists of a variable structure",
                "Refrigerator made of TCO",
                "The room consists of a variable structure",
                "Refrigerator made of inflammable material",
                "The room consists of a variable structure",
                "Refrigerator made of inflammable material ",
                "The room consists of a variable structure ",
                "Refrigerator cover-up of inflammable material",
                "The room consists of a variable structure",
                "Refrigerator cover-up of inflammable material",
                "The room consists of inflammable material",
                "Refrigerator cover-up of inflammable material",
                "The room consists of a inflammable material",
                "Refrigerator cover-up of inflammable material",
                "Window",
                "Structure element",
                "Window",
                "Structure element",
                "Window",
                "Window",
                "Window",
                "Window",
                "Window",
                "Facade",
                "Window",
                "Facade",
                "Window",
                "Smoke",
                "Smoke",
                "Window",
                "Intelligent rescue system",
                "Mobile app",
                "Intelligent rescue system",
                "Mobile app",
                "Intelligent rescue system",
                "Window",
                "Intelligent rescue system",
                "Window",
                "Flying robots",
                "Mobile rescue-drone",
                "Flying robots",
                "Mobile rescue-drone",
                "changeable building structure",
                "Mobile rescue-drone",
                "changeable building structure",
                "Mobile rescue-drone"
        } ;
        //String[] when ={""} ;
        String[] how ={ "indicate the exit path",
                "show the exit path",
                "shows person position",
                "paints the exit path",
                "indicate the escape route",
                "show the exit path",
                "sends information to fire department",
                "shows the exit path",
                "indicate the escape route",
                "show the exit path",
                "indicates the escape route",
                "transforms to flying jet",
                "indicate the escape route",
                "show the exit path",
                "indicates the escape route",
                "that’s activates and cover person body",
                "indicate the exit path",
                "show the exit path",
                "indicates the exit path",
                "sends position info",
                "indicate the exit path",
                "show the exit path",
                "indicates the position",
                "prints exit path on wall",
                "indicate the exit path",
                "show the exit path",
                "indicates the exit path",
                "sends position information through the smell",
                "indicate the exit path",
                "show the exit path",
                "transforms in to flying bed",
                "shows the exit path",
                "covers person body",
                "packs human",
                "turns into a tunnel",
                "packs human",
                "capsules human",
                "packs human",
                "turns into a tunnel",
                "packs human",
                "covers person",
                "packs human",
                "turns into a tunnel",
                "packs human",
                "packs human",
                "packs human",
                "turns into a tunnel",
                "packs human",
                "packs human",
                "packs human",
                "absorbs fire",
                "packs human",
                "indicate the escape root",
                "show the exit path",
                "indicates the escape root",
                "transforms to a cloud",
                "indicate the escape root",
                "show the exit path",
                "generates lights",
                "sends information about the position",
                "indicates the exit path",
                "shows the exit path",
                "shows the exit path",
                "sends information about the position",
                "indicates the exit path",
                "shows the exit path",
                "shows the exit path",
                "sends information about the position",
                "shows the exit path",
                "indicates the escape root",
                "paints exit path on wall",
                "indicate the escape root",
                "show the exit path",
                "indicate the escape root",
                "show the exit path",
                "transforms to cloud",
                "move person to outside",
                "guide person to exit path",
                "move person to outside",
                "docks the wall into escape root",
                "docks into escape root",
                "docks the wall into escape root",
                "docks into escape root",
                "move persons outside"
        } ;



 /*       String[] what ={ "rescue closets ",
                "  rescue closets "} ;
        String[] how = { "that normally functions as a closet. it turns into an escape elevator that supplies people with oxygen ",
                "that can be used as an escape elevator. it supplies  people with oxygen"} ;
*/
        ArrayList<ArrayList<String[]>> what_about_triples = new ArrayList<ArrayList<String[]>>();
        //ArrayList<ArrayList<String[]>> when_triples = new ArrayList<ArrayList<String[]>>();
        ArrayList<ArrayList<String[]>> how_triples = new ArrayList<ArrayList<String[]>>();



     /*   for (int i = 0; i < what.length; i++) {
            what_about_triples.add(Annotation_Extraction(what[i]));
        }
       /* for (int i = 0; i < when.length; i++) {
            when_triples.add(Annotation_Extraction(when[i]));
        }*/
  /*      for (int i = 0; i < how.length; i++) {
            how_triples.add(Annotation_Extraction(how[i]));
        }

    /*   // System.out.println("what about :");
        for (int i = 0; i < what_about_triples.size() ; i++) {
            System.out.println("******************************idea"+ i +"***************************");
            for (int j = 0 ; j < what_about_triples.get(i).size() ; j++){
                if(what_about_triples.get(i).get(j).length==2){

                    System.out.println(what_about_triples.get(i).get(j)[1]);

                    if(what_about_triples.get(i).get(j)[0].contains("one word"))
                    {
                        System.out.println("it is a one word");
                    }
                }else {
                    System.out.println(what_about_triples.get(i).get(j)[1]);
                    System.out.println("subject : " + what_about_triples.get(i).get(j)[2]);
                    System.out.println("relation : " + what_about_triples.get(i).get(j)[3]);
                    System.out.println("object : " + what_about_triples.get(i).get(j)[4]);
                }
            }
        }
       /* System.out.println("when  :");
        for (int i = 0; i < when_triples.size() ; i++) {
            System.out.println("******************************idea"+ i +"***************************");
            for (int j = 0 ; j < when_triples.get(i).size() ; j++){
                if(when_triples.get(i).get(j).length==2){
                    System.out.println(when_triples.get(i).get(j)[1]);
                }else {
                    System.out.println(when_triples.get(i).get(j)[1]);
                    System.out.println("subject : " + when_triples.get(i).get(j)[2]);
                    System.out.println("relation : " + when_triples.get(i).get(j)[3]);
                    System.out.println("object : " + when_triples.get(i).get(j)[4]);
                }
            }
        }*/
    /*System.out.println("how :");
        for (int i = 0; i < how_triples.size() ; i++) {
            System.out.println("******************************idea"+ i +"***************************");
            for (int j = 0 ; j < how_triples.get(i).size() ; j++){
                if(how_triples.get(i).get(j).length==2){
                    System.out.println(how_triples.get(i).get(j)[1]);
                    System.out.println(how_triples.get(i).get(j)[0]);
                }else {
                    System.out.println(how_triples.get(i).get(j)[1]);
                    System.out.println("subject : "+how_triples.get(i).get(j)[2]);
                    System.out.println("relation : "+how_triples.get(i).get(j)[3]);
                    System.out.println("object : "+how_triples.get(i).get(j)[4]);
                }
            }
        }
*/
 /*     System.out.println("rules :");
        rules R = new rules();
        ArrayList<String> result = new ArrayList<String>();

       // for(int j =0; j<31;j++) {
            for (int i = 2; i < 6; i=i+2) {
                System.out.println("******* iteration : " + i + "**********");
                System.out.println(what[i] + " " + how[i] + " ; " + what[i+1] + " " + how[i+1]);
                System.out.println(R.whatRules(what_about_triples.get(i), what_about_triples.get(i+1), how_triples.get(i), how_triples.get(i+1)));
              //  result.add(R.whatRules(what_about_triples.get(0), what_about_triples.get(1), how_triples.get(0), how_triples.get(1)));
              // System.out.print(" ; " + result.get(0));
                System.out.println("***************************************************************************************************************************");

          //  }
        }
     /*   for(int j =14; j<15;j++) {
            for (int i = 17; i < 20; i++) {
                System.out.println(what[i] + " " + how[i] + " ; " + what[j] + " " + how[j]+ " ; " + result.get(i-13));
                System.out.println("*****************************************************************************************************");
            }
*/
   // }



}
