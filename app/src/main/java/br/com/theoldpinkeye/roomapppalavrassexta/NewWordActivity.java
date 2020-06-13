package br.com.theoldpinkeye.roomapppalavrassexta;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class NewWordActivity extends AppCompatActivity {
  public static final String EXTRA_REPLY = "br.com.theoldpinkeye.roomapppalavrassexta.REPLY";
  private EditText mEditWordView;


  @Override
  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_new_word);

    // fazendo o binding dos componentes
    mEditWordView = findViewById(R.id.edit_word);
    final Button button = findViewById(R.id.button_save);

    // adicionando um método onClickListener ao botão
    button.setOnClickListener(view -> {
      // criando intent de retorno
      Intent replyIntent = new Intent();
      // se nenhuma palavra foi digitada, envia informação que não há palavra a salvar
      if (TextUtils.isEmpty(mEditWordView.getText())){
        setResult(RESULT_CANCELED, replyIntent);
      } else { // caso contrário,  envia a palavra digitada no intent e finaliza a activity
        String word = mEditWordView.getText().toString();
        replyIntent.putExtra(EXTRA_REPLY, word);
        setResult(RESULT_OK, replyIntent);
      }
      finish();
    });
  }
}
