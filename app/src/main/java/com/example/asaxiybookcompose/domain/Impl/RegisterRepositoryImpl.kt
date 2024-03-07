package com.example.asaxiybookcompose.domain.Impl

import com.example.asaxiybookcompose.Mapper
import com.example.asaxiybookcompose.data.data.AddUserData
import com.example.asaxiybookcompose.data.source.MySharedPreference
import com.example.asaxiybookcompose.domain.RegisterRepository
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegisterRepositoryImpl @Inject constructor(

) : RegisterRepository {

    private val fireStore = Firebase.firestore

    override fun loginUser(password: String, gmail: String): Flow<Result<String>> = callbackFlow {
        fireStore
            .collection("users")
            .whereEqualTo("password", password)
            .whereEqualTo("gmail", gmail)
            .limit(1)
            .addSnapshotListener { value, error ->

                if (value?.documents?.size == 0) {
                    trySend(Result.failure(Throwable("Bunaqa user mavjud emas !")))
                } else {
                    val user = Mapper.run { value!!.toUserDataList()[0] }
                    MySharedPreference.setUserData(user)
                    trySend(Result.success("Success"))
                    channel.close()
                }

                if (error != null) {
                    trySend(Result.success("Error"))
                    channel.close()
                }
            }

        awaitClose()

    }

    override fun registerUser(name: String, gmail: String, password: String): Flow<Result<Unit>> = callbackFlow {
        // TODO

        fireStore
            .collection("users")
            .add(AddUserData(name, gmail, password))
            .addOnSuccessListener {
                trySend(Result.success(Unit))
            }.addOnFailureListener {
                trySend(Result.failure(it))
            }

        awaitClose()

    }

}