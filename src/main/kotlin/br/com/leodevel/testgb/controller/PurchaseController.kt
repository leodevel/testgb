package br.com.leodevel.testgb.controller

import br.com.leodevel.testgb.dto.request.PurchaseRequestDTO
import br.com.leodevel.testgb.dto.response.ApiResponseDTO
import br.com.leodevel.testgb.dto.response.PurchaseResponseDTO
import br.com.leodevel.testgb.service.PurchaseService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/purchase")
@Api(tags = ["Compra"], description = "Compra")
class PurchaseController(private val purchaseService: PurchaseService) {

    @PostMapping
    @ApiOperation("Insere uma nova compra de um revendedor")
    @ApiImplicitParams(
        value = [
            ApiImplicitParam(name = "Authorization", required = true, dataType = "string", paramType = "header")
        ]
    )
    fun create(@Valid @RequestBody body: PurchaseRequestDTO): ApiResponseDTO<PurchaseResponseDTO> {
        return ApiResponseDTO(data = purchaseService.save(body))
    }

    @GetMapping("/dealer/{cpf}")
    @ApiOperation("Consulta as compras de um revendedor")
    @ApiImplicitParams(
        value = [
            ApiImplicitParam(name = "Authorization", required = true, dataType = "string", paramType = "header")
        ]
    )
    fun findAllByDealerCpf(@Valid @PathVariable cpf: String): ApiResponseDTO<List<PurchaseResponseDTO>> {
        return ApiResponseDTO(data = purchaseService.findByDealerCpf(cpf))
    }

}