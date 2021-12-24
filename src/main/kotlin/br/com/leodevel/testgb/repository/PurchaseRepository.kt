package br.com.leodevel.testgb.repository

import br.com.leodevel.testgb.model.Purchase
import org.springframework.data.mongodb.repository.MongoRepository

interface PurchaseRepository: MongoRepository<Purchase, String> {

    fun findByDealerId(id: String): List<Purchase>

    fun findByDealerIdAndCod(dealerId: String, cod: String): Purchase?

}