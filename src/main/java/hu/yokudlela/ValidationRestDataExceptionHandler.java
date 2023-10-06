package hu.yokudlela.table.application.error;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;

import hu.yokudlela.table.business.BusinessException;
import hu.yokudlela.table.business.DataValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author user
 */
@ControllerAdvice
@Component
@Slf4j
public class ValidationRestDataExceptionHandler extends ResponseEntityExceptionHandler{
    @Autowired
    HttpServletRequest req;




        @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> businessExceptionHandle(Exception ex, WebRequest request) {
        log.error(ex.getLocalizedMessage(), ex);
       ApiError res = new ApiError(req.getRequestURI(),"error.business");
        res.getErrors().add(ex.getMessage());
        return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<Object> unexpectedTypeExceptionHandle(Exception ex, WebRequest request) {
        log.error(ex.getLocalizedMessage(), ex);
        ApiError res = new ApiError(req.getRequestURI(),"error.business");
        res.getErrors().add(ex.getMessage());
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({DataValidationException.class, ConversionFailedException.class})
    public ResponseEntity<Object> dataValidationExceptionHandle(Exception ex, WebRequest request) {
        log.error(ex.getLocalizedMessage(), ex);
        ApiError res = new ApiError(req.getRequestURI(),"error.business");
        res.getErrors().add(ex.getMessage());
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);

    }

     @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> HandleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        log.error(ex.getLocalizedMessage(), ex);
       ApiError res = new ApiError(req.getRequestURI(),"error.validation");
 /*     
       for(Throwable msg: ex.getSuppressed()){
        res.getErrors().add(msg.getLocalizedMessage());
       }
*/         
       for(String msg: ex.getMessage().split(",")){
        res.getErrors().add(msg.split(":")[1]);
       }
    
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);

    }
    

    
   @ResponseBody
   @Override
   protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                 HttpHeaders headers, HttpStatus status, WebRequest request) {

        log.error(ex.getLocalizedMessage(), ex);
       ApiError res = new ApiError(req.getRequestURI(),"error.validation");

       ex.getBindingResult().getAllErrors().forEach((ObjectError error) -> {
           res.getErrors().add(
                   (error.getDefaultMessage().indexOf(' ')>0)?"error.validation.bind":error.getDefaultMessage());
       });
      
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    
    }

    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getLocalizedMessage(), ex);
        ApiError res = new ApiError(req.getRequestURI(),"error.validation");

        ex.getBindingResult().getAllErrors().forEach((ObjectError error) -> {
            res.getErrors().add(
                    (error.getDefaultMessage().indexOf(' ')>0)?"error.validation.bind":error.getDefaultMessage());
        });

        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getLocalizedMessage(), ex);
        ApiError res = new ApiError(req.getRequestURI(),"error.validation");

            res.getErrors().add(
                    (ex.getLocalizedMessage().indexOf(' ')>0)?"error.validation.bind":ex.getLocalizedMessage());

        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

//    IllegalArgumentException
//    OptimisticLockingFailureException
}
