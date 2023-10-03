package ibmec.ap1.ap1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
    
@Configuration
public class ApiDocumentationConfiguration { 

    @Bean
    public OpenAPI apiDocConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("Ap1")
                        .description("Apresentação ap1")
                        .version("1.0")
                        .contact(new Contact()
                                .name("João Pedro")
                                .email("jpsa.alencar@hotmail.com")));
    }
}