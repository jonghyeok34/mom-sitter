package com.company.app.users;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.company.app.ControllerExceptionHandler;
import com.company.app.common.codes.GenderTypes;
import com.company.app.common.codes.UserTypes;
import com.company.app.users.model.dto.ChildInfoRequestDto;
import com.company.app.users.model.dto.SignUpRequestDto;
import com.company.app.users.repository.UserModelRepository;
import com.company.app.users.services.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class UsersControllerTests {

 
    @InjectMocks
    private UsersController usersController;

    @Mock
    private UsersService usersService;

    @Mock
    private UserModelRepository userModelRepository;
    
    
    
    private ObjectWriter ow;
    private MockMvc mockMvc;
    @BeforeEach
    public void init(){
        // mock mvc & exception handler
        mockMvc = MockMvcBuilders.standaloneSetup(usersController)
            .setControllerAdvice(new ControllerExceptionHandler())
            .build();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ow = mapper.writer().withDefaultPrettyPrinter();
    }
    SignUpRequestDto parentSignUpForm(){
        
        SignUpRequestDto form = new SignUpRequestDto();
        form.setName("부모이름");
        form.setUserId("tester"); 
        form.setEmail("test@gmail.co.kr");
        form.setPassword("test11");
        form.setUserType(UserTypes.PARENT.name());
        List<ChildInfoRequestDto> kidsInfo = Arrays.asList(
            ChildInfoRequestDto.builder().birthDate(LocalDate.now()).gender(GenderTypes.FEMALE.name()).build()
        );
        form.setKidsInfo(kidsInfo);
        return form;
    }

    


}
