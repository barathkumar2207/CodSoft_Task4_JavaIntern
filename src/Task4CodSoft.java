import java.util.*;

class QuizWithTimer{
    private static final Scanner sc = new Scanner(System.in);
    private static Timer timer;
    private static int QuestionId = 0;
    private static int score = 0;
    private static final String [] questions = {
            "Question 1 : Who is called as Father of Computer?",
            "Question 2 : What was JavaScript originally called?",
            "Question 3 : Which languages is called as Mother of Programming Language?",
            "Question 4 : Which Language in the list below doesn't have the concept of OOPs?"
    };

    private static final String [][] Options = {
            {"A) Mark Zuckerberg" ,"B) Steve Jobs ", "C) Charles Babbage", "D) Elon Musk"},
            {"A) LiveScript", "B) Java" , "C) Rust","D) ES6"},
            {"A) C", "B) R", "C) Java", "D) Python"},
            {"A) Rust", "B) Matlab" , "C) C++" , "D) C " }
    };

    public static char [] ans = {'c','a','a','d'};
    public static void main(String[] args) {
        startQuiz();
    }

    public static void startQuiz() {
        System.out.println("Welcome to the Quiz Program!");
        askQuestion();
    }

    public static void askQuestion() {
        if (QuestionId < questions.length) {
            System.out.println(questions[QuestionId]);
            for (String option : Options[QuestionId]) {
                System.out.println(option);
            }

            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Time's up!");
                    goToNextQuestion();
                }
            }, 10000);

            String userAnswer = sc.nextLine().toLowerCase();
            checkAnswer(userAnswer.charAt(0));
        } else {
            displayResult();
            if (timer != null) {
                timer.cancel();
            }
        }
    }
    private static void checkAnswer(char userAnswer) {
        if (userAnswer == ans[QuestionId]) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Incorrect!");
        }
        goToNextQuestion();
    }

    private static void goToNextQuestion() {
        if (timer != null) {
            timer.cancel();
        }
        QuestionId++;
        askQuestion();
    }

    private static void displayResult() {
        System.out.println("Quiz completed!");
        System.out.println("Your Score: " + score + " out of " + questions.length);
        System.out.println("Summary of Correct/Incorrect Answers:");

        for (int i = 0; i < questions.length; i++) {
            String result = "Question " + (i + 1) + ": ";
            char userAnswer = Options[i][0].toLowerCase().charAt(1);
            if (userAnswer == ans[i]) {
                continue;
            } else {
                result += "Correct answer is: " + Options[i][ans[i] - 'a'];
            }
            System.out.println(result);
        }
    }
}