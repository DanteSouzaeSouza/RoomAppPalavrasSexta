package br.com.theoldpinkeye.roomapppalavrassexta.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import br.com.theoldpinkeye.roomapppalavrassexta.R;
import br.com.theoldpinkeye.roomapppalavrassexta.model.Word;
import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

  // essa classe é responsável por:
  // receber os dados da lista de palavras
  // criar a estrutura pra receber uma palavra usando o layout criado para o item da lista
  // fazer o binding dos dados com o layout do item da lista
  // monitorar alterações na lista
  // retornar quantos itens estão na lista

  private final LayoutInflater mInflater;
  private List<Word> mWords;

  public WordListAdapter(Context context) {
    mInflater = LayoutInflater.from(context);
  }

  @NonNull
  @Override // passa o layout do item para o recyclerview
  public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
    return new WordViewHolder(itemView);
  }

  @Override // esse método faz o binding dos dados
  public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
    // caso haja palavras na lista:
    if (mWords != null) {
      // preenche um card com uma palavra
      Word current = mWords.get(position);
      holder.wordItemView.setText(current.getWord());
    } else { // se não houver:
      // preenche um card com um texto padrão
      holder.wordItemView.setText(R.string.no_word);
    }
  }

  public void setWords(List<Word> words) {
    mWords = words; // recebe a lista de palavras de outro lugar e passa ela pra dentro do adapter
    notifyDataSetChanged();// mostra pro recyclerview que mudou a lista de palavras
  }

  @Override
  public int getItemCount() { // conta quantos itens há na lista
    return mWords != null ? mWords.size() : 0;
  }

  static class WordViewHolder extends RecyclerView.ViewHolder {

    private final TextView wordItemView;

    public WordViewHolder(View itemView) {
      super(itemView);

      wordItemView = itemView.findViewById(R.id.textView);
    }
  }
}
