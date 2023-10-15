package org.example.crudprogramveb.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class NotesMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer { //класс-инициализатор наследуется от AbstractAnnotationConfigDispatcherServletInitializer

    @Override
    protected Class<?>[] getRootConfigClasses() { //метод возвращает конфигурационные классы, загруженные при запуске приложения
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() { //метод возвращает конфигурационные классы, определяющие для настройки обработки http-запросов
        return new Class[] {NotesConfig.class}; //возвращает класс, содержащий такие настройки
    }

    @Override
    protected String[] getServletMappings() { //метод определяет, какие url будут обрабатываться DispatcherServlet
        return new String[] {"/"}; //указывает, что все http-запросы обрабатываются им
    }

    @Override
    public void onStartup(ServletContext aServletContext) throws ServletException { // фильтр, работающий со скрытым полем
        super.onStartup(aServletContext);
        registerHiddenFieldFilter(aServletContext);
    }

    private void registerHiddenFieldFilter(ServletContext aContext) {
        aContext.addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null, true, "/*");
    }
}
