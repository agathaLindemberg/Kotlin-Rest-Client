package ufc.smd.restclient.cep

data class ViaCEPResponse(
    val cep: String,
    val logradouro: String,
    val complemento: String?,
    val bairro: String,
    val localidade: String,
    val uf: String,
    val ibge: String?,
    val gia: String?,
    val ddd: String?,
    val siafi: String?
)
