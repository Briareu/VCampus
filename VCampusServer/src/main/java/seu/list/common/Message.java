package seu.list.common;

import java.io.Serializable;
import java.util.Vector;
//与Client、GUI进行交互的过程中需要用到Message对象进行信息的传递

/**
 * 类{@code Message}为客户端与服务端进行通信时交换的数据类型 <br>
 * 包含了存放的数据{@code Object data}, 以及各类用于消息处理的辅助信息 <br>
 * 该类可序列化 
 * @author 吴慕陶 柳多荣
 * @version 1.0
 * @see java.io.Serializable
 * @see seu.list.common.MessageType
 * @see seu.list.common.ModuleType
 */
public class Message implements Serializable {
      private static final long serialVersionUID = 50000L;

      public Vector<String> Content = null;
      private Object data = null;
      private String messageType = null;
      private String moduleType = null;
      private boolean isOffline = false;
      private boolean isSeccess=false;
      private int userType;//0-学生 1-管理员
      private String userId;
      private String extraMessage;
      private String errorMessage;
      private boolean lastOperState;

      /**
       * 将该条消息设置为下线消息
	   * @author 吴慕陶 
	   * @version 1.0
       */
      public void offline() {
    	  this.isOffline = true;
      }

      /**
       * 读取{@code isOffline}字段, 判断是否下线
       * @return 消息中的{@code isOffline}字段
       * @author 吴慕陶 
	   * @version 1.0
       */
      public boolean isOffline() {
    	  return isOffline;
      }
      
      public boolean isSeccess() {
    	  return isSeccess;
      }

      public void setSeccess(boolean seccess) {
    	  isSeccess = seccess;
      }

      /**
       * 将需要传输的数据存入{@code Message}类
       * @param data 任意类型的数据
       * @author 吴慕陶 
	   * @version 1.0
       */
      public void setData(Object data) {
          this.data = data;
      }
      
      /**
       * 读取消息中的数据字段
       * @return 消息中的{@code data}字段
       * @author 吴慕陶 
	   * @version 1.0
       */
      public Object getData() {
          return data;
      }
      
      public boolean isLastOperState() {
          return lastOperState;
      }
      public void setLastOperState(boolean lastOperState) {
          this.lastOperState = lastOperState;
      }
      
      /**
       * 读取消息中的模块类型字段
       * @return 消息中的{@code moduleType}字段
       * @author 吴慕陶 
	   * @version 1.0
	   * @see seu.list.common.ModuleType
       */
      public String getModuleType() {
          return this.moduleType;
      }
      
      /**
       * 设定消息的模块类型
       * @param moduleType 该消息的模块类型
       * @author 吴慕陶 
	   * @version 1.0
	   * @see seu.list.common.ModuleType
       */
      public void setModuleType(String moduleType) {
          this.moduleType = moduleType;
      }
      
      /**
       * 读取消息的操作类型
       * @return 消息中的{@code messageType}字段
       * @author 吴慕陶 
	   * @version 1.0
	   * @see seu.list.common.MessageType
       */
      public String getMessageType() {
          return messageType;
      }
      
      /**
       * 设定消息的操作类型
       * @param messageType 消息的操作类型
       * @author 吴慕陶 
	   * @version 1.0
	   * @see seu.list.common.MessageType
       */
      public void setMessageType(String messageType) {
          this.messageType = messageType;
      }
      
      public String getExtraMessage() { 
    	  return extraMessage; 
      }
      public void setExtraMessage(String extraMessage) { 
    	  this.extraMessage = extraMessage; 
      }
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

      public Vector<String> getContent() {
            return Content;
      }
      public void setContent(Vector<String> content) {
            Content = content;
      }
}