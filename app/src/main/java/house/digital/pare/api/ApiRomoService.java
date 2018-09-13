package house.digital.pare.api;

import java.util.List;

import model.ResponseApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
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
//    @GET("GetAdvImage")
//    Call<List<String>> listAdvImage();
//
//    @GET("GetFood")
//    Call<List<Food>> listFood(@Query("typeId") Integer typeId);
//
//    @GET("GetFoodType")
//    Call<List<FoodType>> listFoodType();
//
//    @GET("GetPromotion")
//    Call<List<Promo>> listPromo(@Query("id") Integer id);
//
//    @GET("GetClient")
//    Call<Room> room(@Query("mac") String mac);
//
//    @GET("GetSettings")
//    Call<Setting> setting();
//
//    @GET("GetLiveTV")
//    Call<List<TvChannel>> tvChannel(@Query("mac") String mac, @Query("packageId") Integer packageId);

//    @GET("GetSubtitles")
//    Call<List<RunningText>> runningText(@Query("mac") String mac);
//
//    @GET("GetScenery")
//    Call<List<Scenery>> listScenery(@Query("id") Integer id);
//
//    @GET("GetApkPassword")
//    Call<List<Pass>> listPass();

    @GET("GetCurrentTimeMillis")
    Call<Long> getCurrentTimeMillis();
}
