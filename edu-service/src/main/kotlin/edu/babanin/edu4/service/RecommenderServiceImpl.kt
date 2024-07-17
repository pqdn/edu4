package edu.babanin.edu4.service

import edu.babanin.edu4.repository.DocumentRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class RecommenderServiceImpl<Document, User>(
    val scorer: Scorer<Document, User>,
    val documentRepository: DocumentRepository<Document>,
): RecommenderService<Document, User> {

    override fun getTop(user: User, limit: Int): List<Document> {
        val allDocuments = documentRepository.getAllDocuments()

        val documentsWithScore: PriorityQueue<Pair<Int, Document>> = allDocuments.mapTo(PriorityQueue(limit) { a, b ->
            a.first.compareTo(b.first)
        }) { scorer.score(it, user) to it }


        return (0 until Math.min(limit, documentsWithScore.size)).map { documentsWithScore.poll().second }
    }

    override fun addDocument(document: Document) {
        documentRepository.addDocument(document)
    }
}