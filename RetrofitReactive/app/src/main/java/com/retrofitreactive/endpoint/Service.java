package com.retrofitreactive.endpoint;




import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface Service {
    @POST(Constants.POST)
    Observable<FacturaResponse> generateFacturaRX(@Body Pojo pojo);
}
