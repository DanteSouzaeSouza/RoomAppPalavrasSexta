package br.com.theoldpinkeye.roomapppalavrassexta;

import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Classe responsável por criar uma instância da Base de dados
public abstract class WordRoomDatabase extends RoomDatabase {

  public abstract WordDao wordDao();

  // Objeto WordRoomDatabase que é único (static) e permite ter seus valores alterados (volatile)
  public static volatile WordRoomDatabase INSTANCE;
  // definindo quantas threads usaremos - numero imutável (final)
  public static final int NUMBER_OF_THREADS = 4;
  // instanciando um ExecutorService que vai fornecer as threads para nós
  static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

  // Construindo a isntância da base de dados
  static WordRoomDatabase getDatabase(final Context context){
    // checa se a INSTANCE está vazia
    if (INSTANCE == null){
      // cria uma base de dados de forma síncrona caso INSTANCE esteja vazia
      synchronized (WordRoomDatabase.class){
        if (INSTANCE == null) {
          // alimentando a INSTANCE com o Context da aplicação, a classe modelo da Base de dados
          // e o nome dessa Base dados
          INSTANCE = Room.databaseBuilder(
              context.getApplicationContext(),
              WordRoomDatabase.class,
              "word_database")
              .build();
        }
      }
    }
    // feito isso, retorna a própria INSTANCE atualizada
    return INSTANCE;
  }

}
