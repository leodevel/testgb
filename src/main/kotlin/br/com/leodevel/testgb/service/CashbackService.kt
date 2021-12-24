package br.com.leodevel.testgb.service

import br.com.leodevel.testgb.client.CashbackClient
import br.com.leodevel.testgb.dto.response.CashbackBodyResponseDTO
import org.springframework.stereotype.Service

@Service
class CashbackService(private val cashbackClient: CashbackClient) {

    fun getAccumulated(cpf: String): CashbackBodyResponseDTO {
        return cashbackClient.getAccumulated(cpf).body
    }

    fun calculatePctCashback(purchaseValue: Double): Double {
        return when{
            purchaseValue <= 0 -> 0.0
            purchaseValue <= 1000 -> 10.0
            purchaseValue <= 1500 -> 15.0
            else -> 20.0
        }
    }

    fun calculateValueCashback(purchaseValue: Double, pctCashback: Double): Double {
        return (purchaseValue * pctCashback) / 100
    }

}