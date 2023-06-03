package me.dio.creditapplicationsystem.service.impl

import me.dio.creditapplicationsystem.entity.Customer
import me.dio.creditapplicationsystem.repository.CustomerRepository
import me.dio.creditapplicationsystem.service.ICustomerService
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
): ICustomerService {
    override fun save(customer: Customer): Customer =
        this.customerRepository.save(customer)

    // Caso não encontre o optional de um customer através do id, será lançado a exception.
    override fun findById(id: Long): Customer = this.customerRepository.findById(id).orElseThrow(){
            throw RuntimeException("id $id not found")
        }

    override fun delete(id: Long) =
        this.customerRepository.deleteById(id)

}