package com.hrsystem.hrsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.hrsystem.hrsystem.entity.command.InsuranceCommand;
import com.hrsystem.hrsystem.entity.command.LeavesCommand;
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
    SalaryController salaryController ;
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
    @DatabaseSetup ("/DBUnit.dataset/EmployeeRecordLeaves/dataset.xml")
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT,
            value = "/DBUnit.dataset/EmployeeRecordLeaves/expected.xml")
    public void recordEmployeeLeaves () throws Exception {
        LeavesCommand leavesCommand = new LeavesCommand (20);
        mockMvc.perform(MockMvcRequestBuilders.put("/salary/record/leaves/20")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(leavesCommand)))
                .andExpect(jsonPath("$.leaves").value(0));
    }
    @Test
    @DatabaseSetup ("/DBUnit.dataset/Insurance/dataset.xml")
    @ExpectedDatabase (assertionMode = DatabaseAssertionMode.NON_STRICT,
            value = "/DBUnit.dataset/Insurance/expected.xml")
    public void setEmployeeLeaves () throws Exception {
        InsuranceCommand insuranceCommand = new InsuranceCommand(21,7,20);
        mockMvc.perform(MockMvcRequestBuilders.post("/salary/set/leaves/insurance")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(insuranceCommand)))
                .andExpect(jsonPath("$.leavesYear").value(21))
                .andExpect(jsonPath("$.insuranceYears").value(7))
                .andExpect(jsonPath("$.employeeId").value(20));
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