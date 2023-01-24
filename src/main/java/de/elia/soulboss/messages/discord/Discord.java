package de.elia.soulboss.messages.discord;

import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description This is the Discord record to send a Message to a Discord channel on a Discord Server.
 */
public class Discord {

  private final URL url;

  public Discord(URL url){
    this.url = url;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This Methode send a message in a Discord channel.
   * @param message Requires the Message
   */
  public void message(String message) {
    try {
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("content", message);
      HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
      connection.addRequestProperty("Content-Type", "application/json");
      connection.addRequestProperty("User-Agent", "Java-DiscordWebhook");
      connection.setDoOutput(true);
      connection.setRequestMethod("POST");
      OutputStream outputStream = connection.getOutputStream();
      outputStream.write(jsonObject.toJSONString().getBytes());
      outputStream.flush();
      outputStream.close();
      connection.getInputStream().close();
      connection.disconnect();
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the URL
   * @return The URL of the Webhook.
   */
  @NotNull
  public URL url() {
    return url;
  }
}
