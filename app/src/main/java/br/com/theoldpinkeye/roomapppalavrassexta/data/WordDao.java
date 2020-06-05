package br.com.theoldpinkeye.roomapppalavrassexta.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import br.com.theoldpinkeye.roomapppalavrassexta.model.Word;
import java.util.List;
// transformando a Interface em um DAO
@Dao
public interface WordDao {

  // permitir a inserção de uma palavra repetida
  // passando orientação para ignorar o erro
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  void insert(Word word);

  // Query que deleta os dados
  @Query("DELETE FROM word_table")
  void deleteAll();

  // Seleciona os dados em ordem alfabética
  @Query("SELECT * FROM word_table ORDER BY word ASC")
  LiveData<List<Word>> getAlphabetizedWords();
}
