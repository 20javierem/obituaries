package com.moreno.obituaries;

import com.moreno.obituaries.ui.frames.Login;
import com.moreno.obituaries.utilities.Properties;
import com.moreno.obituaries.utilities.Utilities;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        Hibernate.initialize();
        Utilities.loadTheme();
        Login login=new Login();
        login.setVisible(true);
    }
}
