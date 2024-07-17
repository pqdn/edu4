package edu.babanin.edu4.service

import edu.babanin.edu4.client.HelloClient
import edu.babanin.edu4.model.Document
import edu.babanin.edu4.model.User
import edu.babanin.edu4.repository.DocumentRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.UUID

@ExtendWith(MockKExtension::class)
class RecommenderServiceImplTest {
    @InjectMockKs
    lateinit var service: TestRecommenderServiceImpl

    @MockK
    lateinit var documentRepository: DocumentRepository<Document>

    @MockK
    lateinit var scorer: Scorer<Document, User>

    @Test
    fun `should get top if limit bigger that docs` (){
        val user = User("123")

        val documents = setOf<Document>(
            createDocument(),
            createDocument(),
        )

        every { scorer.score(any(), any()) } returns 1

        every { documentRepository.getAllDocuments() } returns documents

        val top = service.getTop(user, 10)

        assertEquals(documents, top.toSet())
    }

    @Test
    fun `should get top with hihgt priority` (){
        val user = User("123")

        val documents = (0..100).map {
            createDocument(it.toString())
        }.toSet()

        val limit = 10

        val favoriteDocuments = documents.drop(50).take(limit).toSet()

        every { scorer.score(any(), any()) } answers {
            val doc = args[0] as Document
            if(doc in favoriteDocuments) 1
            else 100
        }

        every { documentRepository.getAllDocuments() } returns documents

        val top = service.getTop(user, limit)

        assertEquals(favoriteDocuments, top.toSet())
    }


    private fun createDocument(id: String = UUID.randomUUID().toString()): Document {
        return Document(
            id = id,
            value = "value"
        )
    }
}

class TestRecommenderServiceImpl(scorer: Scorer<Document, User>, documentRepository: DocumentRepository<Document>) :
    RecommenderServiceImpl<Document, User>(scorer, documentRepository)


