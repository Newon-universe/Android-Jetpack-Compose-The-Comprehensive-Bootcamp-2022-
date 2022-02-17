package com.example.jettrivia.repository

import com.example.jettrivia.data.DataOrException
import com.example.jettrivia.model.QuestionItem
import com.example.jettrivia.network.QuestionApi
import javax.inject.Inject

class QuestionRepository @Inject constructor(private val api: QuestionApi) {
    private val dataOrException = DataOrException<
            ArrayList<QuestionItem>,
            Boolean,
            Exception>()

    suspend fun getAllQuestions(): DataOrException<ArrayList<QuestionItem>, Boolean, Exception>{
        try {
            dataOrException.loadingState = true
            

        } catch (exception: Exception) {

        }
    }

}