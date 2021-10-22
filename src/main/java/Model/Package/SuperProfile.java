package Model.Package;

import java.util.LinkedList;
import java.util.List;

public class SuperProfile {

    private String name;
    private int wins;
    private int losses;
    private List<Career> careers;

    public SuperProfile(String name){
        this.name = name;
        wins = 0;
        losses = 0;
        careers = new LinkedList<>();
    }

    public void startCareer(Career career){
        careers.add(career);
    }

    public void win(){
        wins++;
    }

    public void lose(){
        losses++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWins(){
        return wins;
    }

    public int getLosses(){
        return losses;
    }

    public List<Career> getCareers(){
        return careers;
    }
}
