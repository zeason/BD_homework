package com.assignment.survey;

import java.util.ArrayList;
import java.util.List;

import com.assignment.survey.bean.Option;
import com.assignment.survey.bean.Question;
import com.assignment.survey.bean.Survey;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SurveySerializer {


	public SurveySerializer() {
    }

	
    /**
     * Serialize survey into json
     * 
     * @param survey
     * @return
     */
    public static String serialize(Survey survey) {
    	JsonObject object = new JsonObject();
    	object.addProperty("title", survey.getTitle());
    	JsonArray pages = new JsonArray();
    	JsonObject page = new JsonObject();
    	JsonArray elements = new JsonArray();
        for (Question q : survey.getQuestions()) {
        	JsonObject question = new JsonObject();
        	question.addProperty("name", q.getTitle());
        	question.addProperty("type", "radiogroup");
        	JsonArray choices = new JsonArray();
        	for (Option o : q.getOptions()) {
        		choices.add(o.getContent());
        	}
        	question.add("choices", choices);
        	elements.add(question);
        }
        page.add("elements", elements);
        pages.add(page);
        object.add("pages", pages);
        return new Gson().toJson(object);
    }
    
    /**
     * Deserialize survey object from json
     * 
     * @param json
     * @return
     */
    public static Survey deserialize(String json){
    	
    	Survey survey = new Survey();

        JsonElement k = new JsonParser().parse(json);
    	JsonObject object = k.getAsJsonObject();
    	survey.setTitle(object.get("title").getAsString());
        JsonArray array = object.getAsJsonArray("pages");
        object = array.get(0).getAsJsonObject();
        JsonArray questionsInJson = object.getAsJsonArray("elements");
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < questionsInJson.size(); i++) {
        	object = questionsInJson.get(i).getAsJsonObject();
        	Question q = new Question();
        	q.setTitle(object.get("name").getAsString());
        	JsonArray optionsInJson = object.getAsJsonArray("choices");
        	List<Option> options = new ArrayList<>();
        	for (int j = 0; j < optionsInJson.size(); j++) {
        		Option o = new Option();
        		try {
        			o.setContent(optionsInJson.get(j).getAsJsonObject().get("text").getAsString());
        		} catch (Exception e) {
        			o.setContent(optionsInJson.get(j).getAsJsonPrimitive().getAsString());
        		}  	
            	options.add(o);
        	}
        	q.setOptions(options);
        	questions.add(q);
        }
        survey.setQuestions(questions);
        return survey;
    }
}
