package org.bubenheimer.sample

import android.os.Bundle
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
                supervisorScope {
                    async {
                        // The dialog never shows up; the async block never completes
                        MyDialogFragment().showNow(supportFragmentManager, "DLG")
                    }.await()

                    while (true) {
                        yield()
                        async {
                            throw Exception("Some misbehaving library function that usually takes 10 seconds")
                        }.await()
                    }
                }
            }
        }
    }
}
