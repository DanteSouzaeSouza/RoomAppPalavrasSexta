package br.com.theoldpinkeye.roomapppalavrassexta.data;

import android.app.Application;
import androidx.lifecycle.LiveData;
import br.com.theoldpinkeye.roomapppalavrassexta.model.Word;
import java.util.List;


// classe que vai gerenciar as queries e permitir o uso de diversos backends
// implementa a lógica que vai decidir de onde pegar os dados
public class WordRepository {
  // criando instâncias das variáveis
  private final WordDao mWordDao;
  private final LiveData<List<Word>> mAllWords;

  // acessar no background a instância da base de dados passando o contexto da aplicação
  public WordRepository(Application application){
    // acessando a instância e jogando numa variável local
    WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
    //atrelando os componentes da base de dados aos componentes internos do repositório
    mWordDao = db.wordDao();
    mAllWords = mWordDao.getAlphabetizedWords();
  }

  // faz ser executada a query em uma thread separada
  // LiveData vai notificar quando os dados mudaram
  public LiveData<List<Word>> getAllWords(){
    return mAllWords;
  }

  // será chamado também numa thread diferente que não travará o app
  // quem garante isso é o Room
  public void insert(Word word){
    WordRoomDatabase.databaseWriteExecutor.execute(() -> mWordDao.insert(word));
  }

  // Importante: Se o app por fechado à força no Background pelo sistema, o ViewModel não sobrevive
  // pra que os dados do ViewModel sejam preservados, precisamos implementar um módulo Saved State

}
