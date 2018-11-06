package main.java.com.github.muhin007.coldplacespring;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class PageGenerator {
    private static final Logger log = Logger.getLogger(PageGenerator.class);
    private static final String HTML_DIR = "templates";

    private static PageGenerator pageGenerator;
    private final Configuration cfg;


    public static PageGenerator instance() {
        if (pageGenerator == null)
            pageGenerator = new PageGenerator();
        return pageGenerator;
    }

    public String getPage(String filename, Map<String, Object> data) {
        Writer stream = new StringWriter();
        Template template;
        String errorTextForUser = "Произошла ошибка. Мы уже в курсе. Перезагрузите, пожалуйста, страницу позднее.";

        try {
            template = cfg.getTemplate(HTML_DIR + File.separator + filename);
        } catch (IOException e) {
            log.error("Не удалось получить Template " + filename, e);
            return errorTextForUser;
        }

        try {
            template.process(data, stream);
        } catch (TemplateException | IOException e) {
            log.error("Не удалось сгенерировать страницу на основе Template " + filename, e);
            return errorTextForUser;
        }


        return stream.toString();
    }

    private PageGenerator() {
        cfg = new Configuration();
    }
}
