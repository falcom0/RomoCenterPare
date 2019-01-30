package house.digital.pare.romocenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import house.digital.pare.api.AdapterApi;
import model.ListUserAnswer;
import model.ResponseApi;
import model.SessionManager;
import model.Survei;
import model.SurveiQuestion;
import model.UserAnswer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SurveiActivity extends AppCompatActivity implements View.OnClickListener{

    private SessionManager session;
    private RecyclerView recyclerView;
    private SurveyQuestionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survei);


        recyclerView = (RecyclerView)findViewById(R.id.list_survei_question);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        session = new SessionManager(this.getApplicationContext());
        Survei survei = getIntent().getParcelableExtra("survei_id");
        AdapterApi.service().listSurveiQuestion(survei.getIdSurvei().toString()).enqueue(new Callback<List<SurveiQuestion>>() {
            @Override
            public void onResponse(Call<List<SurveiQuestion>> call, Response<List<SurveiQuestion>> response) {
                List<SurveiQuestion> surveiQuestionList = response.body();
                adapter = new SurveyQuestionAdapter(surveiQuestionList,Integer.parseInt(session.getUserDetails().get("id_user")));
                recyclerView.setAdapter(adapter);
//                Log.v("FarizSurveiQuestion",l.get(0).getAnswers()[0].getAnswer());
            }

            @Override
            public void onFailure(Call<List<SurveiQuestion>> call, Throwable t) {

            }
        });
        Button btnSubmit = (Button) findViewById(R.id.buttonSS);
        btnSubmit.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        List<UserAnswer> userAnswerList = new ArrayList<UserAnswer>();
        for(int i=0;i<adapter.getItemCount();i++){
            RadioGroup rg = (RadioGroup)findViewById((i+1)*100);
            int rbId = rg.getCheckedRadioButtonId();
            RadioButton rb = (RadioButton) findViewById(rbId);
            UserAnswer ua = (UserAnswer)rb.getTag();
            userAnswerList.add(ua);
        }
        ListUserAnswer lua = new ListUserAnswer();lua.setUserAnswerList(userAnswerList);
        AdapterApi.service().addUserAnswer(lua).enqueue(new Callback<ResponseApi>() {
            @Override
            public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                finish();
            }

            @Override
            public void onFailure(Call<ResponseApi> call, Throwable t) {
                Toast.makeText(getBaseContext(),"Server error.",Toast.LENGTH_LONG);
            }
        });
    }
}
