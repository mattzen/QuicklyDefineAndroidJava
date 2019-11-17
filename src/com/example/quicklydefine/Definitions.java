package com.example.quicklydefine;

public class Definitions {

    //private variables
    String _examples;
    String _type;
    String definition;
    // Empty constructor
    public Definitions(){

    }
    // constructor
    public Definitions(String definition, String type, String example){
       
    	this.definition = definition;
    	this._examples = example;
    	this._type = type;
    }

    // constructor
    public Definitions(String examples, String type){
        this._examples = examples;
        this._type = type;
    }

    // getting name
    public String getExample(){
        return this._examples;
    }

    // setting name
    public void setexample(String examples){
        this._examples = examples;
    }

    // getting phone number
    public String getType(){
        return this._type;
    }

    // setting phone number
    public void setType(String type){
        this._type = type;
    }
}