package br.com.leodevel.testgb.repository

import br.com.leodevel.testgb.model.Dealer
import org.springframework.data.mongodb.repository.MongoRepository

interface DealerRepository: MongoRepository<Dealer, String> {

    fun findByCpf(cpf: String): Dealer?

}