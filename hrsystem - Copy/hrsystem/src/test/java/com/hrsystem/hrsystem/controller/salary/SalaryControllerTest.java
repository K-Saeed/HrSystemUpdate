package com.hrsystem.hrsystem.controller.salary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ExtendWith({SpringExtension.class})
@ActiveProfiles("test")
@AutoConfigureTestDatabase
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public class SalaryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper ;

    @Test
    @DatabaseSetup("/DBUnit.dataset/SalaryHistory/dataset.xml")
    public  void getSalaryHistoryTest () throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/salary/salary/history/20")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", Matchers.isA(ArrayList.class)))
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)));
    }

    @Test
    @DatabaseSetup ("/DBUnit.dataset/RecordBonus/dataset1.xml")
    public void recordEmployeeBonus () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/salary/record/bonus/20")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.bonus").value(10000));
    }
    @Test
    @DatabaseSetup ("/DBUnit.dataset/RecordBonus/dataset2.xml")
    public void recordEmployeeBonus2 () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/salary/record/bonus/20")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.bonus").value(10000));
    }
    @Test
    @DatabaseSetup ("/DBUnit.dataset/RecordBonus/dataset2.xml")
    public void recordEmployeeRaises () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/salary/record/raises/20")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.raises").value(12000));
    }
    @Test
    @DatabaseSetup ("/DBUnit.dataset/CalculateSalary/dataset.xml")
    public void getEmployeesSalary () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/salary/all")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[2].netSalary").value(2050.0));
    }



}