package com.example.tgergely.tvshows.containers

import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import android.R.attr.data
import com.example.tgergely.tvshows.R
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import org.jetbrains.anko.*


/**
 * Created by tudorgergely on 9/01/17.
 */
class Login : Fragment() {
    private var mListener: Login.OnFragmentInteractionListener? = null

    private var mGoogleApiClient: GoogleApiClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        mGoogleApiClient = GoogleApiClient.Builder(context)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()


    }

    private val RC_SIGN_IN: Int = 12

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return UI {
            verticalLayout {
                button(text = "Sign in with Google") {
                    onClick {
                        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
                        startActivityForResult(signInIntent, RC_SIGN_IN)
                    }
                }
            }
        }.view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Login.OnFragmentInteractionListener) {
            mListener = context as Login.OnFragmentInteractionListener?
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode === RC_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            handleSignInResult(result)
        }
    }

    private fun handleSignInResult(result: GoogleSignInResult) {
        if (result.isSuccess) {
            val acct = result.signInAccount
            context.toast(acct!!.displayName.toString())
        } else {
            context.toast("asdfasd")
        }
    }

    interface OnFragmentInteractionListener
}