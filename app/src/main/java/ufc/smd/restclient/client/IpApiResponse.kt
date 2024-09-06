package ufc.smd.restclient.client

data class IpApiResponse(
    val ip: String,
    val city: String,
    val region: String,
    val country_name: String,
    val latitude: Double,
    val longitude: Double,
    val org: String
)
