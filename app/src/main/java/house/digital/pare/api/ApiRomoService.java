package house.digital.pare.api;

import java.util.List;

import model.Aspirasi;
import model.Jadwal;
import model.ListUserAnswer;
import model.Posko;
import model.PoskoJadwal;
import model.ResponseApi;
import model.Survei;
import model.SurveiAnswer;
import model.SurveiQuestion;
import model.UserAnswer;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import model.UserRomo;


public interface ApiRomoService {
    @POST("login")
    @FormUrlEncoded
    Call<ResponseApi> login(@Field("nik") String nik,
                            @Field("password") String password);

    @POST("koordinat")
    @FormUrlEncoded
    Call<List<String>> insertKoordinat(@Field("id_user") String id_user,@Field("longlat") String longlat);

    @POST("jadwal")
    @FormUrlEncoded
    Call<List<Jadwal>> getJadwal(@Field("id_user") String id_user);

    @POST("feedback")
    @FormUrlEncoded
    Call<ResponseApi> submitFeedback(@Field("comment") String comment,@Field("idJadwal") Integer idJadwal);

    @POST("posko")
    @FormUrlEncoded
    Call<ResponseApi> submitPosko(@Field("koordinat") String koordinat,@Field("namaPosko") String namaPosko,@Field("kecamatan") String kecamatan);

    @POST("posko_jadwal")
    @FormUrlEncoded
    Call<ResponseApi> submitPoskoJadwal(@Field("idPosko") Integer idPosko,@Field("judul") String judul,@Field("deskripsi") String deskripsi
            ,@Field("waktuMulai") String waktuMulai,@Field("waktuSelesai") String waktuSelesai);

    @GET("posko")
    Call<List<Posko>> listPosko();

    @GET("posko_jadwal")
    Call<List<PoskoJadwal>> listPoskoJadwal();

    @GET("survei")
    Call<List<Survei>> listSurvei();

    @GET("survei_question")
    Call<List<SurveiQuestion>> listSurveiQuestion();

    @GET("survei_question/{idsurvei}")
    Call<List<SurveiQuestion>> listSurveiQuestion(@Path("idsurvei") String idsurvei);

    @GET("survei_answer")
    Call<List<SurveiAnswer>> listSurveiAnswer(@Query("idquestion") Integer idSurveiQuestion);

    @POST("user_answer")
    Call<ResponseApi> addUserAnswer(@Body ListUserAnswer userAnswerList);

    @GET("aspirasi/{id}")
    Call<List<Aspirasi>> getAspirasi(@Path("id") String nik);

    @GET("GetCurrentTimeMillis")
    Call<Long> getCurrentTimeMillis();
}
