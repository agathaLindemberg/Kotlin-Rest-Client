package ufc.smd.restclient

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ufc.smd.restclient.client.IpApiResponse
import ufc.smd.restclient.client.IpApiService
import ufc.smd.restclient.cep.ViaCEPService
import ufc.smd.restclient.cep.ViaCEPResponse

class MainActivity : AppCompatActivity() {

    private lateinit var textViewIpInfo: TextView
    private lateinit var edCEP: EditText
    private lateinit var edAddress: EditText
    private lateinit var btViaCEP: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewIpInfo = findViewById(R.id.textViewIpInfo)
        edCEP = findViewById(R.id.editTextCEP)
        edAddress = findViewById(R.id.editTextAddress)
        btViaCEP = findViewById(R.id.btViaCEP)

        val retrofitIp = Retrofit.Builder()
            .baseUrl("https://ipapi.co/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val ipService = retrofitIp.create(IpApiService::class.java)
        buscarIpInfo(ipService)


        val retrofitCep = Retrofit.Builder()
            .baseUrl("https://viacep.com.br/ws/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val cepService = retrofitCep.create(ViaCEPService::class.java)
        btViaCEP.setOnClickListener {
            val cep = edCEP.text.toString()
            buscarCepInfo(cepService, cep)
        }
    }

    private fun buscarIpInfo(service: IpApiService) {
        val call = service.getIpInfo()

        call.enqueue(object : Callback<IpApiResponse> {
            override fun onResponse(call: Call<IpApiResponse>, response: Response<IpApiResponse>) {
                if (response.isSuccessful) {
                    val ipInfo = response.body()
                    ipInfo?.let {
                        val info = """
                            IP: ${it.ip}
                            Cidade: ${it.city}
                            Região: ${it.region}
                            País: ${it.country_name}
                            Latitude: ${it.latitude}
                            Longitude: ${it.longitude}
                            Organização: ${it.org}
                        """.trimIndent()
                        textViewIpInfo.text = info
                    }
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Erro ao obter informações do IP",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<IpApiResponse>, t: Throwable) {
                Log.e("MainActivity", "Erro: ${t.message}")
                Toast.makeText(
                    this@MainActivity,
                    "Falha ao acessar o serviço de IP",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun buscarCepInfo(service: ViaCEPService, cep: String) {
        val call = service.buscarCEP(cep)

        call.enqueue(object : Callback<ViaCEPResponse> {
            override fun onResponse(
                call: Call<ViaCEPResponse>,
                response: Response<ViaCEPResponse>
            ) {
                if (response.isSuccessful) {
                    val cepResponse = response.body()
                    cepResponse?.let {
                        val enderecoCompleto =
                            "${it.logradouro}, ${it.bairro}, ${it.localidade} - ${it.uf}"
                        edAddress.setText(enderecoCompleto) // Exibir o endereço no campo de endereço
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Erro ao buscar o CEP", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<ViaCEPResponse>, t: Throwable) {
                Log.e("MainActivity", "Erro: ${t.message}")
                Toast.makeText(
                    this@MainActivity,
                    "Falha na comunicação com o serviço de CEP",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}
