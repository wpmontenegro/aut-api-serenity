package com.api.utils;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.api.utils.Constants.JSON_FORMAT;
import static com.api.utils.Constants.TEMPLATES_PATH;

public class TemplateUtil {

    private static Configuration configuration = null;

    /**
     * The template file should be in the "src/test/resources/templates" directory.
     */
    private static Template getTemplate(String templateFile) {
        try {
            if (configuration == null) {
                configuration = new Configuration(Configuration.VERSION_2_3_28);
                configuration.setDefaultEncoding("UTF-8");
                configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

                MultiTemplateLoader multiTemplateLoader = createRecursiveTemplateLoader(TEMPLATES_PATH);
                configuration.setTemplateLoader(multiTemplateLoader);
            }
            return configuration.getTemplate(String.format(JSON_FORMAT, templateFile.toLowerCase()));
        } catch (Exception e) {
            throw new IllegalStateException("Could not find template " + templateFile, e);
        }
    }

    public static String mergeWithFieldsFrom(String templateFile, Map<String, Object> fieldValues) {
        Template template = getTemplate(templateFile);
        Writer writer = new StringWriter();
        try {
            template.process(fieldValues, writer);
        } catch (TemplateException | IOException e) {
            throw new IllegalStateException("Failed to merge test data template", e);
        }
        return writer.toString();
    }

    private static MultiTemplateLoader createRecursiveTemplateLoader(String basePath) throws IOException {
        List<FileTemplateLoader> fileTemplateLoaders = new ArrayList<>();
        List<File> templateFiles = findTemplateFiles(new File(basePath));
        for (File templateFile : templateFiles) {
            fileTemplateLoaders.add(new FileTemplateLoader(templateFile.getParentFile()));
        }
        return new MultiTemplateLoader(fileTemplateLoaders.toArray(new FileTemplateLoader[0]));
    }

    private static List<File> findTemplateFiles(File baseDir) {
        List<File> templateFiles = new ArrayList<>();
        File[] files = baseDir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    templateFiles.addAll(findTemplateFiles(file));
                } else {
                    templateFiles.add(file);
                }
            }
        }
        return templateFiles;
    }
}