package br.com.theoldpinkeye.roomapppalavrassexta;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.theoldpinkeye.roomapppalavrassexta.data.WordDao;
import br.com.theoldpinkeye.roomapppalavrassexta.data.WordRoomDatabase;
import br.com.theoldpinkeye.roomapppalavrassexta.model.Word;
import br.com.theoldpinkeye.roomapppalavrassexta.view.WordListAdapter;

public class MainActivity extends AppCompatActivity {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    RecyclerView recyclerView = findViewById(R.id.recyclerview);
    final WordListAdapter adapter = new WordListAdapter(this);
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));


  }
}