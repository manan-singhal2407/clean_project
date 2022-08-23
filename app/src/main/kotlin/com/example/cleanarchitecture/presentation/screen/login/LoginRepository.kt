package com.example.cleanarchitecture.presentation.screen.login

import com.example.cleanarchitecture.domain.state.DataState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class LoginRepository {
    fun login(firebaseAuth: FirebaseAuth): Flow<DataState<String>> = flow {
        firebaseAuth.signInWithEmailAndPassword("singhal.manan24@gmail.com", "manan@123")
        emit(DataState.success("User successfully logged in"))
    }.catch {
        emit(DataState.error("User failed to logged in"))
    }
}