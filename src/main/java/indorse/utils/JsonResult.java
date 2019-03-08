package indorse.utils;

/**
 * Created by vsantos on 7/03/2019.
 */
public class JsonResult {

    private Boolean success;
    private Object result;
    private String message;

    public JsonResult() {
    }

    public JsonResult(Boolean success, Object result, String message) {
        this.success = success;
        this.result = result;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
