package br.com.leodevel.testgb.service

import br.com.leodevel.testgb.dto.request.DealerRequestDTO
import br.com.leodevel.testgb.dto.request.UpdatePasswordRequestDTO
import br.com.leodevel.testgb.dto.response.DealerResponseDTO
import br.com.leodevel.testgb.exception.EntityNotFoundException
import br.com.leodevel.testgb.helper.removeCpfFormatting
import br.com.leodevel.testgb.model.Dealer
import br.com.leodevel.testgb.model.toResponseDTO
import br.com.leodevel.testgb.repository.DealerRepository
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class DealerService(private val dealerRepository: DealerRepository,
                    private val passwordEncoder: PasswordEncoder
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun save(dealerRequestDTO: DealerRequestDTO): DealerResponseDTO {

        logger.info("save dealer cpf {}", dealerRequestDTO.cpf)

        var dealer = buildDealer(dealerRequestDTO)
        return dealerRepository.save(dealer).toResponseDTO()

    }

    fun updatePassword(updatePasswordRequestDTO: UpdatePasswordRequestDTO) {

        logger.info("update password dealer cpf {}", updatePasswordRequestDTO.cpf)

        updatePasswordRequestDTO.cpf = updatePasswordRequestDTO.cpf.removeCpfFormatting()

        val dealer = dealerRepository.findByCpf(updatePasswordRequestDTO.cpf!!)
            ?: throw EntityNotFoundException("dealer.not.found")

        dealer.password = passwordEncoder.encode(updatePasswordRequestDTO.password)

        dealerRepository.save(dealer)

    }

    private fun buildDealer(dealerRequestDTO: DealerRequestDTO): Dealer {

        dealerRequestDTO.cpf = dealerRequestDTO.cpf.removeCpfFormatting()
        dealerRequestDTO.password = passwordEncoder.encode(dealerRequestDTO.password)

        val dealerCurrent = findByCpf(dealerRequestDTO.cpf!!)

        return Dealer(
            id = dealerCurrent?.id,
            name = dealerRequestDTO.name!!,
            cpf = dealerRequestDTO.cpf!!,
            email = dealerRequestDTO.email!!,
            password = dealerRequestDTO.password!!
        )

    }

    fun findByCpf(cpf: String): DealerResponseDTO? {
        logger.info("find dealer cpf {}", cpf)
        return dealerRepository.findByCpf(cpf)?.toResponseDTO()
    }

}