package com.example.paperbuddy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.paperbuddy.model.Question
import com.example.paperbuddy.model.QuestionPaper
import java.util.Date

class HomeViewModel : ViewModel() {

    // _papers is the private, editable data
    private val _papers = MutableLiveData<List<QuestionPaper>>()

    // papers is the public, read-only data that the Fragment will observe
    val papers: LiveData<List<QuestionPaper>> = _papers

    // This function will be replaced by a call to our Repository (Firebase)
    fun loadDummyData() {
        val dummyList = listOf(
            QuestionPaper(
                id = "1",
                title = "Cardiovascular Drugs",
                topic = "Pharmacology",
                questionCount = 10,
                timestamp = Date(),
                questions = emptyList()
            ),
            QuestionPaper(
                id = "2",
                title = "Paging and Segmentation",
                topic = "Operating Systems",
                questionCount = 15,
                timestamp = Date(System.currentTimeMillis() - 86400000), // 1 day ago
                questions = emptyList()
            ),
            QuestionPaper(
                id = "3",
                title = "TCP/IP vs OSI Model",
                topic = "Computer Networks",
                questionCount = 5,
                timestamp = Date(System.currentTimeMillis() - 172800000), // 2 days ago
                questions = emptyList()
            )
        )
        _papers.value = dummyList
    }

    // In a future step, we'll add this:
    // private val paperRepository = PaperRepository()
    // init {
    //     _papers = paperRepository.getAllPapers()
    // }
}