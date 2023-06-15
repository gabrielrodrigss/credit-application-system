//package me.dio.creditapplicationsystem.controller
//
//import com.fasterxml.jackson.databind.ObjectMapper
//import me.dio.creditapplicationsystem.dto.CreditDto
//import me.dio.creditapplicationsystem.dto.CreditView
//import me.dio.creditapplicationsystem.dto.CreditViewList
//import me.dio.creditapplicationsystem.entity.Credit
//import me.dio.creditapplicationsystem.service.impl.CreditService
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import org.mockito.Mockito
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
//import org.springframework.boot.test.mock.mockito.MockBean
//import org.springframework.http.MediaType
//import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
//import org.springframework.test.web.servlet.MockMvc
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers
//import java.math.BigDecimal
//import java.time.LocalDate
//import java.util.UUID
//
//@SpringJUnitConfig
//@WebMvcTest(CreditResource::class)
//class CreditResourceTest {
//
//    @Autowired
//    private lateinit var mockMvc: MockMvc
//
//    @Autowired
//    private lateinit var objectMapper: ObjectMapper
//
//    @MockBean
//    private lateinit var creditService: CreditService
//
//    private val customerId = 1L
//    private val creditCode = UUID.randomUUID()
//
//    @BeforeEach
//    fun setUp() {
//        // Configurar comportamentos do mock CreditService aqui, se necessário
//    }
//
//    @Test
//    fun testSaveCredit() {
//        /* Preencher com os valores necessários para o DTO */
//        val creditDto = CreditDto()
//        val credit = Credit(/* Preencher com os valores necessários para o objeto Credit */)
//
//        Mockito.`when`(creditService.save(Mockito.any(Credit::class.java))).thenReturn(credit)
//
//        val request = MockMvcRequestBuilders.post("/api/credits")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(objectMapper.writeValueAsString(creditDto))
//
//        mockMvc.perform(request)
//            .andExpect(MockMvcResultMatchers.status().isCreated)
//            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(credit.id)) // Verificar o valor do ID retornado
//    }
//
//    @Test
//    fun testFindAllByCustomerId() {
//        val creditViewList = listOf(CreditViewList(/* Preencher com os valores necessários para o objeto CreditViewList */))
//
//        Mockito.`when`(creditService.findAllByCustomer(customerId)).thenReturn(creditViewList)
//
//        val request = MockMvcRequestBuilders.get("/api/credits")
//            .param("customerId", customerId.toString())
//
//        mockMvc.perform(request)
//            .andExpect(MockMvcResultMatchers.status().isOk)
//            .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(creditViewList.size))
//    }
//
//    @Test
//    fun testFindByCreditCode() {
//        val creditView = CreditView(/* Preencher com os valores necessários para o objeto CreditView */)
//
//        Mockito.`when`(creditService.findByCreditCode(customerId, creditCode)).thenReturn(creditView)
//
//        val request = MockMvcRequestBuilders.get("/api/credits/{creditCode}", creditCode)
//            .param("customerId", customerId.toString())
//
//        mockMvc.perform(request)
//            .andExpect(MockMvcResultMatchers.status().isOk)
//            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(creditView.id)) // Verificar o valor do ID retornado
//    }
//
//    private fun builderCreditDto(
//        creditValue: String = "100",
//        dayFirstOfInstallment: String = "2025-03-18",
//        numberOfInstallments: Int = 8,
//        customerId: Long
//    ) = CreditDto(
//        creditValue = creditValue,
//        dayFirstOfInstallment = dayFirstOfInstallment,
//        numberOfInstallments = numberOfInstallments,
//        customerId = customerId
//    )
//}
