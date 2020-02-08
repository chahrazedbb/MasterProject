import java.io.*;

public class dandelion {

    public String convert(InputStream in) throws IOException {

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = in.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        // StandardCharsets.UTF_8.name() > JDK 7
        return result.toString("UTF-8");
    }

    public double sim(String s1, String s2) throws IOException {
        String text1 = s1.replaceAll(" ","%20");
        String text2 = s2.replaceAll(" ","%20");
        String token = //"7c47511298ff4e84bc1aefc962801bda";
                //"f3e343b7370943dc94646e0bf82794ed";
                //"32c16ec0abee4260b7d58b95003f8788";
                //"965b44cb89be46d0a2799444f47f695b";
                //"bcbed55687f448b8bd1dfca8ce0981e9" ;
                "70591370606f48a69b7256dce5e7f64c";
       // System.out.println(text1);
        String command = "curl -X POST https://api.dandelion.eu/datatxt/sim/v1/?text1=" + text1+ "&text2="+text2 +"&lang=en&token="+token;
        Process process = Runtime.getRuntime().exec(command);
        String[] d = convert(process.getInputStream()).split(",") ;
        String[] s = d[1].split(":");
        System.out.println(text1 + " " + text2 + "this is s : " + Double.parseDouble(s[1]));
        process.destroy();
        return Double.parseDouble(s[1]);


    }
}
