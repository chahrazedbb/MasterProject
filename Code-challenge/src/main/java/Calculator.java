import java.util.ArrayList;

public class Calculator {

    int  nbRelations;
    Double trueP = 0.0, FalseN = 0.0 , FalseP = 0.0 ;

    public Calculator(ArrayList<String> Rresults){

        nbRelations = Rresults.size() ;

        for (int i = 0 ; i < Rresults.size() ; i++){
            String relation = Rresults.get(i).split(";;;")[2];
            String validation = Rresults.get(i).split(";;;")[3];
            if(relation.equals("disjoint") && validation.equals("False")){
                FalseN++;
            }else if (!relation.equals("disjoint") && validation.equals("False")){
                FalseP++;
            }else if (!relation.equals("disjoint") && validation.equals("True")){
                trueP++;
            }
        }

    }
    public Double Precision(){
        Double result = 0.0;
        result = trueP/(trueP+FalseP);
        System.out.println("precision "+result);
        return result ;
    }
    public Double Recall(){
        Double result = 0.0;
        result = trueP/(trueP+FalseN);
        System.out.println("recall "+result);
        return result ;
    }
    public Double fmeasure(){
        Double result = 0.0;
        result = 2*((Precision()*Recall())/(Precision()+Recall()));
        System.out.println("fmeasure "+result);
        return result ;
    }
}
