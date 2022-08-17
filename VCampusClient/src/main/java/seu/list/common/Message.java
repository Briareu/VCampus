//package VCampusClient.src.main.java.seu.list.common;
package seu.list.common;

import java.io.Serializable;
//与Client、GUI进行交互的过程中需要用到Message对象进行信息的传递
import java.util.Vector;


public class Message implements Serializable {
  private static final long serialVersionUID = 50000L;
  
  private Vector<String> Content;
  private Object data;
  private String messageType;
  private String moduleType;
  
  
  private int userType;//0-学生 1-管理员
  private String userId;
  private String extraMessage;
  private String errorMessage;
  private boolean lastOperState;

  public Object getData() {
      return data;
  }
  public void setData(Object data) {
      this.data = data;
  }
  public boolean isLastOperState() {
      return lastOperState;
  }
  public void setLastOperState(boolean lastOperState) {
      this.lastOperState = lastOperState;
  }
  public String getMessageType() {
      return messageType;
  }
  public void setMessageType(String messageType) {
      this.messageType = messageType;
  }
  public String getExtraMessage() { return extraMessage; }
  public void setExtraMessage(String extraMessage) { this.extraMessage = extraMessage; }
  public String getUserId() {
      return userId;
  }
  public void setUserId(String userId) {
      this.userId = userId;
  }
  public String getErrorMessage() {
      return errorMessage;
  }
  public void setErrorMessage(String errorMessage) {
      this.errorMessage = errorMessage;
  }
  public int getUserType() {
      return userType;
  }
  public void setUserType(int userType) {
      this.userType = userType;
  }
  public String getModuleType() {
	  return this.moduleType;
  }
  public void setModuleType(String moduleType) {
	  this.moduleType = moduleType;
  }
  public Vector<String> getContent() {
      return Content;
  }
  public void setContent(Vector<String> content) {
      Content = content;
  }
}