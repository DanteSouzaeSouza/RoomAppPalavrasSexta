package br.com.theoldpinkeye.roomapppalavrassexta.data;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import br.com.theoldpinkeye.roomapppalavrassexta.model.Word;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Classe responsável por criar uma instância da Base de dados
// abstract só permite chamar essa classe uma vez (classe singleton)
@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

  public abstract WordDao wordDao();

  // Objeto WordRoomDatabase que é único (static) e permite ter seus valores alterados (volatile)
  public static volatile WordRoomDatabase INSTANCE;

  // definindo quantas threads usaremos - numero imutável (final)
  public static final int NUMBER_OF_THREADS = 4;
  // instanciando um ExecutorService que vai fornecer as threads para nós
  static final ExecutorService databaseWriteExecutor =
      Executors.newFixedThreadPool(NUMBER_OF_THREADS);



  //~Esse método adiciona automaticamente palavras ao banco de dados
  private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
    @Override
    public void onOpen(@NonNull SupportSQLiteDatabase db) {
      super.onOpen(db);
      Log.d("Eu aqui ó", "É memo?");

      // chamando o Executr e pedindo:
      databaseWriteExecutor.execute(() -> {

        // receber a instância do dao de dentro da Base de dados atual
        WordDao dao = INSTANCE.wordDao();
        // limpando a tabela
        dao.deleteAll();

        // mandando adicionar novas palavras
        Word word = new Word("Hello");
        dao.insert(word);
        word = new Word("World");
        dao.insert(word);
      });
    }
  };

  // Construindo a instância da base de dados passando o contexto da aplicação
  static WordRoomDatabase getDatabase(final Context context) {
    // checa se a INSTANCE está vazia
    if (INSTANCE == null) {
      // cria uma base de dados de forma síncrona caso INSTANCE esteja vazia
      synchronized (WordRoomDatabase.class) {
        // se ainda assim a instance estiver vazia:
        if (INSTANCE == null) {
          // alimentando a INSTANCE com o Context da aplicação, a classe modelo da Base de dados
          // e o nome dessa Base dados
          INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
              WordRoomDatabase.class, "word_database")
              .addCallback(sRoomDatabaseCallback) // esse callback rodará quando for feito build da base de dados
              .build();
        }
      }
    }
    // feito isso, retorna a própria INSTANCE atualizada
    return INSTANCE;
  }


}
