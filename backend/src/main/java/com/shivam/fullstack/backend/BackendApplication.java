package com.shivam.fullstack.backend;

import com.shivam.fullstack.backend.Exception.MyException;
import com.shivam.fullstack.backend.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

public static Utility utility=new Utility();

    public static void setUtility(Utility utility) {
        BackendApplication.utility = utility;
    }

    public static Utility getUtility() {
        return utility;
    }

    public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);


	}

}
