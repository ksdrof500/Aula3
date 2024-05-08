package com.aula.aula3.splash

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aula.aula3.MainActivity
import com.aula.aula3.databinding.ActivitySplashBinding
import com.aula.aula3.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onConfigLoaded()
    }

    private fun onConfigLoaded(){
        binding.splashAnimation.addAnimatorListener(object : AnimatorListener {
            override fun onAnimationStart(p0: Animator) {
            }

            override fun onAnimationEnd(p0: Animator) {
                openNextScreen()
            }

            override fun onAnimationCancel(p0: Animator) {
            }

            override fun onAnimationRepeat(p0: Animator) {
            }

        })
    }

    private fun openNextScreen() {
        if (FirebaseAuth.getInstance().currentUser == null)
            startActivity(Intent(this, LoginActivity::class.java))
        else
            startActivity(Intent(this, MainActivity::class.java))
    }
}