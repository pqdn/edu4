package edu.babanin.edu4.repository

interface DocumentRepository<Document> {
    fun addDocument(document: Document)


    fun getAllDocuments(): Set<Document>
}