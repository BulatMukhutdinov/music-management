package tat.mukhutdinov.musicmanagement.infrastructure

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tat.mukhutdinov.musicmanagement.R

class ContainerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.container)
    }
}