import de.linguatools.disco.CorruptConfigFileException;
import java.io.IOException;
import java.util.ArrayList;

public class rules {
    public ArrayList<String[]> what, how;
    public relationship rel = new relationship();

    public rules() throws IOException  {
    }
    public String whatRules(ArrayList<String[]> what1,
                            ArrayList<String[]> what2,
                            ArrayList<String[]> how1,
                            ArrayList<String[]> how2)
            throws IOException, CorruptConfigFileException {
        String relation = "no relation";

        if ( (what1.size() == 1 && what1.get(0).length == 5 &&
                what1.get(0)[0]=="triple" && what2.size() == 1
                && what2.get(0).length == 2
                && what2.get(0)[0].equals("one word")) ||
                (what2.size() == 1 && what2.get(0).length == 5 &&
                        what2.get(0)[0]=="triple"  && what1.size() == 1
                        && what1.get(0).length == 2
                        && what1.get(0)[0].equals("one word"))){

            String s1, o1, w ;
            if (what1.get(0).length == 5) {
                s1 = what1.get(0)[2];
                o1 = what1.get(0)[4];
                w = what2.get(0)[1];
            }else{
                s1 = what2.get(0)[2];
                o1 = what2.get(0)[4];
                w = what1.get(0)[1];
            }
            // System.out.println(s1 + "  + + " + o1);
            System.out.println("one word " + w);
            System.out.println("rule 1");
            if (s1.equals(w)||o1.equals(w)||rel.superclass(s1,w)||rel.subclass(s1,w)||rel.subclass(o1,w)||rel.superclass(o1,w)||rel.similar(s1,w)||rel.similar(o1,w)){
                if(sameHow(how1,how2)){
                    relation = "generalizes";
                }else {
                    relation = "alternative solution" ;
                }
            } else {
                if(sameHow(how1,how2)){
                    relation = "similar";
                }else {
                    relation = "disjoint";
                }
            }
        } else if( (what1.size() == 1 && what1.get(0).length == 5 &&  what1.get(0)[0]=="triple" &&
                (what2.size() > 1 || (what2.size() == 1 && what2.get(0).length == 2 && what2.get(0)[0].equals("sentence"))) )
                ||
                (what2.size() == 1 && what2.get(0).length == 5 &&  what2.get(0)[0]=="triple" &&
                        (what1.size() > 1 || (what1.size() == 1 && what1.get(0).length == 2 && what1.get(0)[0].equals("sentence"))))
        )
        {
            System.out.println("rule 2");
            String s1, o1, sequence;
            if(what1.size() == 1 &&  what1.get(0).length == 5){
                s1 = what1.get(0)[2];
                o1 = what1.get(0)[4];
                sequence = "" ;
                for (int i = 0 ; i < what2.size() ; i++){
                    sequence = sequence + " " + what2.get(i)[1];
                }
            }else{
                s1 = what2.get(0)[2];
                o1 = what2.get(0)[4];
                sequence = "" ;
                for (int i = 0 ; i < what1.size() ; i++){
                    sequence = sequence + " " + what1.get(i)[1];
                }
            }

            if (rel.similar(s1 + " " + o1,sequence)){
                if(sameHow(how1,how2)){
                    relation = "similar";
                }else {
                    relation = "alternative solution" ;
                }
            }else{
                if(sameHow(how1,how2)){
                    relation = "alternative solution";
                }else {
                    relation = "disjoint";
                }
            }

        } else if (what1.size() == 1 && what1.get(0).length == 5 &&  what1.get(0)[0]=="triple"  &&
                what2.size() == 1 && what2.get(0).length == 5 &&  what1.get(0)[0]=="triple" ) {
            System.out.println("rule 3");

            String s1 = what1.get(0)[2];
            String r1 = what1.get(0)[3];
            String o1 = what1.get(0)[4];
            String s2 = what2.get(0)[2];
            String r2 = what2.get(0)[3];
            String o2 = what2.get(0)[4];
            String Srelation  = "" , Orelation = "" , Vrelation = "" ;
            if(s1.equals(s2) || rel.IsSynonymNoun(s1,s2)) {
                Srelation = "SRS";
            }else if ( rel.subclass(s1,s2) || rel.superclass(s1,s2)||rel.similar(s1,s2)){
                Srelation = "RS" ;
            }else{
                Srelation = "SD" ;
            }
            if (rel.IsSynonymVerb(r1,r2) || rel.similar(r1,r2)){
                Vrelation = "VR" ;
            }else{
                Vrelation = "VD" ;
            }
            if(o1.equals(o2)){
                Orelation = "ORS" ;
            }else if (rel.subclass(o1,o2) || rel.superclass(o1,o2)||rel.similar(o1,o2)){
                Orelation = "OR" ;
            }else{
                Orelation = "OD" ;
            }
            System.out.println(Srelation + " " + Vrelation +" " + Orelation );

            //(1) SRS VR ORS: means if we have same subjects (SRS), same verbs (VR), same objects (ORS)
            if(Srelation=="SRS" && Vrelation=="VR" && Orelation=="ORS"){
                if(sameHow(how1,how2)){
                    relation = "duplicate";
                }else {
                    relation = "alternative solution" ;
                }
            }
            //(2) (RS or SRS) VR OR: means if we have same (SRS) or related subjects (RS), same verbs (VR), related objects (ORS)
            else if((Srelation=="RS" || Srelation=="SRS") && Vrelation=="VR" && Orelation=="OR"){
                if(sameHow(how1,how2)){
                    relation = "generalize/specialize";
                }else {
                    relation = "alternative solution" ;
                }
            }
            //(3) (RS or SRS) VR OD: means if we have same (SRS) or related subjects (RS), same verbs (VR), different objects (OD)
            else if((Srelation=="RS" || Srelation=="SRS") && Vrelation=="VR" && Orelation=="OD"){
                if(sameHow(how1,how2)){
                    relation = "alternative solution";
                }else {
                    relation = "disjoint" ;
                }
            }
            //(4) (RS or SRS) VD (OR or ORS)
            else if((Srelation=="RS" || Srelation=="SRS") && Vrelation=="VD" && (Orelation=="OR" || Orelation=="ORS")){
                if(sameHow(how1,how2)){
                    relation = "alternative solution";
                }else {
                    relation = "disjoint" ;
                }
            }
            //(5) (RS or SRS) VD OD
            else if((Srelation=="RS"||Srelation=="SRS") && Vrelation=="VD" && Orelation=="OD"){
                relation = "disjoint";
            }
            //(6) SD VR (OR or ORS)
            else if(Srelation=="SD" && Vrelation=="VR" &&(Orelation=="OR" || Orelation=="ORS")){




                if(sameHow(how1,how2)){
                    relation = "alternative solution";
                }else {
                    relation = "disjoint" ;
                }
            }
            //(7) SD VR OD
            else if(Srelation=="SD" && Vrelation=="VR" && Orelation=="OD"){
                if(sameHow(how1,how2)){
                    relation = "alternative solution";
                }else {
                    relation = "disjoint" ;
                }
            }
            //(8) SD VD OD
            else if(Srelation=="SD" && Vrelation=="VD" && Orelation=="OD"){
                if(sameHow(how1,how2)){
                    relation = "alternative solution";
                }else {
                    relation = "disjoint" ;
                }
            }
            //(9) SD VD (OR or ORS)
            else if(Srelation=="SD" && Vrelation=="VD" && (Orelation=="OR" || Orelation=="ORS")){
                if(sameHow(how1,how2)){
                    relation = "alternative solution";
                }else {
                    relation = "disjoint" ;
                }
            }
        } else if (what1.size() == 1 && what1.get(0).length == 2 && what1.get(0)[0]=="one word" &&
                what2.size() == 1 && what2.get(0).length == 2 && what2.get(0)[0]=="one word" ) {
            String w1 = what1.get(0)[1];
            String w2 = what2.get(0)[1];
            System.out.println("rule 4: one word w1 = " + w1 + "\n one word w2 = " + w2 );
            //– if we identify that ((W 1)sameTo(W 2))
            if(w1.equals(w2) || rel.IsSynonymNoun(w1,w2)){
                if(sameHow(how1,how2)){
                    relation = "duplicate";
                }else {
                    relation = "alternative solution" ;
                }
            }
            else if(rel.superclass(w1,w2)||rel.subclass(w1,w2)){
                if(sameHow(how1,how2)){
                    relation = "generalizes";
                }else {
                    relation = "alternative solution" ;
                }
            }
            //– if we identify that ((W 1)similarTo(W 2))
            else if(rel.similar(w1,w2)){
                if(sameHow(how1,how2)){
                    relation = "alternative solution";
                }else {
                    relation = "disjoint" ;
                }
            }
            else{
                if(sameHow(how1,how2)){
                    relation = "alternative solution";
                }else {
                    relation = "disjoint" ;
                }
            }
        } else if( (what1.size() == 1 && what1.get(0).length == 2 && what1.get(0)[0]=="one word" &&
                (what2.size() > 1 || (what2.size() == 1 && what2.get(0).length == 2 && what2.get(0)[0].equals("sentence"))))
                ||
                (what2.size() == 1 && what2.get(0).length == 2 && what2.get(0)[0]=="one word" &&
                        (what1.size() > 1 || (what1.size() == 1 && what1.get(0).length == 2 && what1.get(0)[0].equals("sentence"))))
        ) {
            String w1 = "" , sequence = "" ;
            if(what1.get(0)[0]=="one word"){
                w1 = what1.get(0)[1];
                for (int i = 0 ; i < what2.size() ; i++){
                    sequence = sequence + " " + what2.get(i)[1];
                }
            }else{
                w1 = what2.get(0)[1];
                for (int i = 0 ; i < what1.size() ; i++){
                    sequence = sequence + " " + what1.get(i)[1];
                }
            }
            System.out.println("rule 5 : one word + sequence" );

            if (rel.similar(w1,sequence)){
                System.out.println("similar what");
                if(sameHow(how1,how2)){
                    relation = "similar";
                }else {
                    relation = "disjoint" ;
                }
            }else{
                System.out.println("disjoint what");
                if(sameHow(how1,how2)){
                    relation = "alternative solution";
                }else {
                    relation = "disjoint";
                }
            }
        } else if ((what1.size() > 1 || (what1.size() == 1 && what1.get(0).length == 2 && what1.get(0)[0].equals("sentence")))
                &&
                (what2.size() > 1 || (what2.size() == 1 && what2.get(0).length == 2 && what2.get(0)[0].equals("sentence")))) {
            System.out.println("rule 6");
            String sequence2 = "";
            String sequence1 = "" ;
            for (int i = 0 ; i < what1.size() ; i++){
                sequence1 = sequence1 + " " + what1.get(i)[1];
            }
            for (int i = 0 ; i < what2.size() ; i++){
                sequence2 = sequence2 + " " + what2.get(i)[1];
            }
            if (rel.similar(sequence1,sequence2)){
                if(sameHow(how1,how2)){
                    relation = "similar";
                }else {
                    relation = "disjoint" ;
                }
            }else{
                if(sameHow(how1,how2)){
                    relation = "alternative solution";
                }else {
                    relation = "disjoint";
                }
            }
        }
        return relation;
    }

    public  boolean sameHow(ArrayList<String[]> how1, ArrayList<String[]> how2) throws IOException, CorruptConfigFileException {
        boolean same = true;
        //If the part of (how) of idea1 of <subject S1, predicate R1, object O1> and the part of (how) of idea2 is composed of <subject S2, predicate R2, object O2>
        if (how1.size() == 1 && how1.get(0).length == 5 && how1.get(0)[0]=="triple" && how2.size() == 1
                && how2.get(0).length == 5 &&  how2.get(0)[0]=="triple") {
            String s1 = how1.get(0)[2];
            String r1 = how1.get(0)[3];
            String o1 = how1.get(0)[4];
            String s2 = how2.get(0)[2];
            String r2 = how2.get(0)[3];
            String o2 = how2.get(0)[4];
            System.out.println("sameHow " +
                    ": both triples: s1,r1,o1 = " + s1 + " ,,, " + r1 + " ,,, " + o1
                    + "\n s2,r2,o2 = " + s2 + " ,,, " + r2 + " ,,, " + o2);

            String Srelation  = "" , Orelation = "" , Vrelation = "" ;
            //If ((s1)sameAs(s2) ∨ (s1)similarTo(s2) ∨ (s1)subclass(s2)∨ (s1)superclass(s2)) Then == SR Else == SD
            if (s1.equals(s2)|| rel.subclass(s1,s2) || rel.superclass(s1,s2) || rel.similar(s1,s2) ){
                Srelation = "RS" ;
            }else{
                Srelation = "SD" ;
            }
            //If (r1) synonymTo or similarTo (r2) Then == VR Else == VD
            if (rel.similar(r1,r2)|| rel.IsSynonymVerb(r1,r2)){
                Vrelation = "VR" ;
            }else{
                Vrelation = "VD" ;
            }
            //If ((o1)sameAs(o2) ∨ (o1)similarTo(o2) ∨ (o1) subclass(o2) ∨ (o1)superclass (o2)) Then == OR Else == OD
            if (o1.equals(o2) || rel.subclass(o1,o2) || rel.superclass(o1,o2)|| rel.similar(o1,o2) ){
                Orelation = "OR" ;
            }else{
                Orelation = "OD" ;
            }
            System.out.println(Srelation + " " + Vrelation + " " + Orelation );
            //(1) RS VR OR then it same how
            if(Srelation.equals("RS") && Vrelation.equals("VR") && Orelation.equals("OR")){
                same = true ;
            }
            //(2) RS VR OD then it different how
            else if(Srelation.equals("RS") && Vrelation.equals("VR") && Orelation.equals("OD")){
                same = false ;
            }
            //(3) RS VD OR then it same how
            else if(Srelation.equals("RS") && Vrelation.equals("VD") && Orelation.equals("OR")){
                same = true ;
            }
            //(4) RS VD OD then it different how
            else if(Srelation.equals("RS") && Vrelation.equals("VD") && Orelation.equals("OD")){
                same = false ;
            }
            //(5) SD VR OR then it same how
            else if(Srelation.equals("SD") && Vrelation.equals("VR") && Orelation.equals("OR")){
                same = true ;
            }
            //(6) SD VR OD then it different how
            else if(Srelation.equals("SD") && Vrelation.equals("VR") && Orelation.equals("OD")){
                same = false ;
            }
            //(7) SD VD OD then it different how
            else if(Srelation.equals("SD") && Vrelation.equals("VD") && Orelation.equals("OD")){
                same = false ;
            }
            //(8) SD VD OR then it different how
            else if(Srelation.equals("SD") && Vrelation.equals("VD") && Orelation.equals("OR")){
                same = false ;
            }
        }
        //If the part of (how) of idea1 is composed of <subject S1, predicate R1, object O1> and the part of (how) of idea2 is composed only of one word <W1>
        if ( (how1.size() == 1 && how1.get(0).length == 5 && how1.get(0)[0]=="triple" && how2.size() == 1 && how2.get(0).length == 2 && how2.get(0)[0].equals("one word"))
                || (how2.size() == 1 && how2.get(0).length == 5 && how2.get(0)[0]=="triple" && how1.size() == 1 && how1.get(0).length == 2 && how1.get(0)[0].equals("one word"))
        ){
            System.out.println("sameHow : sequence + one word");

            String s1="", o1="", w1 = "" ;
            if(how2.get(0)[0].equals("one word")){
                s1 = how1.get(0)[2];
                o1 = how1.get(0)[4];
                w1 = how2.get(0)[1];

            }else if(how1.get(0)[0].equals("one word")){
                s1 = how2.get(0)[2];
                o1 = how2.get(0)[4];
                w1 = how1.get(0)[1];
            }

            if(rel.similar(s1,w1) || rel.similar(o1,w1)){
                same = true ;
            }else {
                same = false ;
            }
        }
        //If the part of (how) of idea1 is composed of <subject S1, predicate R1, object O1> and the part of (how) of idea2 is composed of sequence of words <SW1>
        if ( (how1.size() == 1 && how1.get(0).length == 5 && how1.get(0)[0]=="triple" &&
                (how2.size() > 1 || (how2.size() == 1 && how2.get(0).length == 2 && how2.get(0)[0].equals("sentence"))))
                ||
                (how2.size() == 1 && how2.get(0).length == 5 && how2.get(0)[0]=="triple" &&
                        (how1.size() > 1 || (how1.size() == 1 && how1.get(0).length == 2 && how1.get(0)[0].equals("sentence"))))
        ){
            System.out.println("sameHow : sequence and triple");

            String s1 = "" , r1 = "" , o1 = "" , sequence = "" ;

            if(how1.size() == 1 && how1.get(0).length == 5){
                s1 = how1.get(0)[2];
                r1 = how1.get(0)[3];
                o1 = how1.get(0)[4];
                System.out.println("triple : " + s1+" "+r1+ " "+o1);
                for (int i = 0 ; i < how2.size() ; i++){
                    sequence = sequence + " " + how2.get(i)[1];
                }
                System.out.println("sequence :  "+ sequence);
            }else if(how2.size() == 1 && how2.get(0).length == 5){
                s1 = how2.get(0)[2];
                r1 = how2.get(0)[3];
                o1 = how2.get(0)[4];
                System.out.println("triple : " + s1+" "+r1+ " "+o1);
                for (int i = 0 ; i < how1.size() ; i++){
                    sequence = sequence + " " + how1.get(i)[1];
                }
                System.out.println("sequence :  "+ sequence);
            }

            if(rel.similar(s1+" "+r1+ " "+o1,sequence)){
                same = true;
            }else {
                same = false;
            }
        }
        //If the part of (how) of idea1 is composed of <only word W1> and the part of (how) of idea2 is composed only one word w2
        if (how1.size() == 1 && how1.get(0).length == 2 && how1.get(0)[0].equals("one word") && how2.size() == 1 && how2.get(0).length == 2 && how2.get(0)[0].equals("one word")) {
            System.out.println("sameHow: both  word ");

            String w1 = how1.get(0)[1];
            String w2 = how2.get(0)[1];
            System.out.println(w1+"-----------"+w2);
            if(w1.equals(w2)||rel.subclass(w1,w2)||rel.superclass(w1,w2)||rel.similar(w1,w2)){
                same = true ;
            }else {
                same = false ;
            }
        }
        //If the part of (how) of idea1 is composed of <only word W1> and the part of (how) of idea2 is composed of sequence of words <SW2>
        if( (how1.size() == 1 && how1.get(0).length == 2 && how1.get(0)[0].equals("one word") &&
                (how2.size() > 1 || (how2.size() == 1 && how2.get(0).length == 2 && how2.get(0)[0].equals("sentence"))))
                ||
                (how2.size() == 1 && how2.get(0).length == 2 && how2.get(0)[0].equals("one word") &&
                        (how1.size() > 1 || (how1.size() == 1 && how1.get(0).length == 2 && how1.get(0)[0].equals("sentence"))))
        )
        {
            String w1 = "";
            String sequence = "" ;
            System.out.println("one and sequence : samehow");

            if(how1.get(0)[0].equals("one word")){
                w1 = how1.get(0)[1];
                for (int i = 0 ; i < how2.size() ; i++){
                    sequence = sequence + " " + how2.get(i)[1];
                }
            }else if (how2.get(0)[0].equals("one word")){
                w1 = how2.get(0)[1];
                for (int i = 0 ; i < how1.size() ; i++){
                    sequence = sequence + " " + how1.get(i)[1];
                }
            }

            if(rel.similar(w1,sequence)){
                same = true;
            }else {
                same = false;
            }
        }
        //If the part of (how) of idea1 of <sequence of words SW1> and the part of (how) of idea2 is composed of sequence of words <SW2>
        if ((how1.size() > 1  || (how1.size() == 1 && how1.get(0).length == 2 && how1.get(0)[0].equals("sentence"))) &&
                (how2.size() > 1 || (how2.size() == 1 && how2.get(0).length == 2 && how2.get(0)[0].equals("sentence")))) {
            System.out.println("both are sequence of words");
            String sequence1 ="";
            String sequence2="";
            System.out.println("sequences of words for both : samehow");
            for (int i = 0 ; i < how1.size() ; i++){
                sequence1 = sequence1 + " " + how1.get(i)[1];
                System.out.println(sequence1);
            }
            for (int i = 0 ; i < how2.size() ; i++){
                sequence2 = sequence2 + " " + how2.get(i)[1];
                System.out.println(sequence2);
            }
            if(rel.similar(sequence1,sequence2)){
                same = true ;
            }else {
                same =false ;
            }
        }
        return same;
    }



}
