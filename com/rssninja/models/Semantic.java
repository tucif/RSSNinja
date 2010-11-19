package com.rssninja.models;

public class Semantic {
    private int id;
    private Word word1;
    private Word word2;
    private float relation_factor;

    public Semantic(int id, Word word1, Word word2, float relation_factor){
        this.id = id;
        this.word1 = word1;
        this.word2 = word2;
        this.relation_factor = relation_factor;
    }

    public int getId() {
        return id;
    }

    public float getRelation_factor() {
        return relation_factor;
    }

    public Word getWord1() {
        return word1;
    }

    public Word getWord2() {
        return word2;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRelation_factor(float relation_factor) {
        this.relation_factor = relation_factor;
    }

    public void setWord1(Word word1) {
        this.word1 = word1;
    }

    public void setWord2(Word word2) {
        this.word2 = word2;
    }

    @Override
    public String toString() {
        return word1.toString()+"-"+relation_factor+"-"+word2.toString();
    }
   

}
