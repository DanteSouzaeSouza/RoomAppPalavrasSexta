package br.com.theoldpinkeye.roomapppalavrassexta.model;

// Classe entidade

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// transformando a classe em Entity no banco de dados do Room
@Entity(tableName = "word_table") // cria tabela no banco
public class Word {

  // informando as propriedades do campo na tabela
  @PrimaryKey // chave primária
  @NonNull // não aceita valores nulos
  @ColumnInfo(name = "word") // informando o nome da coluna que vai receber esse dado
  private final String mWord;

  // método construtor
  public Word(@NonNull String mWord) {
    this.mWord = mWord;
  }

  // método get
  public String getWord() {
    return this.mWord;
  }
}
