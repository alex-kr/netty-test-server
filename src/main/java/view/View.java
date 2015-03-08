package view;

import statistic.StatisticHolder;

import java.util.Map;

import static statistic.StatisticHolder.INSTANCE;

public class View {

    public static String getHelloPage(){

        return "<HTML>\n" +
                "   <HEAD>\n" +
                "      <TITLE>\n" +
                "         Hello, Netty! \n" +
                "      </TITLE>\n" +
                "   </HEAD>\n" +
                "<BODY>\n" +
                "   <P> Hello world!</P> \n" +
                "</BODY>\n" +
                "</HTML>";
    }

    public static String getStatisticPage(){
        String result = "<HTML>\n" +
                "   <HEAD>\n" +
                "      <TITLE>\n" +
                "         Status\n" +
                "      </TITLE>\n" +
                "   </HEAD>\n" +
                "<BODY>\n" +
                "   <H1>Status</H1>\n" +
                "   <H3>Connections</H3>\n" +
                "   <P>Requests:"+ INSTANCE.getRequestsCounter() +"</P> \n" +
                "   <P>Active connections:" + INSTANCE.getActiveConnectionsCounter() + " </P>\n" +
                "\n" +
                "   <table border=\"1\">\n" +
                "   <caption>Redirects</caption>\n" +
                "   <tr>\n" +
                "    <th>IP</th>\n" +
                "    <th>Redirects</th>\n" +
                "   </tr>\n";

                for (Map.Entry<String, Integer> entry : StatisticHolder.INSTANCE.getRedirectionsMap().entrySet()) {
                    result += "   <tr><td>"+ entry.getKey() +"</td><td>" + entry.getValue() + "</td></tr>\n";
                }
                result +=   "</table>" +
                            "</BODY>\n" +
                            "</HTML>";
        return result;
    }

}
