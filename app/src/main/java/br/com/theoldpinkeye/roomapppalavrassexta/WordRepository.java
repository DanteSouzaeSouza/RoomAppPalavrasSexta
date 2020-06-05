package br.com.theoldpinkeye.roomapppalavrassexta;

import android.app.Application;
import androidx.lifecycle.LiveData;
import br.com.theoldpinkeye.roomapppalavrassexta.model.Word;
import java.util.List;
// classe que vai gerenciar as queries e permitir o uso de diversos backends
// implementa a lógica que vai decidir de onde pegar os dados
class WordRepository {
  // criando instâncias das variáveis
  private WordDao mWordDao;
  private LiveData<List<Word>> mAllWords;

  // acessar no background a instância da base de dados passando o contexto da aplicação
  WordRepository(Application application){
    // acessando a instância e jogando numa variável local
    WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
    //atrelando os componentes da base de dados aos componentes internos do repositório
    mWordDao = db.wordDao();
    mAllWords = mWordDao.getAlphabetizedWords();
  }

  // faz ser executada a query em uma thread separada
  // LiveData vai notificar quando os dados mudaram
  LiveData<List<Word>> getAllWords(){
    return mAllWords;
  }

  // será chamado também numa thread diferente que não travará o app
  // quem garante isso é o Rooom
  void insert(Word word){
    mWordDao.insert(word);
  }

}
