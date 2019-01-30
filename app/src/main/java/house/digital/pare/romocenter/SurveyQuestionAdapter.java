package house.digital.pare.romocenter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

import model.SurveiAnswer;
import model.SurveiQuestion;
import model.UserAnswer;

public class SurveyQuestionAdapter extends RecyclerView.Adapter<SurveyQuestionAdapter.ViewHolder> {

    private List<SurveiQuestion> surveyQuestionList;
    private ViewHolder holder;
    private Integer idUser;

    public SurveyQuestionAdapter(List<SurveiQuestion> surveyQuestionList, Integer idUser){
        this.surveyQuestionList = surveyQuestionList;
        this.idUser = idUser;
    }

    @NonNull
    @Override
    public SurveyQuestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_survei,viewGroup,false);
        this.holder = new ViewHolder(view);
        return holder;
    }

    public ViewHolder getHolder() {
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SurveyQuestionAdapter.ViewHolder holder, int position) {
        holder.question.setText(surveyQuestionList.get(position).getQuestion());
        holder.answerGroup.setId((position+1)*100);
        holder.answerGroup.setOrientation(LinearLayout.VERTICAL);
        for (SurveiAnswer sa:surveyQuestionList.get(position).getAnswers()) {
            if(!sa.getAnswer().isEmpty()) {
                RadioButton rb = new RadioButton(holder.answerGroup.getContext());
                rb.setId(sa.getIdanswer() * 10);
                rb.setText(sa.getAnswer());
                rb.setTag(new UserAnswer(surveyQuestionList.get(position).getIdQuestion(), sa.getIdanswer(), idUser));
                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                        RadioGroup.LayoutParams.MATCH_PARENT,
                        RadioGroup.LayoutParams.MATCH_PARENT,
                        1.0f
                );
                rb.setLayoutParams(param);
                holder.answerGroup.addView(rb);
            }
        }
    }

    @Override
    public int getItemCount() {
        return surveyQuestionList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView question;
        private RadioGroup answerGroup;

        public ViewHolder(View itemView) {
            super(itemView);

            question = (TextView)itemView.findViewById(R.id.question);
            answerGroup = (RadioGroup) itemView.findViewById(R.id.answerGroup);

        }
    }
}
