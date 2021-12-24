package br.com.leodevel.testgb.model

import br.com.leodevel.testgb.dto.response.DealerResponseDTO
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Document("revendedor")
data class Dealer(
    @Id
    var id: String? = null,

    @Field("nome")
    var name: String,

    var cpf: String,

    var email: String,

    @Field("senha")
    private var password: String

): UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf()

    override fun getPassword() = password

    fun setPassword(password: String){
        this.password = password
    }

    override fun getUsername() = cpf

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true

}

fun Dealer.toResponseDTO(): DealerResponseDTO {
    return DealerResponseDTO(
        id = id,
        name = name,
        cpf = cpf,
        email = email
    )
}