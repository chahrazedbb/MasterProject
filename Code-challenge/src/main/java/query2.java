/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFactory;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.ResourceFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import uk.ac.shef.wit.simmetrics.similaritymetrics.AbstractStringMetric;
import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;


public class query2 {

    private static String ress;

   public static String QueryDBpedia(String resource) throws IOException {
        System.out.println(resource);
        if(resource.contains("https://www.wikidata.org/wiki/")){

            String linkText="";

            Document document = Jsoup.connect(resource).timeout(5000).get();
            Elements link3 = document.select("title");
            linkText = link3.text();
            linkText = linkText.substring(0, linkText.length()-11);

            return linkText;
        }
        else
        {
            String finalres= resource;
            ParameterizedSparqlString qs = new ParameterizedSparqlString(
                    "prefix rdfs:    <http://www.w3.org/2000/01/rdf-schema#>\n" +

                            "select ?resource where {\n" +
                            "  ?resource rdfs:label ?label\n" +
                            "}" );



            Literal entity = ResourceFactory.createLangLiteral( (resource.substring(0,1).toUpperCase() + resource.substring(1).toLowerCase()), "en" );
            qs.setParam( "label", entity );

              System.out.println( "                  blaaaaaaaaaaaaaaaaaaaaaaa"+qs );

            QueryExecution exec = QueryExecutionFactory.sparqlService( "http://dbpedia.org/sparql", qs.asQuery() );

            ResultSet results = ResultSetFactory.copyResults( exec.execSelect() );

            while ( results.hasNext() ) {

                RDFNode res=results.next().get( "resource" );
                if(res != null) {
                       System.out.println(res.toString() + " ----------------");

                    resource = resource.replaceAll(" ", "_").toLowerCase() ;


                    if (res.toString().contains("dbpedia.org/resource/"+(resource.substring(0,1).toUpperCase() + resource.substring(1).toLowerCase())))
                    {
                        /////////////////////////////////////////////////////////////////////////////////////////////////
                        ress= resource;

                        qs = new ParameterizedSparqlString(
                                "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +

                                        "select ?value { \n" +
                                        "<"+res.toString()+"> dbo:abstract ?value\n" +
                                        "}"
                        );

                              System.out.println( qs );
                        exec = QueryExecutionFactory.sparqlService( "http://dbpedia.org/sparql", qs.asQuery() );

                        results = ResultSetFactory.copyResults( exec.execSelect() );
                        if(results.hasNext()==true) {

                            finalres=QueryDBpediaWikidata(res.toString()) ;
                        }



                    }
                    //if (res.toString().contains("www.wikidata.org")){  }
                }
            }
            System.out.println("this is the fffffffff   " + finalres);
            return finalres;
        }
//http://dbpedia.org/page/Screen

    }

    ///////////////////////////////////////
    public static boolean checkSub(String resource1, String resource2) {

        boolean check = false ;

        String res1 = resource1.toLowerCase();
        String res2 = resource2.toLowerCase() ;

        ParameterizedSparqlString qs = new ParameterizedSparqlString(
                "PREFIX dbpedia: <http://dbpedia.org/resource/>\n" +
                        "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                        "PREFIX wdt: <http://www.wikidata.org/prop/direct/>\n" +
                        "PREFIX wd: <http://www.wikidata.org/entity/>\n" +
                        "prefix rdfs:    <http://www.w3.org/2000/01/rdf-schema#>\n" +

                        "SELECT ?item2 WHERE {\n" +
                        "?item rdfs:label ?item2. \n" +
                        " ?item wdt:P279* ?item1. \n" +
                        "?item1 rdfs:label \""+resource1+"\"@en. \n" +
                        "FILTER (?item2 = \""+resource2+"\"@en). \n" +
                        "}" );


         System.out.println( qs );

        QueryExecution  exec = QueryExecutionFactory.sparqlService( "https://query.wikidata.org/sparql", qs.asQuery() );

        ResultSet     results = ResultSetFactory.copyResults( exec.execSelect() );
        //   System.out.println( results.hasNext());
     /*   while ( results.hasNext() ) {
            QuerySolution res= results.next();
            System.out.println("checkSub " +  res.get( "item2" ).asLiteral());
        }*/
        if(results.hasNext()){
            check = true ;
        }

        //      SELECT ?item2 WHERE {
//?item rdfs:label ?item2.
//item wdt:P279 ?item1.
//?item1 rdfs:label "window"@en.
//FILTER (?item2 = "architectural element"@en).}
        return check ;
    }
    ///////////////////////////////////////////////////////////////////////
    public static boolean checkSuper(String resource1, String resource2) {

        boolean check = false;

        ParameterizedSparqlString qs = new ParameterizedSparqlString(
                "PREFIX dbpedia: <http://dbpedia.org/resource/>\n" +
                        "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                        "PREFIX wdt: <http://www.wikidata.org/prop/direct/>\n" +
                        "PREFIX wd: <http://www.wikidata.org/entity/>\n" +
                        "prefix rdfs:    <http://www.w3.org/2000/01/rdf-schema#>\n" +

                        "SELECT ?item2 WHERE {\n" +
                        "?item rdfs:label ?item2. \n" +
                        " ?item wdt:P279* ?item1. \n" +
                        "?item1 rdfs:label \""+resource2.toLowerCase()+"\"@en. \n" +
                        "FILTER (?item2 = \""+resource1.toLowerCase()+"\"@en). \n" +
                        "}" );


           System.out.println( qs );

        QueryExecution  exec = QueryExecutionFactory.sparqlService( "https://query.wikidata.org/sparql", qs.asQuery() );

        ResultSet     results = ResultSetFactory.copyResults( exec.execSelect() );
        //  System.out.println( results.hasNext());
       /* while ( results.hasNext() ) {
            QuerySolution res= results.next();
            System.out.println("checkSuper :" +  res.get( "item2" ).asLiteral());
        }*/
        if ( results.hasNext() ) {
            check = true;
        }

        //      SELECT ?item2 WHERE {
        //?item rdfs:label ?item2.
        //item wdt:P279 ?item1.
        //?item1 rdfs:label "window"@en.
        //FILTER (?item2 = "architectural element"@en).}
        return  check ;
    }
    ///////////////////////////////////////////////////////////////////////
    public static String QueryDBpediaWikidata(String resource) {
        String finalres="";
        ParameterizedSparqlString qs = new ParameterizedSparqlString(
                "PREFIX dbpedia: <http://dbpedia.org/resource/>\n" +
                        "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                        "prefix rdfs:    <http://www.w3.org/2000/01/rdf-schema#>\n" +
                        "SELECT ?obj ?label WHERE  {\n" +

                        "?obj rdfs:label ?label. \n" +
                        "<"+resource+"> (owl:sameAs|^owl:sameAs) ?obj \n" +

                        "FILTER (langMatches( lang(?label), \"en\" ) ) \n" +
                        " FILTER(CONTAINS(str(?obj), \"http://www.wikidata.org\")) \n" +
                        "}" );


        //  System.out.println( qs );

        QueryExecution exec = QueryExecutionFactory.sparqlService( "http://dbpedia.org/sparql", qs.asQuery() );

        ResultSet results = ResultSetFactory.copyResults( exec.execSelect() );

        while ( results.hasNext() ) {

            QuerySolution res= results.next();
            //    System.out.println( res.get( "obj" ).asResource().getLocalName());
            //     System.out.println("wiiiiwwiiiiwwwiii "+ res.get( "label" ));
            finalres=res.get( "label" ).asLiteral().getString();
            //GetDBpediaH(res.get( "obj" ).asResource().getLocalName().toString());
        }
        return finalres;
    }

    ///////////////////////////////////////////////////////////////////////
    public static String BrowsWord(String mot) throws IOException {
        String linkText="";
        try {
            int i = 0;
            String google = "http://www.google.com/search?q=";
            String search = mot + " wikidata";
            String charset = "UTF-8";
            String userAgent = "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)"; // Change this to your company's name and bot homepage!
            System.out.println(google + URLEncoder.encode(search, charset));
            Elements links;

            links = Jsoup.connect(google + URLEncoder.encode(search, charset)).userAgent(userAgent).timeout(5000).ignoreHttpErrors(true).get().select("a[href]");
            System.out.println(google + URLEncoder.encode(search, charset));

            for (Element link : links) {
                System.out.println("///////////////////////////////////////this is link.text : " + link.text());

                String temp = link.attr("href");
                if (temp.startsWith("/url?q=")) {
                    //use regex to get domain name
                    System.out.println(temp);

                    System.out.println("///////////////////////////////////////this is link.text : " + link.text());
                    String title = link.text();
                    String url = link.absUrl("href"); // Google returns URLs in format "http://www.google.com/url?q=<url>&sa=U&ei=<someKey>".
                    url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");
                    if (!url.startsWith("http")) {
                        continue; // Ads/news/etc.
                    }
                    // System.out.println("URL: " + url);
                    if (url.contains("https://www.wikidata.org/wiki/")) {
                        Document document = Jsoup.connect(url).timeout(5000).get();
                        Elements link3 = document.select("title");
                        linkText = link3.text();

                        System.out.println("the linkText  : " + linkText);

                    }
                else {
                        linkText = mot ;
                    }
                }

            }

        }catch (UnsupportedEncodingException ex) {
            Logger.getLogger(query2.class.getName()).log(Level.SEVERE, null, ex);
        }


        if(linkText.contains("Category:")){
            linkText = linkText.replace("Category:","");

            int i=0;
            String google = "http://www.google.com/search?q=";
            String search = linkText+" wikidata";
            String charset = "UTF-8";
            String userAgent = "Firefox"; // Change this to your company's name and bot homepage!

            Elements links;

            links = Jsoup.connect(google + URLEncoder.encode(search, charset)).userAgent(userAgent).timeout(5000).ignoreHttpErrors(true).get().select(".g>.r>a");

            for (Element link : links) {
                System.out.println(link.text());
                if(i<1){
                    String title = link.text();
                    String url = link.absUrl("href"); // Google returns URLs in format "http://www.google.com/url?q=<url>&sa=U&ei=<someKey>".
                    url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");
                    //    System.out.println("URL: " + url);
                    if (!url.startsWith("http")) {
                        continue; // Ads/news/etc.
                    }
                    if(url.contains("https://www.wikidata.org/wiki/")){
                        Document document = Jsoup.connect(url).timeout(5000).get();
                        System.out.println(url);
                        /*Elements link3 = document.select("title");
                        linkText = link3.text();*/
                        System.out.println("**********************the link **********"+linkText.substring(0, linkText.length()-11));
                        linkText = url;
                        i=1;
                    }

                }

            }
        }
       /* if(linkText.endsWith("s")){
            linkText = linkText.substring(0, linkText.length() - 1);
            System.out.println("**********************the link **********"+linkText);
        }*/
        System.out.println("**********************the link **********"+linkText);
        return (linkText);
    }
    ///////////////////////////////////////////////////////////////////////
    public static void GetDBpediaH(String resource) {

        ParameterizedSparqlString qs = new ParameterizedSparqlString(
                "PREFIX wdt: <http://www.wikidata.org/prop/direct/>\n" +
                        "PREFIX wd: <http://www.wikidata.org/entity/>\n" +
                        "prefix rdfs:    <http://www.w3.org/2000/01/rdf-schema#>\n" +

                        "select distinct ?property ?value{\n" +
                        " wd:"+resource+"  ?property ?value.  \n" +
                        "} \n" +
                        "ORDER BY DESC(?property)" );

          System.out.println( qs );

        QueryExecution exec = QueryExecutionFactory.sparqlService( "https://query.wikidata.org/sparql", qs.asQuery() );

        ResultSet results = ResultSetFactory.copyResults( exec.execSelect() );

        if ( results.hasNext() ) { //remember that you are taking only thé first solution, see this în future
            QuerySolution res=results.next();
            res.get( "property" ).toString();
            if(res.get( "property" ).toString().contains("direct/P279")) {// System.out.println(" QQQQQQQQ ");
                GetDBpediaCon(resource);}
            else {if (res.get( "property" ).toString().contains("P31"))  { //System.out.println(" WWWWWWWWW ");
                GetDBpediaIns(resource); }}
        }

        // ResultSetFormatter.out( results );
    }

    //////////////////////////////////////////////////////////////////////////
    public static void GetDBpediaCon(String resource) {

        ParameterizedSparqlString qs = new ParameterizedSparqlString(
                "PREFIX wdt: <http://www.wikidata.org/prop/direct/>\n" +
                        "PREFIX wd: <http://www.wikidata.org/entity/>\n" +

                        "SELECT DISTINCT ?item   WHERE {\n" +
                        " wd:"+resource+"    wdt:P279 ?item .  \n" +
                        "} \n" +
                        "ORDER BY DESC(?item)");

        // System.out.println( qs );

        QueryExecution exec = QueryExecutionFactory.sparqlService( "https://query.wikidata.org/sparql", qs.asQuery() );

        ResultSet results = ResultSetFactory.copyResults( exec.execSelect() );

        if ( results.hasNext() ) {

            QuerySolution res=results.next();

            if(res.get( "item" ).asResource().getLocalName().toString().equals("Q35120" )) { }
            else { GetDBpediaCon(res.get( "item" ).asResource().getLocalName().toString());}
        }

        //    ResultSetFormatter.out( results );
    }
    //////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////
    public static void GetDBpediaIns(String resource) {

        ParameterizedSparqlString qs = new ParameterizedSparqlString(
                "PREFIX wdt: <http://www.wikidata.org/prop/direct/>\n" +
                        "PREFIX wd: <http://www.wikidata.org/entity/>\n" +
                        "prefix rdfs:    <http://www.w3.org/2000/01/rdf-schema#>\n" +

                        "SELECT DISTINCT ?item   WHERE {\n" +
                        "wd:"+resource+"    wdt:P31 ?item .  \n" +
                        "ORDER BY DESC(?item)" );



        // System.out.println( qs );

        QueryExecution exec = QueryExecutionFactory.sparqlService( "https://query.wikidata.org/sparql", qs.asQuery() );

        ResultSet results = ResultSetFactory.copyResults( exec.execSelect() );

        if ( results.hasNext() ) { //remember that you are taking only thé first solution, see this în future

            QuerySolution res=results.next();

            if(res.get( "item" ).asResource().getLocalName().toString().equals("Q35120" )) { }
            else { GetDBpediaCon(res.get( "item" ).asResource().getLocalName().toString());}
        }

        //    ResultSetFormatter.out( results );
    }

    public  static float Levenshtein(String g1,String g2){
        float res = 0;
        Set<AbstractStringMetric> metrics = new HashSet<AbstractStringMetric>();
        metrics.add( new Levenshtein());
        for (AbstractStringMetric metric : metrics) {
            res = metric.getSimilarity(g1, g2);
        }
        return res;
    }

//////////////////////////////////////////////////////////////////////////
}
