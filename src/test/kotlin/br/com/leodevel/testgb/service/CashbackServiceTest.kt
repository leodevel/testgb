package br.com.leodevel.testgb.service

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class CashbackServiceTest {

    @Autowired
    private lateinit var cashbackService: CashbackService

    @Test
    fun creditNotNull() {
        val cpf = "42762115817"
        assertNotNull(cashbackService.getAccumulated(cpf).credit)
    }

    @Test
    fun calculatePctCashbackIsSuccess() {
        assertEquals( 0.0, cashbackService.calculatePctCashback(-10.0), 0.0)
        assertEquals(10.0, cashbackService.calculatePctCashback(560.0), 0.0)
        assertEquals(15.0, cashbackService.calculatePctCashback(1200.0), 0.0)
        assertEquals(20.0, cashbackService.calculatePctCashback(1600.0), 0.0)
    }

    @Test
    fun calculateValueCashbackIsSuccess() {
        assertEquals(100.0, cashbackService.calculateValueCashback(1000.0, 10.0), 0.0)
    }

}