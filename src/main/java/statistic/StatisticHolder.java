package statistic;


import java.util.HashMap;
import java.util.Map;

public class StatisticHolder {
    public static final StatisticHolder INSTANCE = new StatisticHolder();

    private long requests = 0;
    private long activeConnections = 0;
    private Map<String, Integer> redirections = new HashMap<String,Integer>();



    private StatisticHolder(){}


    public void addRequest(){
        requests++;
    }

    public void addActiveConnection(){
        activeConnections++;
    }

    public void removeActiveConnection(){
        activeConnections--;
    }

    public long getRequestsCounter(){
        return requests;
    }

    public long getActiveConnectionsCounter(){
        return activeConnections;
    }

    public void addRedirection(String url) {
        if(redirections.containsKey(url)) {
            redirections.put(url, redirections.get(url)+1);
        } else {
            redirections.put(url, 1);
        }
    }

    public Map<String,Integer> getRedirectionsMap(){
        return redirections;
    }


}
