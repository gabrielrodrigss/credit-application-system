package me.dio.creditapplicationsystem.service

import me.dio.creditapplicationsystem.entity.Credit
import me.dio.creditapplicationsystem.entity.Customer
import me.dio.creditapplicationsystem.exception.BusinessException
import me.dio.creditapplicationsystem.repository.CreditRepository
import me.dio.creditapplicationsystem.service.impl.CreditService
import me.dio.creditapplicationsystem.service.impl.CustomerService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.lang.IllegalArgumentException
import java.util.*

class CreditServiceTest {

    private lateinit var creditRepository: CreditRepository
    private lateinit var customerService: CustomerService
    private lateinit var creditService: CreditService

    @BeforeEach
    fun setUp() {
        creditRepository = Mockito.mock(CreditRepository::class.java)
        customerService = Mockito.mock(CustomerService::class.java)
        creditService = CreditService(creditRepository, customerService)
    }

    @Test
    fun testSave() {
        val customerId = 1L
        val creditId = 1L
        val customer = Customer(id = customerId, firstName = "John Doe")
        val credit = Credit(id = creditId, customer = customer, creditCode = UUID.randomUUID())

        Mockito.`when`(customerService.findById(customerId)).thenReturn(customer)
        Mockito.`when`(creditRepository.save(credit)).thenReturn(credit)

        val savedCredit = creditService.save(credit)

        Assertions.assertEquals(customer, savedCredit.customer)
        Mockito.verify(customerService, Mockito.times(1)).findById(customerId)
        Mockito.verify(creditRepository, Mockito.times(1)).save(credit)
    }

    @Test
    fun testFindAllByCustomer() {
        val customerId = 1L
        val credits = listOf(Credit(id = 1L), Credit(id = 2L))

        Mockito.`when`(creditRepository.findAllByCustomerId(customerId)).thenReturn(credits)

        val result = creditService.findAllByCustomer(customerId)

        Assertions.assertEquals(credits, result)
        Mockito.verify(creditRepository, Mockito.times(1)).findAllByCustomerId(customerId)
    }

    @Test
    fun testFindByCreditCode() {
        val customerId = 1L
        val creditCode = UUID.randomUUID()
        val credit = Credit(id = 1L, customer = Customer(id = customerId), creditCode = creditCode)

        Mockito.`when`(creditRepository.findByCreditCode(creditCode)).thenReturn(credit)

        val result = creditService.findByCreditCode(customerId, creditCode)

        Assertions.assertEquals(credit, result)
        Mockito.verify(creditRepository, Mockito.times(1)).findByCreditCode(creditCode)
    }

    @Test
    fun testFindByCreditCode_InvalidCustomerId() {
        val customerId = 2L
        val creditCode = UUID.randomUUID()
        val credit = Credit(id = 1L, customer = Customer(id = 1L), creditCode = creditCode)

        Mockito.`when`(creditRepository.findByCreditCode(creditCode)).thenReturn(credit)

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            creditService.findByCreditCode(customerId, creditCode)
        }

        Mockito.verify(creditRepository, Mockito.times(1)).findByCreditCode(creditCode)
    }

    @Test
    fun testFindByCreditCode_CreditNotFound() {
        val customerId = 1L
        val creditCode = UUID.randomUUID()

        Mockito.`when`(creditRepository.findByCreditCode(creditCode)).thenReturn(null)

        Assertions.assertThrows(BusinessException::class.java) {
            creditService
        }
    }
}