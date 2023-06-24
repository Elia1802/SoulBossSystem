package de.elia;

import de.elia.api.annotation.Planned;
import io.papermc.paper.plugin.loader.PluginClasspathBuilder;
import io.papermc.paper.plugin.loader.PluginLoader;
import io.papermc.paper.plugin.loader.library.ClassPathLibrary;
import io.papermc.paper.plugin.loader.library.impl.JarLibrary;
import io.papermc.paper.plugin.loader.library.impl.MavenLibraryResolver;
import java.io.File;
import java.nio.file.Path;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.aether.repository.RemoteRepository;
import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @description This is the loader of this Plugin.
 */
@Planned(value="See comment in paper.yml")
public class Loader
  implements PluginLoader {
  private void generatePathName() {
    String projectPath = System.getProperty("user.dir");
    String existPath = projectPath + "/plugins/SoulBossSystem/";
    File file = new File(existPath, "libraries/");
    if (!file.exists()) {
      file.mkdir();
    }
  }

  public void classloader(@NotNull PluginClasspathBuilder classpathBuilder) {
    this.generatePathName();
    MavenLibraryResolver worldedit = new MavenLibraryResolver();
    worldedit.addDependency(new Dependency((Artifact)new DefaultArtifact("com.sk89q.worldedit:worldedit-bukkit:7.2.16-SNAPSHOT"), null));
    worldedit.addRepository(new RemoteRepository.Builder("sk89q-repo", "default", "https://maven.enginehub.org/repo/").build());
    classpathBuilder.addLibrary((ClassPathLibrary)worldedit);
    MavenLibraryResolver paperlib = new MavenLibraryResolver();
    paperlib.addDependency(new Dependency((Artifact)new DefaultArtifact("io.papermc:paperlib:1.0.7"), null));
    paperlib.addRepository(new RemoteRepository.Builder("papermc", "default", "https://papermc.io/repo/repository/maven-public/").build());
    classpathBuilder.addLibrary((ClassPathLibrary)paperlib);
    String projectPath = System.getProperty("user.dir");
    String libraryPath = projectPath + "/plugins/SoulBossSystem/libraries/";
    classpathBuilder.addLibrary((ClassPathLibrary)new JarLibrary(Path.of(libraryPath + "SoullLibrary-2.0.1.jar", new String[0])));
  }
}
