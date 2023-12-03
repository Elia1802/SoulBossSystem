package de.elia;

import io.papermc.paper.plugin.loader.PluginClasspathBuilder;
import io.papermc.paper.plugin.loader.PluginLoader;
import io.papermc.paper.plugin.loader.library.impl.JarLibrary;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public class Loader implements PluginLoader {

  public void classloader(@NotNull PluginClasspathBuilder classpathBuilder) {
    String projectPath = System.getProperty("user.dir");
    String libraryPath = projectPath + "/plugins/";
    classpathBuilder.addLibrary(new JarLibrary(Path.of(libraryPath + "SoullLibrary-3.0.0.jar")));
  }
}
