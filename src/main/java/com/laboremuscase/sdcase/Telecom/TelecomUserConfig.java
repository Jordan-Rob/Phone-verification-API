package com.laboremuscase.sdcase.Telecom;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TelecomUserConfig {
    
    @Bean
    CommandLineRunner commandLineRunner(TelecomUserRepository repository) {

        return args -> {
            TelecomUser sebastian = new TelecomUser(
                "Sebastian",
                "Mark",
                "Mwangi",
                "MALE",
                "0786543222",
                "CM123899JK098",
                "Active",
                "e456w899-d543-22qw-7f90-2321fg390006",
                null,
                null,
                null,
                LocalDateTime.now(),
                null
            );
            
            TelecomUser chris = new TelecomUser(
                "Chris",
                "Micheal",
                "Mubiru",
                "MALE",
                "0778902311",
                "CM993899JKL",
                "Active",
                "f456w233-e455-67ew-7f90-2321fg390006",
                null,
                null,
                null,
                LocalDateTime.now(),
                null
            );
            TelecomUser joseph = new TelecomUser(
                "Joseph",
                null,
                "Ochen",
                "MALE",
                "0750144322",
                "FH234449LC98",
                "Active",
                "d665n389-o543-45cv-7f34-3445sq390006",
                null,
                null,
                null,
                LocalDateTime.now(),
                null
            );
            TelecomUser lucy = new TelecomUser(
                "Lucy",
                "Mutesi",
                "Mugisha",
                "FEMALE",
                "0765339804",
                "CM478890LC98",
                "Active",
                "q244p567-m344-68fw-7f90-2321fg390006",
                null,
                null,
                null,
                LocalDateTime.now(),
                null
            );
            TelecomUser zoe = new TelecomUser(
                "Zoe",
                "Christine",
                "Zalwango",
                "FEMALE",
                "0778890012",
                "CM67778900LCC3",
                "Active",
                "h689l088-e543-44kn-7k90-2321fg390006",
                null,
                null,
                null,
                LocalDateTime.now(),
                null
            );
            TelecomUser thelma = new TelecomUser(
                "Thelma",
                "Michelle",
                "Agwang",
                "FEMALE",
                "0708992222",
                "CM100999876LC23",
                "Active",
                "f677g889-s455-78ww-8u80-2321fg390006",
                null,
                null,
                null,
                LocalDateTime.now(),
                null
            );

            repository.saveAll(
                List.of(sebastian, chris, joseph, lucy, zoe, thelma)
            );

        };
    }
}
