package edu.babanin.edu4.repository

import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class DocumentRepositoryImpl<Document>: DocumentRepository<Document> {

    private val documents: MutableSet<Document> = ConcurrentHashMap.newKeySet()

    override fun addDocument(document: Document) {
        documents.add(document)
    }

    override fun getAllDocuments(): Set<Document> {
        return documents
    }

}