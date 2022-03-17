package com.iucosoft.beautysalon.fileservice;

import com.iucosoft.beautysalon.models.User;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Rusanovschi
 */
public interface UserFileServiceIntf {
    
    //scrie
    void scrie(List<User> lista, String fileName) throws IOException;
    //citeste
    List<User> citeste(String fileName) throws IOException;
    //creaza
    void creaza(List<User> lista, String fileName) throws IOException;
    
}
