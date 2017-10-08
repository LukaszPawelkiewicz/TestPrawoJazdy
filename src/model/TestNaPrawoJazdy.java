package model;

import java.util.Random;

public class TestNaPrawoJazdy {

    private final Question[] questions = new Question[32];

    private int currentQuestion;
    private int points = 0;
    private boolean endOfTest;

    public TestNaPrawoJazdy() {
        Random random = new Random();
        Question tmp;

        for (int i = 0; i < 4; i++) {
            tmp = Questions.getInstance().getQuestions1p().get(random.nextInt(Questions.getInstance().getQuestions1p().size()));
            tmp.setNumber2(String.valueOf(i + 1));
            questions[i] = tmp;
            Questions.getInstance().getQuestions1p().remove(tmp);
        }

        for (int i = 0; i < 6; i++) {
            tmp = Questions.getInstance().getQuestions2p().get(random.nextInt(Questions.getInstance().getQuestions2p().size()));
            tmp.setNumber2(String.valueOf(i + 5));
            questions[i + 4] = tmp;
            Questions.getInstance().getQuestions2p().remove(tmp);
        }

        for (int i = 0; i < 10; i++) {
            tmp = Questions.getInstance().getQuestions3p().get(random.nextInt(Questions.getInstance().getQuestions3p().size()));
            tmp.setNumber2(String.valueOf(i + 11));
            questions[i + 10] = tmp;
            Questions.getInstance().getQuestions3p().remove(tmp);
        }

        for (int i = 0; i < 2; i++) {
            tmp = Questions.getInstance().getQuestions4p().get(random.nextInt(Questions.getInstance().getQuestions4p().size()));
            tmp.setNumber2(String.valueOf(i + 21));
            questions[i + 20] = tmp;
            Questions.getInstance().getQuestions4p().remove(tmp);
        }

        for (int i = 0; i < 4; i++) {
            tmp = Questions.getInstance().getQuestions5p().get(random.nextInt(Questions.getInstance().getQuestions5p().size()));
            tmp.setNumber2(String.valueOf(i + 23));
            questions[i + 22] = tmp;
            Questions.getInstance().getQuestions5p().remove(tmp);
        }

        for (int i = 0; i < 6; i++) {
            tmp = Questions.getInstance().getQuestions6p().get(random.nextInt(Questions.getInstance().getQuestions6p().size()));
            tmp.setNumber2(String.valueOf(i + 27));
            questions[i + 26] = tmp;
            Questions.getInstance().getQuestions6p().remove(tmp);
        }

        currentQuestion = 0;
        points = 0;

    }

    public void correctAnswer() {
        if ("1".equals(questions[currentQuestion].getNumber1())
                || "4".equals(questions[currentQuestion].getNumber1())) {
            this.points += 1;
        }
        if ("2".equals(questions[currentQuestion].getNumber1())
                || "5".equals(questions[currentQuestion].getNumber1())) {
            this.points += 2;
        }
        if ("3".equals(questions[currentQuestion].getNumber1())
                || "6".equals(questions[currentQuestion].getNumber1())) {
            this.points += 3;
        }

        if (this.currentQuestion >= 31) {
            endOfTest = true;
        } else {
            this.currentQuestion++;
        }
        System.out.println(this.points);
    }

    public void incorrectAnswer() {
        if (this.currentQuestion >= 31) {
            endOfTest = true;
        } else {
            this.currentQuestion++;
        }
        System.out.println(this.points);
    }

    public Question getCurrentQuestion() {
        return this.questions[currentQuestion];
    }

    public int getPoints() {
        return points;

    }

    public boolean isEndOfTest() {
        return endOfTest;
    }

    public void setEndOfTest(boolean endOfTest) {
        this.endOfTest = endOfTest;
    }

}
