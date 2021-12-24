package br.com.leodevel.testgb.service

import br.com.leodevel.testgb.dto.request.DealerRequestDTO
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class DealerServiceTest {

    @Autowired
    private lateinit var dealerService: DealerService

    @Test
    fun insertDealerTest() {

        val dealerRequestDTO = DealerRequestDTO(
            name = "Ruan Souza",
            cpf = "86658793077",
            email = "ru12@gmail.com",
            password = "123"
        )

        dealerService.save(dealerRequestDTO)

        val response = dealerService.findByCpf("86658793077")

        assertNotNull(response)

    }

}