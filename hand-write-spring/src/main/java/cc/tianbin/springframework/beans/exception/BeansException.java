package cc.tianbin.springframework.beans.exception;

import io.github.nibnait.common.utils.DataUtils;

public class BeansException extends RuntimeException {

    private final String message;

    public BeansException(Throwable cause) {
        this(cause, "null");
    }

    public BeansException(Throwable cause, String message) {
        super(message, cause);
        this.message = message;
    }

    public BeansException(Throwable cause, String format, Object... args) {
        super(DataUtils.format(format, args), cause);
        this.message = DataUtils.format(format, args);
    }

    public BeansException(String format, Object... args) {
        super(DataUtils.format(format, args));
        this.message = DataUtils.format(format, args);
    }

    @Override
    public String getMessage() {
        return message;
    }

}
