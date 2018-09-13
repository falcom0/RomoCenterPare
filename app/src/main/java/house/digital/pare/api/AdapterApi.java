package house.digital.pare.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AdapterApi {
    public static String BASE_URL = "http://api.romocenter.com/";
    public static Retrofit instance(){
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

    }

    public static void setBaseUrl(String baseUrl) {
        BASE_URL = baseUrl;
    }

    public static ApiRomoService service(){return instance().create(ApiRomoService.class);}
}
