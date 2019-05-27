import java.util.ArrayList;

public class Question {
    private String question;
    private ArrayList<String> answers = new ArrayList<>();
    private ArrayList<Integer> motivations = new ArrayList<>();
    private ArrayList<Integer> count = new ArrayList<>();

    public Question(String question, ArrayList<String> answers, ArrayList<Integer> motivations, ArrayList<Integer> count){
        this.question = question;
        this.answers = answers;
        this.motivations = motivations;
        this.count = count;
    }

    public String getQuestion(){
        return this.question;
    }
    public String getAnswer(Integer answer){
        return this.answers.get(answer);
    }
    public ArrayList<String> getAnswers(){return this.answers;}
    public Integer getMotivation(Integer answer){
        return this.motivations.get(answer);
    }
    public Integer getCount(Integer answer){return this.count.get(answer);}
    public void setCount(Integer answer){
        Integer value = this.count.get(answer);
        value ++;
        this.count.set(answer, value);
    }
}
