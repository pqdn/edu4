package edu.babanin.edu4.service

interface RecommenderService<Document, User> {
    fun getTop(user: User, limit: Int): List<Document>

    fun addDocument(document: Document)
}