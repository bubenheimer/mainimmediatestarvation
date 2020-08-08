package org.bubenheimer.sample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStateAtLeast
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        lifecycleScope.launch {
            lifecycle.whenStateAtLeast(Lifecycle.State.STARTED) {
//                withContext(Dispatchers.Main) {
                    supervisorScope {
                        // Rule 1 simulation (good rule)
                        async {
                            MyDialogFragment().showNow(supportFragmentManager, "DLG")
                            Log.i("MainActivity", "showNow done")
                            Unit
                        }

                        // Rule 2 simulation (bad rule)
                        while (true) {
                            yield()
                            try {
                                async {
                                    throw Exception("Some misbehaving library function that usually takes 10 seconds")
                                }.await()
                            } catch (e: Exception) {
//                            Log.e("MainActivity", "Caught exception: e")
                            }
                        }
                    }
//                }
            }
        }
    }
}
