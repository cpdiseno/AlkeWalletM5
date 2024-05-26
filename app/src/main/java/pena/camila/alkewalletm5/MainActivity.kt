import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import pena.camila.alkewalletm5.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Enable edge-to-edge display
        enableEdgeToEdge()

        // Configure the NavHostFragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.inicio) as NavHostFragment
        val navController = navHostFragment.navController

        // Set the SplashFragment as the initial destination
        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
        navGraph.setStartDestination(R.id.inicio)
        navController.graph = navGraph

        }
    }
