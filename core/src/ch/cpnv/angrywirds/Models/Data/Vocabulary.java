package ch.cpnv.angrywirds.Models.Data;

import java.util.ArrayList;

import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.Providers.VocProvider;

/**
 * @author
 * Nicolas Henry  */
public class Vocabulary {
    int id;
    String vocName;
    int langprof;
    int langeleve;
    String result;
    ArrayList<Word> words;

    public Vocabulary(int id, String vocName, int langprof, int langeleve){
        this.id = id;
        this.vocName = vocName;
        this.langprof = langprof;
        this.langeleve = langeleve;
        this.words = new ArrayList<Word>();
        this.result = result;
    }

    public void addWord(Word w) {
        words.add(w);
    }

    public Word pickAWord() {
        return words.get(AngryWirds.alea.nextInt(words.size()));
    }

    public int getId() {
        return id;
    }

    public String getVocName() {
        return vocName;
    }

    public String getResult() {
        return result;
    }
}
