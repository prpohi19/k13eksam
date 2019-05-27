import java.util.ArrayList;
import java.util.Scanner;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

public class Program {

    public static void main(String[] args) throws JSONException, IOException{
        MotivationGame game = new MotivationGame(30);
        ArrayList<Question> questions = game.readFile();
        Scanner myObj = new Scanner(System.in);
        System.out.println("Your motivation is "+game.getMotivation());
        for (Question question: questions) {
            Integer answer = -1;
            System.out.println(question.getQuestion());
            for(Integer i = 0; i<4; i++) {
                System.out.println(question.getAnswer(i));
            }
            System.out.println("Enter your preferenced answer (between 1 and 4)");
            while(answer < 0 || answer > 3) {
                answer = Integer.parseInt(myObj.nextLine()) - 1;
                if(answer < 0 || answer > 3){
                    System.out.println("You must enter a number between 1 and 4");
                }
            }
            question.setCount(answer);
            System.out.println("You chose to "+question.getAnswer(answer)+""+question.getCount(answer));
            game.setMotivation(question.getMotivation(answer));
            System.out.println("Your new motivation is "+game.getMotivation());
        }
        game.writeFile(questions);

    }
}
