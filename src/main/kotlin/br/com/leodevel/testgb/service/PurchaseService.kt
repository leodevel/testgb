package br.com.leodevel.testgb.service

import br.com.leodevel.testgb.dto.request.PurchaseRequestDTO
import br.com.leodevel.testgb.dto.response.PurchaseResponseDTO
import br.com.leodevel.testgb.enums.PurchaseStatusEnum
import br.com.leodevel.testgb.exception.EntityNotFoundException
import br.com.leodevel.testgb.helper.removeCpfFormatting
import br.com.leodevel.testgb.model.Purchase
import br.com.leodevel.testgb.model.toResponseDTO
import br.com.leodevel.testgb.repository.DealerRepository
import br.com.leodevel.testgb.repository.PurchaseRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PurchaseService(
    private val purchaseRepository: PurchaseRepository,
    private val dealerRepository: DealerRepository,
    private val cashbackService: CashbackService,
    @Value("\${purchase.approved.cpfs}") private val purchaseApprovedCpfs: List<String>
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun save(purchaseRequestDTO: PurchaseRequestDTO): PurchaseResponseDTO {

        logger.info("save purchase from dealer cpf {}", purchaseRequestDTO.dealerCpf)

        var purchase = buildPurchase(purchaseRequestDTO)
        return purchaseRepository.save(purchase).toResponseDTO()

    }

    private fun buildPurchase(purchaseRequestDTO: PurchaseRequestDTO): Purchase {

        purchaseRequestDTO.dealerCpf = purchaseRequestDTO.dealerCpf.removeCpfFormatting()

        val dealer = dealerRepository.findByCpf(purchaseRequestDTO.dealerCpf!!)
            ?: throw EntityNotFoundException("dealer.not.found")

        val purchaseCurrent = findById(purchaseRequestDTO.id)

        val pctCashback = purchaseCurrent?.pctCashback
            ?:cashbackService.calculatePctCashback(purchaseRequestDTO.value!!)

        val valueCashback = purchaseCurrent?.valueCashback
            ?:cashbackService.calculateValueCashback(purchaseRequestDTO.value!!, pctCashback)

        return Purchase(
            id = purchaseCurrent?.id?:purchaseRequestDTO.id,
            dealer = dealer,
            cod = purchaseRequestDTO.cod!!,
            value = purchaseRequestDTO.value!!,
            pctCashback = pctCashback,
            valueCashback = valueCashback,
            status = getInitialStatus(dealer.cpf),
            dhCreate = purchaseRequestDTO.dhCreate?: LocalDateTime.now()
        )

    }

    private fun getInitialStatus(cpf: String): PurchaseStatusEnum{
        return when{
            purchaseApprovedCpfs.contains(cpf) -> PurchaseStatusEnum.APROVADO
            else -> PurchaseStatusEnum.EM_VALIDACAO
        }
    }

    private fun findById(id: String?): PurchaseResponseDTO?{
        logger.info("find purchase by id {}", id)

        if (id.isNullOrEmpty()) return null
        return purchaseRepository.findByIdOrNull(id)?.toResponseDTO()
    }

    fun findByDealerCpf(cpf: String): List<PurchaseResponseDTO> {
        logger.info("find purchase by dealer cpf {}", cpf)

        val dealer = dealerRepository.findByCpf(cpf.removeCpfFormatting()!!)
            ?: throw EntityNotFoundException("dealer.not.found")
        return purchaseRepository.findByDealerId(dealer.id!!).toResponseDTO()
    }

    fun findByDealerCpfAndCod(cpf: String, cod: String): PurchaseResponseDTO? {
        val dealer = dealerRepository.findByCpf(cpf.removeCpfFormatting()!!)
            ?: throw EntityNotFoundException("dealer.not.found")
        return purchaseRepository.findByDealerIdAndCod(dealer.id!!, cod)?.toResponseDTO()
    }

}