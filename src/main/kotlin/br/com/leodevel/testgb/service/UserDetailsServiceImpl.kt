package br.com.leodevel.testgb.service

import br.com.leodevel.testgb.exception.EntityNotFoundException
import br.com.leodevel.testgb.exception.UserNotFoundException
import br.com.leodevel.testgb.repository.DealerRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    private val dealerRepository: DealerRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        return dealerRepository.findByCpf(username)
            ?: throw UserNotFoundException()
    }

}