package ch.cpnv.angrywirds.Providers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;

import ch.cpnv.angrywirds.Activities.GameOver;
import ch.cpnv.angrywirds.Models.Data.Language;
import ch.cpnv.angrywirds.Models.Data.PostAssignmentsDatas;
import ch.cpnv.angrywirds.Models.Data.Vocabulary;
import ch.cpnv.angrywirds.Models.Data.Word;

/**
 * @author
 * Nicolas Henry  */
public abstract class VocProvider {
    private static final String API = "http://voxerver.mycpnv.ch/api/v1/";

    public enum Status { unknown, ready, cancelled, nocnx }
    public static Status status = Status.unknown;

    public static ArrayList<Language> languages;
    public static ArrayList<Vocabulary> vocabularies;
    public static String messageRespons;
    private static BitmapFont font;

    static public void load() {
        languages = new ArrayList<Language>();
        vocabularies = new ArrayList<Vocabulary>();
        font= new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(5);

        /*
        Get my vocs homework (point 1)
         */
        HttpRequestBuilder requestLangues = new HttpRequestBuilder();
        Net.HttpRequest httpRequestLangues = requestLangues.newRequest().method(Net.HttpMethods.GET).url(API+"assignments/*EFF7485DF3BCDAFA547DD4DAF868450C3FEC383F").build();
        Gdx.net.sendHttpRequest(httpRequestLangues, new Net.HttpResponseListener() {

            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                JsonReader jsonlangue = new JsonReader();
                JsonValue baselangue = jsonlangue.parse(httpResponse.getResultAsString());
                for (JsonValue langages : baselangue.iterator()) {
                    languages.add(new Language(langages.getInt("assignment_id"),langages.getString("title"),langages.getString("result")));
                    Gdx.app.log("Q1", langages.toString());
                }
            }

            @Override
            public void failed(Throwable t) {
                status = Status.nocnx;
                Gdx.app.log("ANGRY", "No connection", t);
            }

            @Override
            public void cancelled() {
                status = Status.cancelled;
                Gdx.app.log("ANGRY", "cancelled");
            }
        });

        /*
        Get my assigned vocs
         */
        httpRequestLangues = requestLangues.newRequest().method(Net.HttpMethods.GET).url(API+"fullvocs").build();
        Gdx.net.sendHttpRequest(httpRequestLangues, new Net.HttpResponseListener() {

            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                JsonReader jreader = new JsonReader();
                JsonValue vocs = jreader.parse(httpResponse.getResultAsString());
                for (JsonValue voc : vocs.iterator()) {
                    Vocabulary newvoc = new Vocabulary(voc.getInt("mId"),voc.getString("mTitle"), voc.getInt("mLang1"), voc.getInt("mLang2"));

                    for (JsonValue word : voc.get("Words").iterator()) {
                        newvoc.addWord(new Word(word.getInt("mId"), word.getString("mValue1"), word.getString("mValue2")));
                    }
                    vocabularies.add(newvoc);
                }
                status = Status.ready;
            }

            @Override
            public void failed(Throwable t) {
                status = Status.nocnx;
                Gdx.app.log("ANGRY", "No connection", t);
            }

            @Override
            public void cancelled() {
                status = Status.cancelled;
                Gdx.app.log("ANGRY", "cancelled");
            }
        });

    }


    //Send data to my homework
    static public void submitResults (int assignment_id, int result, String token) {
        Gdx.app.log("AJAXPOST", "Appel ajax demandé");
        HttpRequestBuilder requestSubmitResults = new HttpRequestBuilder();
        PostAssignmentsDatas datas = new PostAssignmentsDatas(assignment_id, result, token);
        Net.HttpRequest httpRequestSubmitResults = requestSubmitResults.newRequest()
                .method(Net.HttpMethods.POST)
                .jsonContent(datas)
                .url(API+"result")
                .build();
        Gdx.app.log("AJAXPOST", httpRequestSubmitResults.getContent());
        Gdx.net.sendHttpRequest(httpRequestSubmitResults, new Net.HttpResponseListener() {

            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                Gdx.app.log("AJAXPOST", "Soumission des résultats");

                //Check if Laravel send correct response
                if (httpResponse.getStatus().getStatusCode() == 403){
                    messageRespons = "Votre score n'as pas pu être envoyé (403)";

                    Gdx.app.log("AJAX", "Erreur 403");
                }

                //Check if Laravel send correct response
                if (httpResponse.getStatus().getStatusCode() == 500){
                    messageRespons = "Votre score n'as pas pu être envoyé (500)";

                    Gdx.app.log("AJAX", "Erreur 500");
                }
            }

            @Override
            public void failed(Throwable t) {
                status = Status.nocnx;
                Gdx.app.log("AJAXPOST", "No connection", t);
            }

            @Override
            public void cancelled() {
                status = Status.cancelled;
                Gdx.app.log("AJAXPOST", "cancelled");
            }
        });
    }

    public static void setVocabularies(ArrayList<Vocabulary> vocabularies) {
        VocProvider.vocabularies = vocabularies;
    }

    public static ArrayList<Vocabulary> getVocabularies() {
        return vocabularies;
    }

    public void draw(Batch batch)
    {
        font.draw(batch, messageRespons, 200, 200);
    }
}
