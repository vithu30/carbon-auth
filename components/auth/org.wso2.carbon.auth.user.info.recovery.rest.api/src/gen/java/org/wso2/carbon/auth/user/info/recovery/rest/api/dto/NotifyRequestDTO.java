package org.wso2.carbon.auth.user.info.recovery.rest.api.dto;


import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

/**
 * NotifyRequestDTO
 */
public class NotifyRequestDTO   {
  @SerializedName("confirmationCode")
  private String confirmationCode = null;

  @SerializedName("username")
  private String username = null;

  @SerializedName("host")
  private String host = null;

  @SerializedName("port")
  private String port = null;

  @SerializedName("emailUsername")
  private String emailUsername = null;

  @SerializedName("emailPassword")
  private String emailPassword = null;

  @SerializedName("from")
  private String from = null;

  @SerializedName("to")
  private String to = null;

  public NotifyRequestDTO confirmationCode(String confirmationCode) {
    this.confirmationCode = confirmationCode;
    return this;
  }

   /**
   * Get confirmationCode
   * @return confirmationCode
  **/
  @ApiModelProperty(value = "")
  public String getConfirmationCode() {
    return confirmationCode;
  }

  public void setConfirmationCode(String confirmationCode) {
    this.confirmationCode = confirmationCode;
  }

  public NotifyRequestDTO username(String username) {
    this.username = username;
    return this;
  }

   /**
   * Get username
   * @return username
  **/
  @ApiModelProperty(value = "")
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public NotifyRequestDTO host(String host) {
    this.host = host;
    return this;
  }

   /**
   * Get host
   * @return host
  **/
  @ApiModelProperty(value = "")
  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public NotifyRequestDTO port(String port) {
    this.port = port;
    return this;
  }

   /**
   * Get port
   * @return port
  **/
  @ApiModelProperty(value = "")
  public String getPort() {
    return port;
  }

  public void setPort(String port) {
    this.port = port;
  }

  public NotifyRequestDTO emailUsername(String emailUsername) {
    this.emailUsername = emailUsername;
    return this;
  }

   /**
   * Get emailUsername
   * @return emailUsername
  **/
  @ApiModelProperty(value = "")
  public String getEmailUsername() {
    return emailUsername;
  }

  public void setEmailUsername(String emailUsername) {
    this.emailUsername = emailUsername;
  }

  public NotifyRequestDTO emailPassword(String emailPassword) {
    this.emailPassword = emailPassword;
    return this;
  }

   /**
   * Get emailPassword
   * @return emailPassword
  **/
  @ApiModelProperty(value = "")
  public String getEmailPassword() {
    return emailPassword;
  }

  public void setEmailPassword(String emailPassword) {
    this.emailPassword = emailPassword;
  }

  public NotifyRequestDTO from(String from) {
    this.from = from;
    return this;
  }

   /**
   * Get from
   * @return from
  **/
  @ApiModelProperty(value = "")
  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public NotifyRequestDTO to(String to) {
    this.to = to;
    return this;
  }

   /**
   * Get to
   * @return to
  **/
  @ApiModelProperty(value = "")
  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NotifyRequestDTO notifyRequest = (NotifyRequestDTO) o;
    return Objects.equals(this.confirmationCode, notifyRequest.confirmationCode) &&
        Objects.equals(this.username, notifyRequest.username) &&
        Objects.equals(this.host, notifyRequest.host) &&
        Objects.equals(this.port, notifyRequest.port) &&
        Objects.equals(this.emailUsername, notifyRequest.emailUsername) &&
        Objects.equals(this.emailPassword, notifyRequest.emailPassword) &&
        Objects.equals(this.from, notifyRequest.from) &&
        Objects.equals(this.to, notifyRequest.to);
  }

  @Override
  public int hashCode() {
    return Objects.hash(confirmationCode, username, host, port, emailUsername, emailPassword, from, to);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NotifyRequestDTO {\n");
    
    sb.append("    confirmationCode: ").append(toIndentedString(confirmationCode)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    host: ").append(toIndentedString(host)).append("\n");
    sb.append("    port: ").append(toIndentedString(port)).append("\n");
    sb.append("    emailUsername: ").append(toIndentedString(emailUsername)).append("\n");
    sb.append("    emailPassword: ").append(toIndentedString(emailPassword)).append("\n");
    sb.append("    from: ").append(toIndentedString(from)).append("\n");
    sb.append("    to: ").append(toIndentedString(to)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

