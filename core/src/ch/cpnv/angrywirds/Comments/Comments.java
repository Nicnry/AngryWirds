/**
 * @author
 * Nicolas Henry
 **/

/**
 * This page show some code who works but i cant use it for the application or it crash.
 */

    //This code will show you only correct exercices (put on VocProvider)
    /**
     httpRequestLangues = requestLangues.newRequest().method(Net.HttpMethods.GET).url(API+"assignments/*EFF7485DF3BCDAFA547DD4DAF868450C3FEC383F").build();
     Gdx.net.sendHttpRequest(httpRequestLangues, new Net.HttpResponseListener() {
    @Override
    public void handleHttpResponse(Net.HttpResponse httpResponse) {
    JsonReader jreader = new JsonReader();
    JsonValue vocs = jreader.parse(httpResponse.getResultAsString());
    for (JsonValue voc : vocs.iterator())
    {
    Vocabulary newvoc = new Vocabulary(voc.getInt("assignment_id"),voc.getString("title"));

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
     **/


    //This code will show you all vocs (put on VocProvider)
    /**
     HttpRequestBuilder requestLangues = new HttpRequestBuilder();
     Net.HttpRequest httpRequestLangues = requestLangues.newRequest().method(Net.HttpMethods.GET).url(API+"languages").build();
     Gdx.net.sendHttpRequest(httpRequestLangues, new Net.HttpResponseListener() {
    @Override
    public void handleHttpResponse(Net.HttpResponse httpResponse) {
    JsonReader jsonlangue = new JsonReader();
    JsonValue baselangue = jsonlangue.parse(httpResponse.getResultAsString());
    for (JsonValue langages : baselangue.iterator())
    {
    languages.add(new Language(langages.getInt("id"),langages.getString("lName")));
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

     httpRequestLangues = requestLangues.newRequest().method(Net.HttpMethods.GET).url(API+"fullvocs").build();
     Gdx.net.sendHttpRequest(httpRequestLangues, new Net.HttpResponseListener() {
    @Override
    public void handleHttpResponse(Net.HttpResponse httpResponse) {
    JsonReader jreader = new JsonReader();
    JsonValue vocs = jreader.parse(httpResponse.getResultAsString());
    for (JsonValue voc : vocs.iterator())
    {
    Vocabulary newvoc = new Vocabulary(voc.getInt("mId"),voc.getString("mTitle"),voc.getInt("mLang1"),voc.getInt("mLang2"));
    for (JsonValue word : voc.get("Words").iterator())
    {
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
    });**/
