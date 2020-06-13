package br.com.theoldpinkeye.roomapppalavrassexta.view;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import br.com.theoldpinkeye.roomapppalavrassexta.data.WordRepository;
import br.com.theoldpinkeye.roomapppalavrassexta.model.Word;
import java.util.List;



public class WordViewModel extends AndroidViewModel {

  // Linkou a classe com o repositório adicionando uma variável para recebê-lo
  private final WordRepository mRepository;

  private final LiveData<List<Word>> mAllWords;

  // o construtor dessa classe pega a Application e herda os métodos do AndroidViewModel
  public WordViewModel(Application application) {
    super(application);
    // criando o WordRepository
    mRepository = new WordRepository(application);
    // inicializando a lista de palavras usando o Repositório
    mAllWords = mRepository.getAllWords();
  }

  // esse método retorna uma lista de palavras em cache
  public LiveData<List<Word>> getAllWords() {
    return mAllWords;
  }

  // criando um método insert que vai se comunicar com o repository, o Dao e o banco de dados
  public void insert(Word word) {
    mRepository.insert(word);
  }


}
