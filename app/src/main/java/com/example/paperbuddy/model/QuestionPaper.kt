package com.example.paperbuddy.model

import java.util.Date

// This is the main object we'll save in Firestore and display
data class QuestionPaper(
    val id: String = "", // Firestore document ID
    val title: String = "Untitled Paper",
    val topic: String = "General Knowledge",
    val questionCount: Int = 0,
    val timestamp: Date = Date(),
    val questions: List<Question> = emptyList()
)

// This is the sub-collection or list within the paper
data class Question(
    val questionText: String = "",
    val options: List<String> = emptyList(), // For MCQs
    val correctAnswerIndex: Int = 0,
    val type: String = "MCQ" // "MCQ" or "WRITTEN"
)