package com.cultmost.android.ui

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.cultmost.android.R
import com.cultmost.android.usecases.AuthUseCase
import com.facebook.*
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.rbddevs.splashy.Splashy
import kotlinx.android.synthetic.main.activity_login.*


const val RC_SIGN_IN = 148

class LoginActivity : AppCompatActivity() {

    private lateinit var callbackManager: CallbackManager
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (AuthUseCase.isLoggedIn(applicationContext)) {
            goToMainScreen()
            finish()
        } else {
//            setSplashy()
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        setContentView(R.layout.activity_login)
        policy_text_view.movementMethod = LinkMovementMethod.getInstance()

        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)

        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {
                    val request: GraphRequest = GraphRequest.newMeRequest(
                        loginResult!!.accessToken
                    ) { jsonObject, response ->
                        Log.v("LoginActivity", response.toString())
                        val email: String = jsonObject.getString("email")
                        finishSignIn(email)
                    }
                    val parameters = Bundle()
                    parameters.putString("fields", "email")
                    request.parameters = parameters
                    request.executeAsync()
                }

                override fun onCancel() {

                }

                override fun onError(exception: FacebookException) {

                }
            })
    }

    private fun setSplashy(){
        Splashy(this)
            .setLogo(R.drawable.splash_logo)
            .showTitle(false)
//            .setTitleColor(R.color.black)
//            .setTitleSize(resources.getDimension(R.dimen.title_logo_size))
//            .setLogoScaleType(ImageView.ScaleType.FIT_CENTER)
            .setSubTitleColor(R.color.black)
            .setSubTitle("Онлайн занятия по истории, искусству, географии для детей и взрослых")
            .setProgressColor("#40a7e3")
            .showProgress(true)
            .setBackgroundColor(R.color.white)
            .setFullScreen(true)
            .setTime(4200)
//            .setClickToHide(true)
            .setAnimation(Splashy.Animation.SLIDE_IN_LEFT_RIGHT, 800)
            .show()
    }

    fun login(view: View) {
        val email = email_edit_text.text.toString()
        if (checkEmail(email)) {
            AuthUseCase.saveUserEmail(applicationContext, email)
            goToMainScreen()
            finish()
        }
    }

    private fun checkEmail(email: String): Boolean {
        return if (email.isBlank()) {
            email_edit_text.error = "Введите e-mail адрес"
            false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            email_edit_text.error = "Введите корректный e-mail адрес"
            false
        } else
            true
    }

    fun continueWithoutSignUp(view: View) {
        AuthUseCase.saveUserEmail(applicationContext, "no_email")
        goToMainScreen()
        finish()
    }

    fun loginWithGoogle(view: View) {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount = completedTask.getResult(ApiException::class.java)!!
            // Signed in successfully
            finishSignIn(account.email!!)
        } catch (e: ApiException) {
            Log.w("LoginActivity", "signInResult:failed code=" + e.statusCode)
        }
    }

    fun loginWithFacebook(view: View) {
        LoginManager.getInstance().logInWithReadPermissions(
            this,
            listOf("email")
        )
    }

    fun finishSignIn(email: String) {
        AuthUseCase.saveUserEmail(applicationContext, email)
        goToMainScreen()
        finish()
    }

    private fun goToMainScreen() {
        startActivity(Intent(this, MainScreenActivity::class.java))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }
}