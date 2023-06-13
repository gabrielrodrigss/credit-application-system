package me.dio.creditapplicationsystem.service.impl

import me.dio.creditapplicationsystem.entity.Credit
import me.dio.creditapplicationsystem.exception.BusinessException
import me.dio.creditapplicationsystem.repository.CreditRepository
import me.dio.creditapplicationsystem.service.ICreditService
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException
import java.lang.RuntimeException
import java.util.*

@Service
class CreditService(
    private val creditRepository: CreditRepository,
    private val customerService: CustomerService
): ICreditService {
    override fun save(credit: Credit): Credit {
        // Verificação da existência do customer do credito que está sendo salvo no db
        credit.apply {
            customer = customerService.findById(credit.customer?.id!!)
        }
        return this.creditRepository.save(credit)
    }

    // Retorna uma lista de crédito de um determinado cliente através do id do cliente
    override fun findAllByCustomer(customerId: Long): List<Credit> =
        this.creditRepository.findAllByCustomerId(customerId)

    // Retorna um dos crédito code de um determinado cliente e o seu detalhamento do db.
    // Compara o id do customer que ta solicitando o acesso ao crédito se é igual ao id do customer que está vinculado
    // a esse crédito com o Named Queries
    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
        val credit: Credit = (this.creditRepository.findByCreditCode(creditCode)
            ?: throw BusinessException("CreditCo $creditCode not found"))
        // Comparação do id.
        return if (credit.customer?.id == customerId) credit else throw IllegalArgumentException("Contact admin")
    }

}