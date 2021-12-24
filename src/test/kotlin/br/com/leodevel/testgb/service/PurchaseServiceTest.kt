package br.com.leodevel.testgb.service

import br.com.leodevel.testgb.dto.request.DealerRequestDTO
import br.com.leodevel.testgb.dto.request.PurchaseRequestDTO
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
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
class PurchaseServiceTest {

    @Autowired
    private lateinit var dealerService: DealerService

    @Autowired
    private lateinit var purchaseService: PurchaseService

    @Before
    fun insertDealerTest() {

        val dealerRequestDTO = DealerRequestDTO(
            name = "Ramon Santana",
            cpf = "12376523456",
            email = "ram12@gmail.com",
            password = "123456"
        )

        dealerService.save(dealerRequestDTO)

        val response = dealerService.findByCpf("12376523456")

        assertNotNull(response)

    }

    @Test
    fun insertPurchaseTest() {

        val purchaseRequestDTO = PurchaseRequestDTO(
            dealerCpf = "12376523456",
            cod = "12312",
            value = 350.0
        )

        purchaseService.save(purchaseRequestDTO)

        val response = purchaseService.findByDealerCpfAndCod("12376523456", "12312")

        assertNotNull(response)
        assertEquals(10.0, response?.pctCashback!!, 0.0)

    }

}