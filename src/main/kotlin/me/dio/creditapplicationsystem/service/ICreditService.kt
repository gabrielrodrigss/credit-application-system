package me.dio.creditapplicationsystem.service

import me.dio.creditapplicationsystem.entity.Credit
import java.util.UUID

interface ICreditService {
    fun save(credit: Credit): Credit
    fun findAllByCustomer(customerId: Long): List<Credit> // Lista todas os credito feito pelo o cliente atravé do id do customer.
    fun findByCreditCode(customerId: Long, creditCode: UUID): Credit // Encontra um crédito de acordo com o UUID do crédito
}