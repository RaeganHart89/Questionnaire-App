package questionapp.rharttech.dev.com.questionapp;

public class QuestionObject {
    //Variables
    private String question;
    private boolean answer;
    private int picture;

   // Set return values for entire object
   public QuestionObject(String question, boolean answer, int picture){
       this.question = question;
       this.answer = answer;
       this.picture = picture;
   }
    // Return objects for individual values
    public String getQuestion() {
        return question;
    }

    public boolean isAnswer() {
        return answer;
    }

    public int getPicture(){
        return picture;
    }
}
