package ufc.smd.restclient.client

import retrofit2.Call
import retrofit2.http.GET

interface IpApiService {
    @GET("json")
    fun getIpInfo(): Call<IpApiResponse>
}
