package edu.babanin.edu4.service

interface Scorer<Document, User> {
    fun score(doc: Document, user: User): Int
}