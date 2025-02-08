package com.github.com.nikolai.bukkit.core.loaders.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassScanner {

    public static List<Class<?>> fetchClassesFromJar(File jarFile, String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        try (JarFile jar = new JarFile(jarFile)) {
            String packagePath = packageName.replace('.', '/');
            Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String entryName = entry.getName();
                if (entryName.endsWith(".class") && entryName.startsWith(packagePath)) {
                    String className = entryName.replace('/', '.').replace(".class", "");
                    classes.add(loadClass(className));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load classes from JAR: " + jarFile.getAbsolutePath(), e);
        }
        return classes;
    }

    public static List<Class<?>> fetchClassesFromPackage(Object instance, String packageName) {
        CodeSource source = instance.getClass().getProtectionDomain().getCodeSource();
        List<Class<?>> classes = new ArrayList<>();
        if (source != null) {
            scanJarForClasses(source.getLocation(), packageName, classes);
        }
        return classes;
    }

    private static Class<?> loadClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load class: " + className, e);
        }
    }

    private static void scanJarForClasses(URL resource, String packageName, List<Class<?>> classes) {
        String packagePath = packageName.replace('.', '/');
        String jarPath = resource.getPath().replace("%20", " ").replaceFirst("[.]jar[!].*", ".jar").replaceFirst("file:", "");
        try (JarFile jar = new JarFile(jarPath)) {
            Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String entryName = entry.getName();
                if (entryName.endsWith(".class") && entryName.startsWith(packagePath)) {
                    String className = entryName.replace('/', '.').replace(".class", "");
                    classes.add(loadClass(className));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JAR: " + jarPath, e);
        }
    }
}
