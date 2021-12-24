package br.com.leodevel.testgb.model

import br.com.leodevel.testgb.dto.response.PurchaseResponseDTO
import br.com.leodevel.testgb.enums.PurchaseStatusEnum
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.time.LocalDateTime

@Document("compra")
data class Purchase(
    @Id
    var id: String? = null,

    @DBRef
    var dealer: Dealer,

    @Field("codigo")
    var cod: String,

    @Field("valor")
    var value: Double,

    @Field("pct_cashback")
    var pctCashback: Double? = null,

    @Field("valor_cashback")
    var valueCashback: Double? = null,

    @Field("status")
    var status: PurchaseStatusEnum,

    @Field("dh_criacao")
    var dhCreate: LocalDateTime
)

fun Purchase.toResponseDTO(): PurchaseResponseDTO {
    return PurchaseResponseDTO(
        id = id,
        dealer = dealer.toResponseDTO(),
        cod = cod,
        value = value,
        pctCashback = pctCashback,
        valueCashback = valueCashback,
        status = status.status,
        dhCreate = dhCreate
    )
}

fun List<Purchase>.toResponseDTO() = map { it.toResponseDTO() }