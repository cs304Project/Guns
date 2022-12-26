
package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author youse
 */
public class ScoreBoard {
    private String path = "./scores.json";
    private JSONObject json;
    public void addScore(String name,int score) throws IOException, FileNotFoundException, ParseException{
        json = new JSONObject();
        try{
            json.put("name", name);
            json.put("score", score);
        }catch(Exception err){
            err.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(new FileWriter(path,true))) {
            out.write(json.toString()+"\n");
            out.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
        sortScores();
    }
    public void sortScores() throws IOException, FileNotFoundException, ParseException{
        ArrayList scores = getScores();
        Collections.sort(scores,new Comparator<Score>(){
            @Override
            public int compare(Score o1, Score o2) {
                return (int)(o1.score - o2.score);
            }
        });
        PrintWriter out = new PrintWriter(new FileWriter(path));
        for(int i=scores.size()-1;(i>=0 && i>=scores.size()-10);i--){
            json = new JSONObject();
            long score = ((Score)scores.get(i)).score;
            String name = ((Score)scores.get(i)).name;
            try{
                json.put("score", score);
                json.put("name", name);
                out.append(json.toString()+"\n");
            }catch(Exception err){
                err.printStackTrace();
            }
        }
        out.close();
    }
    
    ArrayList<Score> getScores() throws FileNotFoundException, IOException, ParseException{
        FileReader in = new FileReader(path);
        BufferedReader  br = new BufferedReader(in);
        ArrayList scores = new ArrayList<Score>();
        String str;

        while((str = br.readLine()) != null){
            json = (JSONObject) new JSONParser().parse(str);
            Score s = new Score();
            s.score = (long) json.get("score");
            s.name = (String) json.get("name");
            scores.add(s);
        }
        in.close();
        return scores;
    }
}

class Score{
    String name;
    long score;
    Score(){
        this.name="";
        this.score=0;
    }
    Score(String name,long score){
        this.name = name;
        this.score = score;
    }
    @Override
    public String toString() {
        return "{name is: "+name+" ,score is: "+score+"}";
    }
    
}