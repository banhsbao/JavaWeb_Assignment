package baohc.listener;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Web application lifecycle listener.
 *
 * @author Admin
 */
public class ContextListener implements ServletContextListener {

    private final String MAPPING_FILE = "/WEB-INF/mapping.txt";
    private final String MEMBER_FUNCTION = "/WEB-INF/memberfunc.txt";

    private Map<String, String> readMapping(String file) {
        FileReader f = null;
        BufferedReader bf = null;
        Map<String, String> mapping = null;
        try {
            f = new FileReader(file);
            bf = new BufferedReader(f);
            while (bf.ready()) {
                String[] read = bf.readLine().split("=");
                if (read.length == 2) {
                    if (mapping == null) {
                        mapping = new HashMap<>();
                    }
                    mapping.put(read[0], read[1]);
                }
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException e) {
        } finally {
            try {
                if (bf != null) {
                    bf.close();
                }
                if (f != null) {
                    f.close();
                }
            } catch (IOException e) {
            }
        }
        return mapping;
    }

    private List<String> readMemberFuntion(String file) {
        FileReader f = null;
        BufferedReader bf = null;
        List<String> listfunction = null;
        try {
            f = new FileReader(file);
            bf = new BufferedReader(f);
            while (bf.ready()) {
                if (listfunction == null) {
                    listfunction = new ArrayList<>();
                }
                listfunction.add(bf.readLine());
            }
        } catch (FileNotFoundException ex) {

        } catch (IOException e) {

        }
        return listfunction;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String realPath = context.getRealPath("/");
        Map<String, String> mapping = readMapping(realPath + MAPPING_FILE);
        if (mapping != null) {
            context.setAttribute("MAPPING", mapping);
        }
        List<String> functionMember = readMemberFuntion(realPath + MEMBER_FUNCTION);
        if (functionMember != null) {
            context.setAttribute("FUNCTION_MEMBER", functionMember);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
