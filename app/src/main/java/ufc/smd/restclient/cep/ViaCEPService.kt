package ufc.smd.restclient.cep

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ViaCEPService {
    @GET("{cep}/json/")
    fun buscarCEP(@Path("cep") cep: String): Call<ViaCEPResponse>
}
