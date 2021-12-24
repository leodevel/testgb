package br.com.leodevel.testgb.controller

import br.com.leodevel.testgb.dto.request.DealerRequestDTO
import br.com.leodevel.testgb.dto.request.UpdatePasswordRequestDTO
import br.com.leodevel.testgb.dto.response.ApiResponseDTO
import br.com.leodevel.testgb.dto.response.CashbackBodyResponseDTO
import br.com.leodevel.testgb.dto.response.DealerResponseDTO
import br.com.leodevel.testgb.service.CashbackService
import br.com.leodevel.testgb.service.DealerService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/dealer")
@Api(tags = ["Revendedor"], description = "Revendedor")
class DealerController(private val dealerService: DealerService,
                       private val cashbackService: CashbackService
) {

    @PostMapping
    @ApiOperation("Insere um novo revendedor")
    fun create(@Valid @RequestBody body: DealerRequestDTO): ApiResponseDTO<DealerResponseDTO> {
        return ApiResponseDTO(data = dealerService.save(body))
    }

    @PutMapping
    @ApiOperation("Atualiza os dados de um revendedor")
    @ApiImplicitParams(
        value = [
            ApiImplicitParam(name = "Authorization", required = true, dataType = "string", paramType = "header")
        ]
    )
    fun update(@Valid @RequestBody body: DealerRequestDTO): ApiResponseDTO<DealerResponseDTO> {
        return ApiResponseDTO(data = dealerService.save(body))
    }

    @ApiOperation("Altera a senha do revendedor")
    @PutMapping("/update-password")
    @ApiImplicitParams(
        value = [
            ApiImplicitParam(name = "Authorization", required = true, dataType = "string", paramType = "header")
        ]
    )
    fun updatePassword(@Valid @RequestBody body: UpdatePasswordRequestDTO): ApiResponseDTO<Any> {
        dealerService.updatePassword(body)
        return ApiResponseDTO()
    }

    @PostMapping("/accumulated-cashback")
    @ApiOperation("Consulta o cashback acumulado do revendedor")
    @ApiImplicitParams(
        value = [
            ApiImplicitParam(name = "Authorization", required = true, dataType = "string", paramType = "header")
        ]
    )
    fun getAccumulatedCashback(@RequestParam cpf: String): ApiResponseDTO<CashbackBodyResponseDTO> {
        return ApiResponseDTO(
            data = cashbackService.getAccumulated(cpf)
        )
    }

}