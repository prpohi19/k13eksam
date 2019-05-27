import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.FileWriter;
import java.io.IOException;

public class MotivationGame {
    private Integer motivation;

    public MotivationGame(Integer motivation){
        this.motivation = motivation;
    }

    public Integer getMotivation() {
        return this.motivation;
    }

    public void setMotivation(Integer motivation) {
        this.motivation += motivation;
    }


    public ArrayList<Question> readFile(){
        ArrayList<Question> questions = new ArrayList<>();
        Long motivation;
        JSONParser parser = new JSONParser();
        try {

            Object obj = parser.parse(new FileReader(
                    "file3.json"));

            JSONObject jsonObject = (JSONObject) obj;
            JSONArray gameQuestions = (JSONArray) jsonObject.get("Questions");
            for(Integer i = 0; i < gameQuestions.size(); i++){
                ArrayList<String> answerList = new ArrayList<>();
                ArrayList<Integer> motivationList = new ArrayList<>();
                ArrayList<Integer> count = new ArrayList<>();
                JSONObject gameQuestion1 = (JSONObject) gameQuestions.get(i);
                String question = (String) gameQuestion1.get("Question");
                JSONArray motivations = (JSONArray) gameQuestion1.get("Motivations");
                JSONArray answers = (JSONArray) gameQuestion1.get("Answers");
                JSONArray counts = (JSONArray) gameQuestion1.get("Count");
                Iterator<String> iterator = answers.iterator();
                Iterator<Long> iterator2 = motivations.iterator();
                Iterator<Long> iterator3 = counts.iterator();
                while (iterator.hasNext() && iterator2.hasNext()) {
                    answerList.add(iterator.next());
                    motivation = iterator2.next();
                    motivationList.add(Math.toIntExact(motivation));
                    count.add(Math.toIntExact(iterator3.next()));
                }
                questions.add(new Question(question, answerList, motivationList, count));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return questions;
    }
    public void writeFile(ArrayList<Question> questions) throws IOException{
        JSONObject obj = new JSONObject();
        JSONArray question = new JSONArray();
        for (Question quest: questions
             ) {
            JSONArray answers = new JSONArray();
            JSONArray count = new JSONArray();
            JSONArray motivation = new JSONArray();
            JSONObject obj2 = new JSONObject();
            for(Integer i = 0; i<4; i++){
                answers.add(quest.getAnswer(i));
                count.add(quest.getCount(i));
                motivation.add(quest.getMotivation(i));
            }
            obj2.put("Question", quest.getQuestion());
            obj2.put("Answers", answers);
            obj2.put("Motivations", motivation);
            obj2.put("Count", count);
            question.add(obj2);
            System.out.println(question);

        }
        obj.put("Questions", question);

        // try-with-resources statement based on post comment below :)
        try (FileWriter file = new FileWriter("file3.json")) {
            file.write(obj.toJSONString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + obj);
        }
    }

}

