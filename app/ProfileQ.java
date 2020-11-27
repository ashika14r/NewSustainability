package com.example.newsustainability;

public class ProfileQ {
    private String questions[] = {
            "How important sustainability is to you?",
            "Do you use lots of Electricity?",
            "Do you use lots of Water?",
            "Do you use lots of gasoline?" };

    private String answeres [] [] = {

            {"Very important", "Medium important", "Not important"},
            {"Lots of electricity", "Medium electricity", "Little electricity"},
            {"Lots of water", "Meidum water", "Little water"},
            {"Lots of gasoline", "Medium gasoline", "Little gasoline"}

    };

    public String getQuestions(int a){
        return questions[a];
    }

    public String getAnswers1(int a){
        return answeres[a][0];
    }

    public String getAnswers2(int a){
        return answeres[a][1];
    }

    public String getAnswers3(int a){
        return answeres[a][2];
    }

    public String getAnswers4(int a){
        return answeres[a][3];
    }

}
