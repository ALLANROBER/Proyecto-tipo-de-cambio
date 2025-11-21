package dto;

public class MessageDto {

    private boolean success;
    private String message;
    private Object data;

    public MessageDto() {}

    public MessageDto(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public MessageDto(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
