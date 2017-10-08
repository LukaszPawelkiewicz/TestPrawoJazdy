package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.enums.QuestionType;

public class Questions {

    private static Questions instance;
    private List<Question> questions1p = new ArrayList<>();
    private List<Question> questions2p = new ArrayList<>();
    private List<Question> questions3p = new ArrayList<>();
    private List<Question> questions4p = new ArrayList<>();
    private List<Question> questions5p = new ArrayList<>();
    private List<Question> questions6p = new ArrayList<>();

    private static final String CSV_FILE = "pytania.csv";
    private static final String CSV_SPLIT_BY = ";";

    private Questions() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(CSV_FILE));
            String line;
            String[] separedLine;

            while ((line = br.readLine()) != null) {
                separedLine = line.split(CSV_SPLIT_BY);

                Question tmp = new Question();
                tmp.setNumber1(separedLine[0]);
                tmp.setNumber2(separedLine[1]);
                tmp.setText(separedLine[2]);
                tmp.setAnswer(separedLine[3]);
                if ("TF".equals(separedLine[4])) {
                    tmp.setType(QuestionType.TRUE_FALSE);
                }
                if ("SC".equals(separedLine[4])) {
                    tmp.setType(QuestionType.SINGLE_CHOICE);
                }
                tmp.setQuestionImg("imgs/" + separedLine[0] + "_" + separedLine[1] + ".jpg");
                tmp.setHelp(separedLine[5]);

                if ("1".equals(tmp.getNumber1())) {
                    questions1p.add(tmp);
                }
                if ("2".equals(tmp.getNumber1())) {
                    questions2p.add(tmp);
                }
                if ("3".equals(tmp.getNumber1())) {
                    questions3p.add(tmp);
                }
                if ("4".equals(tmp.getNumber1())) {
                    questions4p.add(tmp);
                }
                if ("5".equals(tmp.getNumber1())) {
                    questions5p.add(tmp);
                }
                if ("6".equals(tmp.getNumber1())) {
                    questions6p.add(tmp);
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Questions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Questions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Question> getQuestions1p() {
        return questions1p;
    }

    public List<Question> getQuestions2p() {
        return questions2p;
    }

    public List<Question> getQuestions3p() {
        return questions3p;
    }

    public List<Question> getQuestions4p() {
        return questions4p;
    }

    public List<Question> getQuestions5p() {
        return questions5p;
    }

    public List<Question> getQuestions6p() {
        return questions6p;
    }

    public static Questions getInstance() {
        if (instance == null) {
            instance = new Questions();
        }
        return instance;
    }

}
