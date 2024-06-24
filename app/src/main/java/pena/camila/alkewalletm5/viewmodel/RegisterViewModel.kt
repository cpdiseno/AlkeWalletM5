import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pena.camila.alkewalletm5.model.network.RegisterRequest
import pena.camila.alkewalletm5.model.network.AccountService
import pena.camila.alkewalletm5.model.network.RegisterResponse
import pena.camila.alkewalletm5.model.network.Retrofitinstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {

    // Variable LiveData que va a informar a la vista el login
    val signupResultLiveData = MutableLiveData<Boolean>()

    fun crearUsuario(
        nombre: String,
        apellido: String,
        email: String,
        contrasena: String,
        reingresarContrasena: String,
    ) {
        viewModelScope.launch {
            val crearUsuario =
                Retrofitinstance.retrofit.create(AccountService::class.java)
            val llamadaCreacion = crearUsuario.crearUsuario(
                RegisterRequest(
                    nombre,
                    apellido,
                    email,
                    contrasena,
                    2,
                    100,
                )
            )

            llamadaCreacion.enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    if (response.isSuccessful) {
                        signupResultLiveData.postValue(true)
                    } else {
                        signupResultLiveData.postValue(false)
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    signupResultLiveData.postValue(false)
                }
            })
        }
    }

    fun crearUsuario(nombre: String, apellido: String, email: String, contrasena: String) {

    }
}