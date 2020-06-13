package br.com.theoldpinkeye.roomapppalavrassexta;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.theoldpinkeye.roomapppalavrassexta.data.WordDao;
import br.com.theoldpinkeye.roomapppalavrassexta.data.WordRoomDatabase;
import br.com.theoldpinkeye.roomapppalavrassexta.model.Word;
import br.com.theoldpinkeye.roomapppalavrassexta.view.WordListAdapter;
import br.com.theoldpinkeye.roomapppalavrassexta.view.WordViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private WordViewModel mWordViewModel;
  public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

  // método que gerencia a chamada do botão que adicona palavras na lista
  // esse método captura o Intent com a palavra inserida na outra Activity
  // e passa ela ao WordView model para que ela seja inserida no banco de dados e no RecyclerView
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
      Word word = new Word(data.getStringExtra(NewWordActivity.EXTRA_REPLY));
      mWordViewModel.insert(word);

    } else {
      Toast.makeText(getApplicationContext(), R.string.empty_not_saved, Toast.LENGTH_LONG).show();
    }

  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);



    // instanciando o RecyclerView e fazendo o binding da view
    RecyclerView recyclerView = findViewById(R.id.recyclerview);

    final WordListAdapter adapter = new WordListAdapter(this);
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    // instanciando o ViewModel
    mWordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
    mWordViewModel.getAllWords().observe(this, adapter::setWords);

    // lógica do FloatingActionButton:
    FloatingActionButton fab = findViewById(R.id.fab); // Binding
    fab.setOnClickListener(v -> { // esse listener vai chamar a activity pra que digitemos nova palavra
      Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
      startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
    });







  }
}