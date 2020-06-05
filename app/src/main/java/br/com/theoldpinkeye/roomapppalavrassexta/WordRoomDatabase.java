package br.com.theoldpinkeye.roomapppalavrassexta;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import br.com.theoldpinkeye.roomapppalavrassexta.model.Word;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
// classe que fornecerá uma instância de base de dados para o app
// deve ter as annotations abaixo:
@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

  // instanciando nosso wordDao:
  public abstract WordDao wordDao();

  // Criando uma instância do banco de forma que
  // ela seja acessível de qualque lugar dessa classe (static)
  // e que seu valor não mude de thread pra tread (volatile):
  private static volatile WordRoomDatabase INSTANCE;

  // definindo uma constante (final) para o número de threads a usar:
  private static final int NUMBER_OF_TREADS = 4;

  // criando um serviço que nos dá um pool de
  // threads para usar com operações do banco de dados:
  static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_TREADS);

  // sempre que algum método pedir o parâmetro do tipo Context,
  // é por que o resultado desse método precisa ser
  // passado de volta para a aplicação

  // método que fornece a intância da base de dados,
  // recebendo o contexto atual da aplicação:
  static WordRoomDatabase getDatabase(final Context context) {
    // se a constante INSTANCE estiver vazia:
    if (INSTANCE == null){
      // chama de forma sincronizada a classe do Banco de dados
      synchronized (WordRoomDatabase.class){
        // se dentro da classe do Banco de dados a instãncia continua vazia:
        if (INSTANCE == null){
          // cria a instância e alimenta a constante.
          INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
              WordRoomDatabase.class, "word_database")
              .build();
        }
      }
    }
    // depois de garantida a existência da instância, entrega ela pro context da aplicação
    return INSTANCE;
  }
}